package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({UserEndpointTest.class,
	BuildingEndpointTest.class
	})
public class RunAllSuite {
}
