package endpoint;

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
 * Endpoints for the Group entity.
 */
@Path("/api/group")
public class GroupEndpoint {
    /**
     * Add a new Group object to the datastore.
     * @param  group	The Group object that is to be added to the datastore.
     * @return          The Group that has just been added.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Group createUser(Group group) {
        Key<Group> key = ofy().save().entity(group).now();
        return ofy().load().key(key).now();
    }
    
    /**
     * Gets a Group by groupId from the datastore.
     * @param  groupId	The groupId of the Group to be retrieved.
     * @return			The requested Group.
     */
    @GET
    @Path("{groupId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Group getGroupInformation(@PathParam("groupId") Long groupId) {
    	return ofy().load().type(Group.class).id(groupId).now();
    }
    
    /**
     * Updates a Group's information
     * @param  groupId	The groupId of the Group being updated.
     * @param  group	The new Group.
     * @return			The updated Group.
     */
    @PUT
    @Path("{groupId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Group updateGroupInformation(@PathParam("groupId") Long groupId, Group group) {
        Key<Group> key = ofy().save().entity(group).now();
        return ofy().load().key(key).now();
    }
    
    /**
     * Gets all the members of a Group.
     * @param  groupId	The groupId of the Group being requested
     * @return			A List of all Users in that Group.
     * 
     * @TODO Everything, please. [Invitation]
     */
    @GET
    @Path("{groupId}/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllGroupMembers(@PathParam("groupId") Long groupId) {
    	return null;
    }
    
    /**
     * Deletes a User from a Group.
     * @param  groupId	The groupId of the Group being accessed.
     * @param  userId	The userId of the User being deleted.
     * @return			The deleted User.
     * 
     * @TODO Everything [Invitation].
     */
    @DELETE
    @Path("{groupId}/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User deleteGroupMember(@PathParam("groupId") Long groupId, @PathParam("userId") String userId) {
    	return null;
    }
    
    /**
     * Adds a User directly to a Group.
     * @param  groupId	The groupId of the Group being accessed.
     * @param  user		The User to be added to the Group.
     * @return			The User that has just been added.
     * 
     * @TODO			Find a way to add user directly to group... [Invitation]
     */
    @PUT
    @Path("{groupId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User addUserToGroup(@PathParam("groupId") Long groupId, User user) {
    	return null;
    }
}
