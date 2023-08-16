package modules.orgManager.Staff;

import java.util.Objects;

import org.skyve.CORE;
import org.skyve.domain.Bean;
import org.skyve.domain.messages.Message;
import org.skyve.domain.messages.ValidationException;
import org.skyve.domain.types.DateOnly;
import org.skyve.domain.types.Timestamp;
import org.skyve.metadata.controller.ImplicitActionName;
import org.skyve.metadata.model.document.Bizlet;
import org.skyve.web.WebContext;

import modules.orgManager.Position.PositionExtension;
import modules.orgManager.domain.Position;
import modules.orgManager.domain.Staff;
import modules.orgManager.domain.StaffStatusHistory;

public class StaffBizlet extends Bizlet<StaffExtension> {
	
	@Override
	public void validate(StaffExtension bean, ValidationException e) throws Exception {
		DateOnly now = new DateOnly();
		if (!Objects.isNull(bean.getDateOfBirth()) && bean.getDateOfBirth().after(now)) {
			e.getMessages().add(new Message(Staff.dateOfBirthPropertyName, "Date of birth can not be after today"));
		}
	}
	
	@Override
	public void preRerender(String source, StaffExtension bean, WebContext webContext) throws Exception {
		if (source.equals(Staff.dateOfBirthPropertyName)) {
			bean.calculateAgeInYears();
		}
		if (source.equals(Staff.statusPropertyName)) {
			StaffStatusHistory ssh = StaffStatusHistory.newInstance();
			ssh.setStatus(bean.getStatus());
			ssh.setTime(new Timestamp());
			bean.addStatusHistoryElement(0, ssh);
			
			if (bean.getStatus().equals(Staff.Status.in)) {
				bean.home();
			}
		}
		
		super.preRerender(source, bean, webContext);
		
	}
	
	public void preSave(StaffExtension bean) throws Exception {
		// Set code to format "S###"
		if (!bean.isPersisted()) {
			String s = CORE.getNumberGenerator().next("S", Staff.MODULE_NAME, Staff.DOCUMENT_NAME, Staff.codePropertyName, 4);
			bean.setCode(s);
		}	
	}
	
	@Override
	public StaffExtension preExecute(ImplicitActionName actionName, StaffExtension bean, Bean parentBean,
			WebContext webContext) throws Exception {
		if (ImplicitActionName.Edit.equals(actionName)) {
			bean.calculateAgeInYears();
		}
		if (ImplicitActionName.Save.equals(actionName)
				|| ImplicitActionName.OK.equals(actionName)) {
			// Make the corresponding change in org structure
			PositionExtension pe = StaffExtension.getPosition(bean);
			
			if (pe == null) {
				String posTitle = bean.getPositionTitle();
				Position pos = Position.newInstance();
				pos.setPositionTitle(posTitle);
				pos.setStaff(bean);
				pos.setReportsTo(bean.getReportsTo());
				CORE.getPersistence().save(pos);
			} else {
				pe.setReportsTo(bean.getReportsTo());
				pe.setPositionTitle(bean.getPositionTitle());
			}
		}
		return super.preExecute(actionName, bean, parentBean, webContext);
	}

	@Override
	public void postLoad(StaffExtension bean) throws Exception {
		
		PositionExtension pe = StaffExtension.getPosition(bean);
		if (pe != null) {
			bean.setReportsTo(pe.getReportsTo());
			bean.setPositionTitle(pe.getPositionTitle());
		}
		
		super.postLoad(bean);
	}
	
	
	
	
	
	

}
