package modules.orgManager.Staff;

import java.util.concurrent.ThreadLocalRandom;


import org.skyve.domain.types.DateOnly;
import org.skyve.util.DataBuilder;
import org.skyve.util.test.DataMap;
import org.skyve.util.test.SkyveFactory;
import org.skyve.util.test.SkyveFixture;
import org.skyve.util.test.SkyveFixture.FixtureType;

import com.ibm.icu.util.Calendar;

import modules.orgManager.domain.Staff;

@SkyveFactory(value = {
		@DataMap(attributeName = Staff.firstNamePropertyName, fileName = "firstName.txt"),
		@DataMap(attributeName = Staff.lastNamePropertyName, fileName = "lastName.txt")
})
public class StaffFactory {
	/**
	 * Utility method to generate custom test data with staff with ages between 18-50 yrs
	 * @return Bean with dob set
	 */
	@SkyveFixture(types = FixtureType.crud)
	public static Staff crudInstance() {
		Staff bean = new DataBuilder().build(Staff.MODULE_NAME, Staff.DOCUMENT_NAME);
		
		int age = ThreadLocalRandom.current().nextInt(18, 49 + 1);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1 * age);
		
		DateOnly dateOnly = new DateOnly(cal.getTimeInMillis());
		bean.setDateOfBirth(dateOnly);

		return bean;
	}
	
}
