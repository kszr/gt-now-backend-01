package endpoint;

import entity.EventSchedule;
import entity.Group;
import entity.Invitation;
import entity.User;
import object.Location;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.cmd.Query;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Endpoints for EventSchedule.
 * 
 * @TODO Filters.
 */
@Path("api/event-schedules")
public class EventScheduleEndpoint {
	/**
	 * Gets All schedules.
	 * @return	A List of all EventSchedules.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EventSchedule> getAllSchedules() {
		return ofy().load().type(EventSchedule.class).list();
	}
	
	/**
	 * Retrieves an EventSchedule by id.
	 * @param  eventScheduleId	The id of the EventSchedule to be retrieved.
	 * @return 					The retrieved EventSchedule.
	 */
	@GET
	@Path("{eventScheduleId}")
	@Produces(MediaType.APPLICATION_JSON)
	public EventSchedule getScheduleById(@PathParam("eventScheduleId") Long eventScheduleId) {
		return ofy().load().type(EventSchedule.class).id(eventScheduleId).now();
	}
	
	/**
	 * Updates an EventSchedule
	 * @param eventScheduleId	The id of the EventSchedule to be updated.
	 * @param eventSchedule		The new EventSchedule.
	 * @return					The updated EventSchedule.
	 */
	@PUT
	@Path("{eventScheduleId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public EventSchedule updateSchedule(@PathParam("eventScheduleId") Long eventScheduleId,
			EventSchedule eventSchedule) {
		Key<EventSchedule> key = ofy().save().entity(eventSchedule).now();
        return ofy().load().key(key).now();
	}
	
	/**
	 * Deletes an EventSchedule
	 * @param eventScheduleId	The id of the EventSchedule to be deleted.
	 * @return					The EventSchedule that has just been deleted.
	 */
	@DELETE
	@Path("{eventScheduleId}")
	@Produces(MediaType.APPLICATION_JSON)
	public EventSchedule deleteSchedule(@PathParam("eventScheduleId") Long eventScheduleId) {
		EventSchedule eventSchedule = ofy().load().type(EventSchedule.class).id(eventScheduleId).now();
        ofy().delete().entity(eventSchedule).now();
        return eventSchedule;
	}
}
