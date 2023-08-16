package modules.orgManager.Position;

import org.skyve.metadata.model.document.Bizlet;

public class PositionBizlet extends Bizlet<PositionExtension> {
	
	

	@Override
	public void postLoad(PositionExtension bean) throws Exception {
		bean.setReportsTo(bean.getParent());
		super.postLoad(bean);
	}

	@Override
	public void preSave(PositionExtension bean) throws Exception {
		if (bean.getReportsTo() != null) {
			bean.setBizParentId(bean.getReportsTo().getBizId());
		} else {
			bean.setBizParentId(null);
		}
		
		super.preSave(bean);
	}
	
	
	
}
