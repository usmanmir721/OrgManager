package modules.orgManager.OrganisationDashboard;

import org.skyve.metadata.model.document.Bizlet;

import modules.admin.ModulesUtil;
import modules.orgManager.Staff.StaffExtension;
import modules.orgManager.domain.OrganisationDashboard;

public class OrganisationDashboardBizlet extends Bizlet<OrganisationDashboard> {

	@Override
	public OrganisationDashboard newInstance(OrganisationDashboard bean) throws Exception {
		
		String username = ModulesUtil.currentAdminUser().getUserName();
		bean.setUserName(username);
		
		return super.newInstance(bean);
	}
	
	

}
