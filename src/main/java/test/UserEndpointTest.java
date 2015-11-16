package test;

import endpoint.UserEndpoint;
import entity.User;
import entity.User.Location;

import java.io.IOException;

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
 * Testing to see whether the UserEndpoint stuff works.
 */
public class UserEndpointTest {
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
    public void testAddUser() {
    	User user = new User("userId",
    			"name",
    			"gmailId",
    			new Location(12.0, 12.0)
    			);
    	UserEndpoint userEndpoint = new UserEndpoint();
    	
    	ObjectifyService.register(User.class);
    	
    	userEndpoint.createUser(user);
    	
    	User user2 = userEndpoint.getUser("userId");
    	Assert.assertNotNull(user2);
    	Assert.assertTrue(user2.equals(user));
    }
    
    @Test
    public void testDeleteUser() {
    	User user = new User("userId",
    			"name",
    			"gmailId",
    			new Location(12.0, 12.0)
    			);
    	UserEndpoint userEndpoint = new UserEndpoint();
    	
    	ObjectifyService.register(User.class);
    	
    	userEndpoint.createUser(user);
    	User user2 = userEndpoint.deleteUser("userId");
    	
    	Assert.assertTrue(user2.equals(user));
    	
    	User user3 = userEndpoint.getUser("userId");
    	
    	Assert.assertNull(user3);
    }
    
    @Test
    public void testUpdateUser() {
    	User user = new User("userId",
    			"name",
    			"gmailId",
    			new Location(12.0, 12.0)
    			);
    	UserEndpoint userEndpoint = new UserEndpoint();
    	
    	ObjectifyService.register(User.class);
    	
    	User user2 = new User(user.getUserId(),
    			"new name",
    			user.getGmailId(),
    			user.getLocation()
    			);
    	
    	User user3 = userEndpoint.updateUserInformation(user.getUserId(), user2);

    	Assert.assertTrue(user3.getName().equals("new name"));
    }
    
    @Test
    public void testUpdateUserLocation() {
    	User user = new User("userId",
    			"name",
    			"gmailId",
    			new Location(12.0, 12.0)
    			);
    	UserEndpoint userEndpoint = new UserEndpoint();
    	
    	ObjectifyService.register(User.class);
    	
    	Location newLocation = new Location (42.0, 42.0);
    	
    	User user2 = new User(user.getUserId(),
    			user.getName(),
    			user.getGmailId(),
    			newLocation
    			);
    	
    	User user3 = userEndpoint.updateUserLocation(user.getUserId(), user2);
    	
    	Assert.assertTrue(user3.getLocation().latitude == 42.0 &&
    			user3.getLocation().longitude == 42.0);
    }
    
    @After
    public void tearDown() throws IOException {
        helper.tearDown();
        session.close();
        session = null;
    }
}
