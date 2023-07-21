package modules.orgManager.Staff;

import java.util.Objects;

import org.skyve.domain.types.DateOnly;
import org.skyve.impl.util.TimeUtil;

import modules.orgManager.domain.Staff;

public class StaffExtension extends Staff {
	
	public void calculateAgeInYears() {
		if (!Objects.isNull(getDateOfBirth())) {
			this.setAgeInYears(TimeUtil.getYear(new DateOnly()) - TimeUtil.getYear(getDateOfBirth()));
		}
	}
	
	public Integer getStatusHistoryCount() {
		return this.getStatusHistory().size();
	}
	
	

}
