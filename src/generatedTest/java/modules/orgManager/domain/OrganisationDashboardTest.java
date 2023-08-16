package modules.orgManager.domain;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFixture.FixtureType;
import util.AbstractDomainTest;

/**
 * Generated - local changes will be overwritten.
 * Extend {@link AbstractDomainTest} to create your own tests for this document.
 */
public class OrganisationDashboardTest extends AbstractDomainTest<OrganisationDashboard> {

	@Override
	protected OrganisationDashboard getBean() throws Exception {
		return new DataBuilder()
			.fixture(FixtureType.crud)
			.build(OrganisationDashboard.MODULE_NAME, OrganisationDashboard.DOCUMENT_NAME);
	}
}