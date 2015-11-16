package test;

import endpoint.EventEndpoint;
import entity.Event;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import org.joda.time.DateTime;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.impl.translate.opt.joda.JodaTimeTranslators;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.util.Closeable;

public class EventEndpointTest {
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
    public void testGetAllEvents() {
    	EventEndpoint eventEndpoint = new EventEndpoint();
    	JodaTimeTranslators.add(ObjectifyService.factory());
    	ObjectifyService.register(Event.class);
    	
    	for(int i=1; i<=10; i++) {
    		Event event = new Event(
    				(long) 100+i,
    				(long) 100+2*i,
    				(long) 100+3*i,
    				"Event" + i,
    				"This is Event #" + i,
    				new DateTime(),
    				new DateTime(),
    				(long) 100+4*i
    				);
    		ofy().save().entity(event).now();
    	}
    	
    	for(int i=1; i<=10; i++) {
    		List<Event> allEvents = eventEndpoint.getAllEvents();
    		Assert.assertTrue(!allEvents.isEmpty());
    		Assert.assertTrue(allEvents.size() == 10);
    	}
    }
    
    
    @After
    public void tearDown() throws IOException {
        helper.tearDown();
        session.close();
        session = null;
    }
}
