package modules.orgManager.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import modules.orgManager.AbstractLastChanged.AbstractLastChangedExtension;
import modules.orgManager.Office.OfficeExtension;
import modules.orgManager.Position.PositionExtension;
import modules.orgManager.Staff.StaffExtension;
import org.locationtech.jts.geom.Geometry;
import org.skyve.CORE;
import org.skyve.domain.messages.DomainException;
import org.skyve.domain.types.DateOnly;
import org.skyve.domain.types.Enumeration;
import org.skyve.impl.domain.ChangeTrackingArrayList;
import org.skyve.impl.domain.types.jaxb.DateOnlyMapper;
import org.skyve.impl.domain.types.jaxb.GeometryMapper;
import org.skyve.metadata.model.document.Bizlet.DomainValue;
import org.skyve.util.Util;

/**
 * Staff
 * 
 * @depend - - - Status
 * @navcomposed 1 statusHistory 0..n StaffStatusHistory
 * @navhas n baseOffice 0..1 Office
 * @navhas n reportsTo 0..1 Position
 * @stereotype "persistent"
 */
@XmlType
@XmlRootElement
public abstract class Staff extends AbstractLastChangedExtension {
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
	public static final String DOCUMENT_NAME = "Staff";

	/** @hidden */
	public static final String codePropertyName = "code";

	/** @hidden */
	public static final String firstNamePropertyName = "firstName";

	/** @hidden */
	public static final String lastNamePropertyName = "lastName";

	/** @hidden */
	public static final String dateOfBirthPropertyName = "dateOfBirth";

	/** @hidden */
	public static final String ageInYearsPropertyName = "ageInYears";

	/** @hidden */
	public static final String addressPropertyName = "address";

	/** @hidden */
	public static final String locationPropertyName = "location";

	/** @hidden */
	public static final String profileImagePropertyName = "profileImage";

	/** @hidden */
	public static final String statusPropertyName = "status";

	/** @hidden */
	public static final String baseOfficePropertyName = "baseOffice";

	/** @hidden */
	public static final String positionTitlePropertyName = "positionTitle";

	/** @hidden */
	public static final String statusHistoryPropertyName = "statusHistory";

	/** @hidden */
	public static final String statusHistoryCountPropertyName = "statusHistoryCount";

	/** @hidden */
	public static final String reportsToPropertyName = "reportsTo";

	/**
	 * Status
	 **/
	@XmlEnum
	public static enum Status implements Enumeration {
		in("#in", "In"),
		outForLunch("#outForLunch", "Out For Lunch"),
		out("#out", "Out");

		private String code;
		private String description;

		/** @hidden */
		private DomainValue domainValue;

		/** @hidden */
		private static List<DomainValue> domainValues;

		private Status(String code, String description) {
			this.code = code;
			this.description = description;
			this.domainValue = new DomainValue(code, description);
		}

		@Override
		public String toCode() {
			return code;
		}

		@Override
		public String toLocalisedDescription() {
			return Util.i18n(description);
		}

		@Override
		public DomainValue toDomainValue() {
			return domainValue;
		}

