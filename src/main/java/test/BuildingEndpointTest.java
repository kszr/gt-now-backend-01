package test;

import endpoint.BuildingEndpoint;
import entity.Building;
import object.Location;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.util.Closeable;

/**
 * Testing endpoints for Building.
 */
public class BuildingEndpointTest {
	private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig(),
                    new LocalMemcacheServiceTestConfig());

    Closeable session;
    
    @Before
    public void setup() {
        helper.setUp();
        session = ObjectifyService.begin();
    }
    
    @Test
    public void testGetBuilding() {
    	BuildingEndpoint buildingEndpoint = new BuildingEndpoint();
    	ObjectifyService.register(Building.class);
    	
    	for(int i=1; i<=10; i++) {
    		ofy().save().entity(new Building((long) i, "building"+i, "address", new Location(i*1.0, i*1.0))).now();
    	}
    	
    	List<Building> buildingList = buildingEndpoint.getAllBuildings();
    	
    	Assert.assertTrue(!buildingList.isEmpty());
    	Assert.assertTrue(buildingList.size() == 10);
    }
    
    @Test
    public void testGetBuildingById() {
    	BuildingEndpoint buildingEndpoint = new BuildingEndpoint();
    	ObjectifyService.register(Building.class);
    	
    	for(int i=1; i<=10; i++) {
    		ofy().save().entity(new Building((long) i, "building"+i, "address", new Location(i*1.0, i*1.0))).now();
    	}
    	
    	for(int i=1; i<=10; i++) {
    		Assert.assertNotNull(buildingEndpoint.getBuilding((long) i));
    	}
    }
    
    @After
    public void tearDown() throws IOException {
        helper.tearDown();
        session.close();
        session = null;
    }
}
