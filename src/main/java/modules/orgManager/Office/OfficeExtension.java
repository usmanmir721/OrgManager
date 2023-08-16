package modules.orgManager.Office;

import java.util.List;
import java.util.stream.Collectors;

import org.skyve.CORE;
import org.skyve.persistence.DocumentQuery;

import modules.orgManager.Staff.StaffExtension;
import modules.orgManager.domain.Office;
import modules.orgManager.domain.Staff;

public class OfficeExtension extends Office {
	
	public DocumentQuery getAllStaff() {
		DocumentQuery q = CORE.getPersistence().newDocumentQuery(Staff.MODULE_NAME, Staff.DOCUMENT_NAME);
		q.getFilter().addEquals(Staff.baseOfficePropertyName, this);
		q.addBoundOrdering(Staff.codePropertyName);
		return q;
	}
	
	public List<StaffExtension> getAllStaffList() {
		return getAllStaff().beanResults();
	}
	
	public Integer getAllStaffCount() {
		return getAllStaffList().size();
	}
	
	public Integer getAllStaffInOfficeCount() {
		DocumentQuery q = getAllStaff();
		q.getFilter().addEquals(Staff.statusPropertyName, Staff.Status.in);
		
		return q.beanResults().size();
	}
	
	/**
	 * Get a list of staff who have never been in office
	 * @return List of StaffExtension
	 */
	
	public List<StaffExtension> getAllStaffNeverInOffice() {
		List<StaffExtension> l = getAllStaffList();
		return l.stream()
				.filter(se -> se.haveNeverBeenInOffice() == true)
				.collect(Collectors.toList());
	}

}
