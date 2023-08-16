package modules.orgManager.AbstractLastChanged;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFactory;
import org.skyve.util.test.SkyveFixture;

@SkyveFactory
public class AbstractLastChangedFactory {
	@SkyveFixture(
			types = SkyveFixture.FixtureType.crud
	)
	public AbstractLastChangedExtension crudInstance() {
		return new DataBuilder().fixture(SkyveFixture.FixtureType.crud).factoryBuild(AbstractLastChangedExtension.MODULE_NAME, AbstractLastChangedExtension.DOCUMENT_NAME);
	}
}
