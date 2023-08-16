package modules.orgManager.Position;

import org.skyve.util.DataBuilder;
import org.skyve.util.test.SkyveFactory;
import org.skyve.util.test.SkyveFixture;

@SkyveFactory
public class PositionFactory {
	@SkyveFixture(
			types = SkyveFixture.FixtureType.crud
	)
	public PositionExtension crudInstance() {
		return new DataBuilder().fixture(SkyveFixture.FixtureType.crud).factoryBuild(PositionExtension.MODULE_NAME, PositionExtension.DOCUMENT_NAME);
	}
}
