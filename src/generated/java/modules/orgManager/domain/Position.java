package modules.orgManager.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import modules.orgManager.Position.PositionExtension;
import modules.orgManager.Staff.StaffExtension;
import org.skyve.CORE;
import org.skyve.domain.Bean;
import org.skyve.domain.HierarchicalBean;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.AbstractPersistentBean;
import org.skyve.persistence.DocumentQuery;
import org.skyve.persistence.Persistence;

/**
 * Position
 * 
 * @navhas n staff 0..1 Staff
 * @navhas n reportsTo 0..1 Position
 * @stereotype "persistent child"
 */
@XmlType
@XmlRootElement
public abstract class Position extends AbstractPersistentBean implements HierarchicalBean<PositionExtension> {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	public static final String MODULE_NAME = "orgManager";

	/** @hidden */
	public static final String DOCUMENT_NAME = "Position";

	/** @hidden */
	public static final String positionTitlePropertyName = "positionTitle";

	/** @hidden */
	public static final String staffPropertyName = "staff";

	/** @hidden */
	public static final String reportsToPropertyName = "reportsTo";

	/**
	 * Position Title
	 **/
	private String positionTitle;

	/**
	 * Staff
	 **/
	private StaffExtension staff = null;

	/**
	 * Reports To
	 **/
	private PositionExtension reportsTo = null;

	private String bizParentId;

	@Override
	@XmlTransient
	public String getBizModule() {
		return Position.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Position.DOCUMENT_NAME;
	}

	public static PositionExtension newInstance() {
		try {
			return CORE.getUser().getCustomer().getModule(MODULE_NAME).getDocument(CORE.getUser().getCustomer(), DOCUMENT_NAME).newInstance(CORE.getUser());
		}
		catch (RuntimeException e) {
			throw e;
		}
		catch (Exception e) {
			throw new DomainException(e);
		}
	}

	@Override
	@XmlTransient
	public String getBizKey() {
		try {
			return org.skyve.util.Binder.formatMessage("{positionTitle} ({staff.bizKey}-{staff.firstName} {staff.lastName})", this);
		}
		catch (@SuppressWarnings("unused") Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Position) && 
					this.getBizId().equals(((Position) o).getBizId()));
	}

	/**
	 * {@link #positionTitle} accessor.
	 * @return	The value.
	 **/
	public String getPositionTitle() {
		return positionTitle;
	}

	/**
	 * {@link #positionTitle} mutator.
	 * @param positionTitle	The new value.
	 **/
	@XmlElement
	public void setPositionTitle(String positionTitle) {
		preset(positionTitlePropertyName, positionTitle);
		this.positionTitle = positionTitle;
	}

	/**
	 * {@link #staff} accessor.
	 * @return	The value.
	 **/
	public StaffExtension getStaff() {
		return staff;
	}

	/**
	 * {@link #staff} mutator.
	 * @param staff	The new value.
	 **/
	@XmlElement
	public void setStaff(StaffExtension staff) {
		if (this.staff != staff) {
			preset(staffPropertyName, staff);
			this.staff = staff;
		}
	}

	/**
	 * {@link #reportsTo} accessor.
	 * @return	The value.
	 **/
	public PositionExtension getReportsTo() {
		return reportsTo;
	}

	/**
	 * {@link #reportsTo} mutator.
	 * @param reportsTo	The new value.
	 **/
	@XmlElement
	public void setReportsTo(PositionExtension reportsTo) {
		if (this.reportsTo != reportsTo) {
			this.reportsTo = reportsTo;
		}
	}

	@Override
	public String getBizParentId() {
		return bizParentId;
	}

	@Override
	@XmlElement
	public void setBizParentId(String bizParentId) {
		preset(HierarchicalBean.PARENT_ID, bizParentId);
		this.bizParentId = bizParentId;
	}

	@Override
	public PositionExtension getParent() {
		PositionExtension result = null;

		if (bizParentId != null) {
			Persistence p = CORE.getPersistence();
			DocumentQuery q = p.newDocumentQuery(Position.MODULE_NAME, Position.DOCUMENT_NAME);
			q.getFilter().addEquals(Bean.DOCUMENT_ID, bizParentId);
			result = q.retrieveBean();
		}

		return result;
	}

	@Override
	@XmlTransient
	public List<PositionExtension> getChildren() {
		Persistence p = CORE.getPersistence();
		DocumentQuery q = p.newDocumentQuery(Position.MODULE_NAME, Position.DOCUMENT_NAME);
		q.getFilter().addEquals(HierarchicalBean.PARENT_ID, getBizId());
		return q.beanResults();
	}
}
