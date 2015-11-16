package test;

import endpoint.InvitationEndpoint;
import entity.Invitation;
import entity.Group;
import entity.User;
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

public class InvitationEndpointTest {
	private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig(),
                    new LocalMemcacheServiceTestConfig());

    Closeable session;
    
    @Before
    public void setup() {
        helper.setUp();
        session = ObjectifyService.begin();
    }
    
    /**
     * @TODO: Write the test...
     */
    @Test
    public void testCreateInvitation() {
    	Assert.assertTrue(true);
    }
    
    @After
    public void tearDown() throws IOException {
        helper.tearDown();
        session.close();
        session = null;
    }
}
