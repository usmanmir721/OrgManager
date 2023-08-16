package modules.orgManager.Office;

import org.skyve.domain.Bean;
import org.skyve.domain.types.Timestamp;
import org.skyve.metadata.controller.ImplicitActionName;
import org.skyve.metadata.model.document.Bizlet;
import org.skyve.web.WebContext;

import modules.orgManager.Staff.StaffExtension;
import modules.orgManager.domain.Staff;
import modules.orgManager.domain.StaffStatusHistory;

public class OfficeBizlet extends Bizlet<OfficeExtension> {
	
	@Override
	public OfficeExtension preExecute(ImplicitActionName actionName, OfficeExtension bean, Bean parentBean,
			WebContext webContext) throws Exception {
		if (ImplicitActionName.Edit.equals(actionName)) {
			bean.setTotalStaffCount(bean.getAllStaffCount());
			bean.setInOfficeCount(bean.getAllStaffInOfficeCount());
			bean.getNeverInOffice().addAll(bean.getAllStaffNeverInOffice());
		}
		return super.preExecute(actionName, bean, parentBean, webContext);
	}

	@Override
	public void preRerender(String source, OfficeExtension bean, WebContext webContext) throws Exception {

		bean.setTotalStaffCount(bean.getAllStaffCount());
		bean.setInOfficeCount(bean.getAllStaffInOfficeCount());
		bean.getNeverInOffice().clear();
		bean.getNeverInOffice().addAll(bean.getAllStaffNeverInOffice());
		
		super.preRerender(source, bean, webContext);
	}
	
}
