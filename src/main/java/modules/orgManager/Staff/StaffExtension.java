package modules.orgManager.Staff;

import java.util.Objects;

import org.locationtech.jts.geom.Point;
import org.skyve.CORE;
import org.skyve.domain.types.DateOnly;
import org.skyve.impl.util.TimeUtil;
import org.skyve.persistence.DocumentQuery;

import modules.orgManager.Position.PositionExtension;
import modules.orgManager.domain.Position;
import modules.orgManager.domain.Staff;
import modules.orgManager.domain.StaffStatusHistory;

public class StaffExtension extends Staff {
	
	public void calculateAgeInYears() {
		if (!Objects.isNull(getDateOfBirth())) {
			this.setAgeInYears(TimeUtil.getYear(new DateOnly()) - TimeUtil.getYear(getDateOfBirth()));
		}
	}
	
	public Integer getStatusHistoryCount() {
		return this.getStatusHistory().size();
	}
	
	/**
	 * Sets staff location to office location
	 */
	public void home() {
		Point point;
		if (!Objects.isNull(this.getBaseOffice()) 
				&& !Objects.isNull(this.getBaseOffice().getBoundary())
				&& !Objects.isNull(this.getBaseOffice().getBoundary().getCentroid())){
			point = this.getBaseOffice().getBoundary().getCentroid();
			this.setLocation(point);
		}
	}
	
	/**
	 * Check is the staff has never been in office.
	 * @return True if never been in office, otherwise false.
	 */
	public boolean haveNeverBeenInOffice() {
		// Check current status
		if (Status.in.equals(this.getStatus())) {
			return false;
		}
		// Check status history and
		// return false as soon as we get a status "in"
	    for (StaffStatusHistory sh: this.getStatusHistory()) {
	    	if (sh.getStatus().equals(Status.in)) {
	    		return false;
	    	}
	    }
	    return true;
	}
	
	/**
	 * Get Position by staff
	 * @param staff
	 * @return Position of staff
	 */
	public static PositionExtension getPosition(Staff staff) {
		DocumentQuery q = CORE.getPersistence().newDocumentQuery(Position.MODULE_NAME, Position.DOCUMENT_NAME);
		q.getFilter().addEquals(Position.staffPropertyName, staff);
		return q.beanResult();
	}
	
}