		public static Status fromCode(String code) {
			Status result = null;

			for (Status value : values()) {
				if (value.code.equals(code)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static Status fromLocalisedDescription(String description) {
			Status result = null;

			for (Status value : values()) {
				if (value.toLocalisedDescription().equals(description)) {
					result = value;
					break;
				}
			}

			return result;
		}

		public static List<DomainValue> toDomainValues() {
			if (domainValues == null) {
				Status[] values = values();
				domainValues = new ArrayList<>(values.length);
				for (Status value : values) {
					domainValues.add(value.domainValue);
				}
			}

			return domainValues;
		}
	}

	/**
	 * Code
	 **/
	private String code;

	/**
	 * First Name
	 **/
	private String firstName;

	/**
	 * Last Name
	 **/
	private String lastName;

	/**
	 * DOB
	 **/
	private DateOnly dateOfBirth;

	/**
	 * Age in years
	 **/
	private Integer ageInYears;

	/**
	 * Address
	 **/
	private String address;

	/**
	 * Location
	 **/
	private Geometry location;

	/**
	 * Profile Image
	 **/
	private String profileImage;

	/**
	 * Status
	 **/
	private Status status = Status.in;

	/**
	 * Base Office
	 **/
	private OfficeExtension baseOffice = null;

	/**
	 * Position Title
	 **/
	private String positionTitle;

	/**
	 * Status History
	 **/
	private List<StaffStatusHistory> statusHistory = new ChangeTrackingArrayList<>("statusHistory", this);

	/**
	 * Status History Count
	 **/
	private Integer statusHistoryCount;

	/**
	 * Reports To
	 **/
	private PositionExtension reportsTo = null;

	@Override
	@XmlTransient
	public String getBizModule() {
		return Staff.MODULE_NAME;
	}

	@Override
	@XmlTransient
	public String getBizDocument() {
		return Staff.DOCUMENT_NAME;
	}

	public static StaffExtension newInstance() {
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
			return org.skyve.util.Binder.formatMessage("{code}", this);
		}
		catch (@SuppressWarnings("unused") Exception e) {
			return "Unknown";
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof Staff) && 
					this.getBizId().equals(((Staff) o).getBizId()));
	}

	/**
	 * {@link #code} accessor.
	 * @return	The value.
	 **/
	public String getCode() {
		return code;
	}

	/**
	 * {@link #code} mutator.
	 * @param code	The new value.
	 **/
	@XmlElement
	public void setCode(String code) {
		preset(codePropertyName, code);
		this.code = code;
	}

	/**
	 * {@link #firstName} accessor.
	 * @return	The value.
	 **/
	public String getFirstName() {
		return firstName;
	}

	/**
	 * {@link #firstName} mutator.
	 * @param firstName	The new value.
	 **/
	@XmlElement
	public void setFirstName(String firstName) {
		preset(firstNamePropertyName, firstName);
		this.firstName = firstName;
	}

	/**
	 * {@link #lastName} accessor.
	 * @return	The value.
	 **/
	public String getLastName() {
		return lastName;
	}

	/**
	 * {@link #lastName} mutator.
	 * @param lastName	The new value.
	 **/
	@XmlElement
	public void setLastName(String lastName) {
		preset(lastNamePropertyName, lastName);
		this.lastName = lastName;
	}

