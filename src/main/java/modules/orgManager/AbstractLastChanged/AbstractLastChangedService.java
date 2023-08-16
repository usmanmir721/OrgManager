package modules.orgManager.AbstractLastChanged;

import java.lang.String;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import org.skyve.persistence.DocumentQuery;
import org.skyve.persistence.Persistence;

/**
 * This class acts as a service layer to encapsulate domain logic.
 *
 * Add this line to classes that wish to use it: @Inject private transient AbstractLastChangedService service;
 */
@Default
public class AbstractLastChangedService {
	@Inject
	private Persistence persistence;

	public AbstractLastChangedExtension get(String bizId) {
		final DocumentQuery query = persistence.newDocumentQuery(AbstractLastChangedExtension.MODULE_NAME, AbstractLastChangedExtension.DOCUMENT_NAME);
		query.getFilter().addEquals(AbstractLastChangedExtension.DOCUMENT_ID, bizId);
		return query.beanResult();
	}

	public List<AbstractLastChangedExtension> getAll() {
		final DocumentQuery query = persistence.newDocumentQuery(AbstractLastChangedExtension.MODULE_NAME, AbstractLastChangedExtension.DOCUMENT_NAME);
		return query.beanResults();
	}
}
