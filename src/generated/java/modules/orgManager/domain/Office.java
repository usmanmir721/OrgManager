package modules.orgManager.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import modules.orgManager.AbstractLastChanged.AbstractLastChangedExtension;
import modules.orgManager.Office.OfficeExtension;
import modules.orgManager.Staff.StaffExtension;
import org.locationtech.jts.geom.Geometry;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.impl.domain.types.jaxb.GeometryMapper;

/**
 * Office
 * 
 * @navhas n neverInOffice 0..n Staff
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public abstract class Office extends AbstractLastChangedExtension {
	/**
	 * For Serialization
	 * @hidden
	 */
	private static final long serialVersionUID = 1L;

	/** @hidden */
	@SuppressWarnings("hiding")
	public static final String MODULE_NAME = "orgManager";

	/** @hidden */
	@SuppressWarnings("hiding")
	public static final String DOCUMENT_NAME = "Office";

	/** @hidden */
	public static final String levelUnitPropertyName = "levelUnit";

	/** @hidden */
	public static final String buildingNamePropertyName = "buildingName";

	/** @hidden */
	public static final String streetPropertyName = "street";

	/** @hidden */
	public static final String suburbPropertyName = "suburb";

	/** @hidden */
	public static final String postcodePropertyName = "postcode";

	/** @hidden */
	public static final String phonePropertyName = "phone";

	/** @hidden */
	public static final String attachmentPropertyName = "attachment";

	/** @hidden */
	public static final String boundaryPropertyName = "boundary";

	/** @hidden */
	public static final String totalStaffCountPropertyName = "totalStaffCount";

	/** @hidden */
	public static final String inOfficeCountPropertyName = "inOfficeCount";

	/** @hidden */
	public static final String neverInOfficePropertyName = "neverInOffice";

	/**
	 * Level Unit
	 **/
	private String levelUnit;

	/**
	 * Building name
	 **/
	private String buildingName;

	/**
	 * Street
	 **/
	private String street;

	/**
	 * Suburb
	 **/
	private String suburb;

	/**
	 * Postcode
	 **/
	private String postcode;

	/**
	 * Phone
	 **/
	private String phone;

	/**
	 * Attachment
	 **/
	private String attachment;

	/**
	 * Office Boundary
	 **/
	private Geometry boundary;

	/**
	 * Total number of Staff
	 **/
	private Integer totalStaffCount;

	/**
	 * In Office Staff
	 **/
	private Integer inOfficeCount;

	/**
	 * Staff never in office
	 **/
	private List<StaffExtension> neverInOffice = new ArrayList<>();

	@Override
	@XmlTransient
	public String getBizModule() {
		return Office.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Office.DOCUMENT_NAME;
	}

	public static OfficeExtension newInstance() {
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
			return org.skyve.util.Binder.formatMessage("Office-{buildingName}-{suburb}", this);
		}
		catch (@SuppressWarnings("unused") Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Office) && 
					this.getBizId().equals(((Office) o).getBizId()));
	}

	/**
	 * {@link #levelUnit} accessor.
	 * @return	The value.
	 **/
	public String getLevelUnit() {
		return levelUnit;
	}

	/**
	 * {@link #levelUnit} mutator.
	 * @param levelUnit	The new value.
	 **/
	@XmlElement
	public void setLevelUnit(String levelUnit) {
		preset(levelUnitPropertyName, levelUnit);
		this.levelUnit = levelUnit;
	}

	/**
	 * {@link #buildingName} accessor.
	 * @return	The value.
	 **/
	public String getBuildingName() {
		return buildingName;
	}

	/**
	 * {@link #buildingName} mutator.
	 * @param buildingName	The new value.
	 **/
	@XmlElement
	public void setBuildingName(String buildingName) {
		preset(buildingNamePropertyName, buildingName);
		this.buildingName = buildingName;
	}

	/**
	 * {@link #street} accessor.
	 * @return	The value.
	 **/
	public String getStreet() {
		return street;
	}

	/**
	 * {@link #street} mutator.
	 * @param street	The new value.
	 **/
	@XmlElement
	public void setStreet(String street) {
		preset(streetPropertyName, street);
		this.street = street;
	}

	/**
	 * {@link #suburb} accessor.
	 * @return	The value.
	 **/
	public String getSuburb() {
		return suburb;
	}

	/**
	 * {@link #suburb} mutator.
	 * @param suburb	The new value.
	 **/
	@XmlElement
	public void setSuburb(String suburb) {
		preset(suburbPropertyName, suburb);
		this.suburb = suburb;
	}

	/**
	 * {@link #postcode} accessor.
	 * @return	The value.
	 **/
	public String getPostcode() {
		return postcode;
	}

	/**
	 * {@link #postcode} mutator.
	 * @param postcode	The new value.
	 **/
	@XmlElement
	public void setPostcode(String postcode) {
		preset(postcodePropertyName, postcode);
		this.postcode = postcode;
	}

	/**
	 * {@link #phone} accessor.
	 * @return	The value.
	 **/
	public String getPhone() {
		return phone;
	}

	/**
	 * {@link #phone} mutator.
	 * @param phone	The new value.
	 **/
	@XmlElement
	public void setPhone(String phone) {
		preset(phonePropertyName, phone);
		this.phone = phone;
	}

	/**
	 * {@link #attachment} accessor.
	 * @return	The value.
	 **/
	public String getAttachment() {
		return attachment;
	}

	/**
	 * {@link #attachment} mutator.
	 * @param attachment	The new value.
	 **/
	@XmlElement
	public void setAttachment(String attachment) {
		preset(attachmentPropertyName, attachment);
		this.attachment = attachment;
	}

	/**
	 * {@link #boundary} accessor.
	 * @return	The value.
	 **/
	public Geometry getBoundary() {
		return boundary;
	}

	/**
	 * {@link #boundary} mutator.
	 * @param boundary	The new value.
	 **/
	@XmlElement
	@XmlJavaTypeAdapter(GeometryMapper.class)
	public void setBoundary(Geometry boundary) {
		preset(boundaryPropertyName, boundary);
		this.boundary = boundary;
	}

	/**
	 * {@link #totalStaffCount} accessor.
	 * @return	The value.
	 **/
	public Integer getTotalStaffCount() {
		return totalStaffCount;
	}

	/**
	 * {@link #totalStaffCount} mutator.
	 * @param totalStaffCount	The new value.
	 **/
	@XmlElement
	public void setTotalStaffCount(Integer totalStaffCount) {
		this.totalStaffCount = totalStaffCount;
	}

	/**
	 * {@link #inOfficeCount} accessor.
	 * @return	The value.
	 **/
	public Integer getInOfficeCount() {
		return inOfficeCount;
	}

	/**
	 * {@link #inOfficeCount} mutator.
	 * @param inOfficeCount	The new value.
	 **/
	@XmlElement
	public void setInOfficeCount(Integer inOfficeCount) {
		this.inOfficeCount = inOfficeCount;
	}

	/**
	 * {@link #neverInOffice} accessor.
	 * @return	The value.
	 **/
	@XmlElement
	public List<StaffExtension> getNeverInOffice() {
		return neverInOffice;
	}

	/**
	 * {@link #neverInOffice} accessor.
	 * @param bizId	The bizId of the element in the list.
	 * @return	The value of the element in the list.
	 **/
	public StaffExtension getNeverInOfficeElementById(String bizId) {
		return getElementById(neverInOffice, bizId);
	}

	/**
	 * {@link #neverInOffice} mutator.
	 * @param bizId	The bizId of the element in the list.
	 * @param element	The new value of the element in the list.
	 **/
	public void setNeverInOfficeElementById(String bizId, StaffExtension element) {
		setElementById(neverInOffice, element);
	}

	/**
	 * {@link #neverInOffice} add.
	 * @param element	The element to add.
	 **/
	public boolean addNeverInOfficeElement(StaffExtension element) {
		return neverInOffice.add(element);
	}

	/**
	 * {@link #neverInOffice} add.
	 * @param index	The index in the list to add the element to.
	 * @param element	The element to add.
	 **/
	public void addNeverInOfficeElement(int index, StaffExtension element) {
		neverInOffice.add(index, element);
	}

	/**
	 * {@link #neverInOffice} remove.
	 * @param element	The element to remove.
	 **/
	public boolean removeNeverInOfficeElement(StaffExtension element) {
		return neverInOffice.remove(element);
	}

	/**
	 * {@link #neverInOffice} remove.
	 * @param index	The index in the list to remove the element from.
	 **/
	public StaffExtension removeNeverInOfficeElement(int index) {
		return neverInOffice.remove(index);
	}
}
