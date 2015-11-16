package endpoint;

import entity.Building;
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
 * Endpoints for the Building entity.
 */
@Path("/api/buildings")
public class BuildingEndpoint {
	/**
	 * Gets a List of all Buildings in the datastore.
	 * @return	A List of all Buildings in the datastore. 
	 */
	@GET
	public List<Building> getAllBuildings() {
		return ofy().load().type(Building.class).list();
	}
	
	/**
	 * Gets a Building by buildingId.
	 * @param 	buildingId	The id of the Building to be retrieved.
	 * @return				The Building that was retrieved.
	 */
	@GET
	@Path("{buildingId}")
	public Building getBuilding(@PathParam("buildingId") Long buildingId) {
		return ofy().load().type(Building.class).id(buildingId).now();
	}
}
