package modules.orgManager.Position;

import java.lang.String;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import org.skyve.persistence.DocumentQuery;
import org.skyve.persistence.Persistence;

/**
 * This class acts as a service layer to encapsulate domain logic.
 *
 * Add this line to classes that wish to use it: @Inject private transient PositionService service;
 */
@Default
public class PositionService {
	@Inject
	private Persistence persistence;

	public PositionExtension get(String bizId) {
		final DocumentQuery query = persistence.newDocumentQuery(PositionExtension.MODULE_NAME, PositionExtension.DOCUMENT_NAME);
		query.getFilter().addEquals(PositionExtension.DOCUMENT_ID, bizId);
		return query.beanResult();
	}

	public List<PositionExtension> getAll() {
		final DocumentQuery query = persistence.newDocumentQuery(PositionExtension.MODULE_NAME, PositionExtension.DOCUMENT_NAME);
		return query.beanResults();
	}
}
