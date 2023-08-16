package modules.orgManager.Staff.actions;

import org.skyve.metadata.controller.ServerSideAction;
import org.skyve.metadata.controller.ServerSideActionResult;
import org.skyve.web.WebContext;

import modules.orgManager.Staff.StaffExtension;

public class SendHome implements ServerSideAction<StaffExtension> {

	@Override
	public ServerSideActionResult<StaffExtension> execute(StaffExtension bean, WebContext webContext) throws Exception {
		bean.home();
		return new ServerSideActionResult<>(bean);
	}

}