	/**
	 * {@link #dateOfBirth} accessor.
	 * @return	The value.
	 **/
	public DateOnly getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * {@link #dateOfBirth} mutator.
	 * @param dateOfBirth	The new value.
	 **/
	@XmlElement
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(DateOnlyMapper.class)
	public void setDateOfBirth(DateOnly dateOfBirth) {
		preset(dateOfBirthPropertyName, dateOfBirth);
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * {@link #ageInYears} accessor.
	 * @return	The value.
	 **/
	public Integer getAgeInYears() {
		return ageInYears;
	}

	/**
	 * {@link #ageInYears} mutator.
	 * @param ageInYears	The new value.
	 **/
	@XmlElement
	public void setAgeInYears(Integer ageInYears) {
		preset(ageInYearsPropertyName, ageInYears);
		this.ageInYears = ageInYears;
	}

	/**
	 * {@link #address} accessor.
	 * @return	The value.
	 **/
	public String getAddress() {
		return address;
	}

	/**
	 * {@link #address} mutator.
	 * @param address	The new value.
	 **/
	@XmlElement
	public void setAddress(String address) {
		preset(addressPropertyName, address);
		this.address = address;
	}

	/**
	 * {@link #location} accessor.
	 * @return	The value.
	 **/
	public Geometry getLocation() {
		return location;
	}

	/**
	 * {@link #location} mutator.
	 * @param location	The new value.
	 **/
	@XmlElement
	@XmlJavaTypeAdapter(GeometryMapper.class)
	public void setLocation(Geometry location) {
		preset(locationPropertyName, location);
		this.location = location;
	}

	/**
	 * {@link #profileImage} accessor.
	 * @return	The value.
	 **/
	public String getProfileImage() {
		return profileImage;
	}

	/**
	 * {@link #profileImage} mutator.
	 * @param profileImage	The new value.
	 **/
	@XmlElement
	public void setProfileImage(String profileImage) {
		preset(profileImagePropertyName, profileImage);
		this.profileImage = profileImage;
	}

	/**
	 * {@link #status} accessor.
	 * @return	The value.
	 **/
	public Status getStatus() {
		return status;
	}

	/**
	 * {@link #status} mutator.
	 * @param status	The new value.
	 **/
	@XmlElement
	public void setStatus(Status status) {
		preset(statusPropertyName, status);
		this.status = status;
	}

	/**
	 * {@link #baseOffice} accessor.
	 * @return	The value.
	 **/
	public OfficeExtension getBaseOffice() {
		return baseOffice;
	}

	/**
	 * {@link #baseOffice} mutator.
	 * @param baseOffice	The new value.
	 **/
	@XmlElement
	public void setBaseOffice(OfficeExtension baseOffice) {
		if (this.baseOffice != baseOffice) {
			preset(baseOfficePropertyName, baseOffice);
			this.baseOffice = baseOffice;
		}
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
	 * {@link #statusHistory} accessor.
	 * @return	The value.
	 **/
	@XmlElement
	public List<StaffStatusHistory> getStatusHistory() {
		return statusHistory;
	}

	/**
	 * {@link #statusHistory} accessor.
	 * @param bizId	The bizId of the element in the list.
	 * @return	The value of the element in the list.
	 **/
	public StaffStatusHistory getStatusHistoryElementById(String bizId) {
		return getElementById(statusHistory, bizId);
	}

	/**
	 * {@link #statusHistory} mutator.
	 * @param bizId	The bizId of the element in the list.
	 * @param element	The new value of the element in the list.
	 **/
	public void setStatusHistoryElementById(String bizId, StaffStatusHistory element) {
		setElementById(statusHistory, element);
	}

	/**
	 * {@link #statusHistory} add.
	 * @param element	The element to add.
	 **/
	public boolean addStatusHistoryElement(StaffStatusHistory element) {
		boolean result = statusHistory.add(element);
		if (result) {
			element.setParent((StaffExtension) this);
		}
		return result;
	}

	/**
	 * {@link #statusHistory} add.
	 * @param index	The index in the list to add the element to.
	 * @param element	The element to add.
	 **/
	public void addStatusHistoryElement(int index, StaffStatusHistory element) {
		statusHistory.add(index, element);
		element.setParent((StaffExtension) this);
	}

	/**
	 * {@link #statusHistory} remove.
	 * @param element	The element to remove.
	 **/
	public boolean removeStatusHistoryElement(StaffStatusHistory element) {
		boolean result = statusHistory.remove(element);
		if (result) {
			element.setParent(null);
		}
		return result;
	}

	/**
	 * {@link #statusHistory} remove.
	 * @param index	The index in the list to remove the element from.
	 **/
	public StaffStatusHistory removeStatusHistoryElement(int index) {
		StaffStatusHistory result = statusHistory.remove(index);
		result.setParent(null);
		return result;
	}

	/**
	 * {@link #statusHistoryCount} accessor.
	 * @return	The value.
	 **/
	public Integer getStatusHistoryCount() {
		return statusHistoryCount;
	}

	/**
	 * {@link #statusHistoryCount} mutator.
	 * @param statusHistoryCount	The new value.
	 **/
	@XmlElement
	public void setStatusHistoryCount(Integer statusHistoryCount) {
		this.statusHistoryCount = statusHistoryCount;
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
}
