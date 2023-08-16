package modules.orgManager.Staff;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyve.domain.types.DateOnly;
import org.skyve.util.Time;

public class StaffExtensionTest {
	
	@Mock
	StaffExtension staffExtension;

	private AutoCloseable closeable;

	@BeforeEach
	public void openMocks() {
		closeable = MockitoAnnotations.openMocks(this);
		}

	@AfterEach
	public void releaseMocks() throws Exception {
		closeable.close();
	}
	
	
	@Test
	public void testCalculateAgeInYears() {
		when(staffExtension.getDateOfBirth()).thenReturn(Time.addYearsToNew(new DateOnly(), -18));
		Mockito.doCallRealMethod().when(staffExtension).calculateAgeInYears();
		Mockito.doCallRealMethod().when(staffExtension).getAgeInYears();
		
		staffExtension.calculateAgeInYears();
		
		Assert.assertEquals(Integer.valueOf(18), staffExtension.getAgeInYears());
	}
	
	
	@Test
	public void testCalculateAgeInYearsWhenDOBNULL() {
		StaffExtension se = StaffExtension.newInstance();
		se.calculateAgeInYears();
		
		Assert.assertNull(se.getAgeInYears());
	}

}
