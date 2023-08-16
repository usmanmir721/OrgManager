package modules.orgManager.Staff;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyve.domain.types.Timestamp;
import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;

import modules.orgManager.domain.Staff;
import util.AbstractH2TestForJUnit5;
import modules.orgManager.domain.Staff.Status;
import modules.orgManager.domain.StaffStatusHistory;

public class StaffExtensionH2Test extends AbstractH2TestForJUnit5 {
	
	private DataBuilder db;
	private StaffExtension se;
	
	@BeforeEach
	public void setup() throws Exception {
		this.db = new DataBuilder().fixture(FixtureType.crud);
		this.se = this.db.build(Staff.MODULE_NAME, Staff.DOCUMENT_NAME);
	}
	
	@Test
	public void testHaveNeverBeenInOfficeWithStatusNull() {
		this.se.setStatus(null);
		this.se.getStatusHistory().clear();
		
		Assert.assertTrue(this.se.haveNeverBeenInOffice());
	}
	
	@Test
	public void testHaveNeverBeenInOfficeWithStatusSet() {
		this.se.setStatus(Status.in);
		this.se.getStatusHistory().clear();
		
		Assert.assertFalse(this.se.haveNeverBeenInOffice());
	}
	
	@Test
	public void testHaveNeverBeenInOfficeWithStatusAndHistorySet() {
		this.se.setStatus(Status.out);
		StaffStatusHistory ssh = StaffStatusHistory.newInstance();
		ssh.setStatus(Status.in);
		ssh.setTime(new Timestamp());
		this.se.addStatusHistoryElement(ssh);
		ssh = StaffStatusHistory.newInstance();
		ssh.setStatus(Status.out);
		ssh.setTime(new Timestamp());
		this.se.addStatusHistoryElement(ssh);
		ssh = StaffStatusHistory.newInstance();
		ssh.setStatus(Status.outForLunch);
		ssh.setTime(new Timestamp());
		this.se.addStatusHistoryElement(ssh);
		
		Assert.assertFalse(this.se.haveNeverBeenInOffice());
	}
	
}
