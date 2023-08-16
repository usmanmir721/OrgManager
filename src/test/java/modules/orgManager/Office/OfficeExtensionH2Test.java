package modules.orgManager.Office;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyve.CORE;
import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;

import modules.orgManager.domain.Office;
import util.AbstractH2TestForJUnit5;

public class OfficeExtensionH2Test extends AbstractH2TestForJUnit5 {
	
	private DataBuilder db;
	private OfficeExtension oe;
	
	@BeforeEach
	public void setup() throws Exception {
		this.db = new DataBuilder().fixture(FixtureType.crud);
		this.oe = this.db.build(Office.MODULE_NAME, Office.DOCUMENT_NAME);
	}
	
	@Test
	public void testtotalStaffCountZero() {
		this.oe = CORE.getPersistence().save(this.oe);
		this.oe.setTotalStaffCount(this.oe.getAllStaffCount());
		Assert.assertTrue(this.oe.getTotalStaffCount().equals(0));
	}

}
