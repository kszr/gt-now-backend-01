package endpoint;

import entity.User;
import object.Location;

import com.googlecode.objectify.Key;
import static com.googlecode.objectify.ObjectifyService.ofy;

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
 * Endpoints for the User entity.
 */
@Path("/user")
public class UserEndpoint {
    /**
     * Add a new User object to the datastore.
     * @param   user     The User object that is to be added to the datastore.
     * @return           The User that has just been added.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(User user) {
        Key<User> key = ofy().save().entity(user).now();
        return ofy().load().key(key).now();
    }

    /**
     * Retrieve a User (if it exists) from the datastore by userId.
     * @param   userId      The userId of the User whose information is to be retrieved.
     * @return              The User that is to be retrieved.
     */
    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("userId") Long userId) {
        return ofy().load().type(User.class).id(userId).now();
    }

    /**
     * Update a User's information.
     * @param   userId      The userId of the User being updated.
     * @param   user        The User being updated.
     * @return              The updated User.
     *
     * @TODO				Update timestamp when this method is called.
     */
    @PUT
    @Path("{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUserInformation(@PathParam("userId") Long userId, User user) {
        Key<User> key = ofy().save().entity(user).now();
        return ofy().load().key(key).now();
    }

    /**
     * Delete a User from the datastore.
     * @param   userId      The userId of the User that is to be deleted.
     * @return              The User that has just been deleted.
     */
    @DELETE
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User deleteUser(@PathParam("userId") Long userId) {
    	User user = ofy().load().type(User.class).id(userId).now();
        ofy().delete().entity(user).now();
        return user;
    }

    /**
     * Get a User's Location.
     * @param   userId      The userId of the User whose Location is to be retrieved.
     * @return              The Location of the user.
     */
    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Location getUserLocation(@PathParam("userId") Long userId) {
        User user = ofy().load().type(User.class).id(userId).now();
        return user.getLocation();
    }

    /**
     * Update a User's Location.
     * @param   userId      The userId of the User whose Location is to be updated.
     * @param   user        The User with (presumably) updated Location.
     * @return              The updated User.
     * 
     * @TODO				Update timestamp when this method is called.
     */
    @PUT
    @Path("{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUserLocation(@PathParam("userId") Long userId, User user) {
        Key<User> key = ofy().save().entity(user).now();
        return ofy().load().key(key).now();
    }
}