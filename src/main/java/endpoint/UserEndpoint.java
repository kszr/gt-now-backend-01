package endpoint;

import entity.User;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.cmd.Query;

import static OfyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;

@Api(name = "userEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "api.mindstorm.com", ownerName = "api.mindstorm.com", packagePath=""))
@Path("/api")
public class UserEndpoint {
    public UserEndpoint() {

    }

    /**
     * Add a new User object to the datastore.
     * @param   user     The User object that is to be added to the datastore.
     * @return           The User that has just been added.
     */
    //@ApiMethod(name = "createUser")
    @POST
    @Path("/user")
    public User createUser(User user) {
        //NOT CHECKING FOR CONFLICTS, because
        //load()'ing and then save()'ing objects
        //is central to updating with Objectify.

        // if (user.userId != null) {
        //     if (findRecord(user.userId) != null) {
        //         throw new ConflictException("Object already exists");
        //     }
        // }
        return ofy().save().entity(user).now();
    }

    /**
     * Retrieve a User (if it exists) from the datastore by userId.
     * @param   userId      The userId of the User whose information is to be retrieved.
     * @return              The User that is to be retrieved.
     */
    @GET
    @Path("/user/{userId}")
    public User getUser(String userId) {
        return ofy().load().type(User.class).id(userId).now();
    }

    /**
     * Update a User's information.
     * @TODO    Everything, please.
     * CONCEPTS: If you want to update a User's information, you have to
     * retrieve it from the datastore with load(), modify its information, and then
     * put the same object back into the datastore with save().
     */
    @PUT
    @Path("/user/{userId}")
    public User updateUserInformation() {
        return null;
    }

    /**
     * Delete a User from the datastore.
     * @param   userId      The userId of the User that is to be deleted.
     * @return              The User that has just been deleted.
     */
    @DELETE
    @Path("/user/{userId}")
    public User deleteUser(String userId) {
        return ofy().delete().type(User.class).id(userId).now();
    }

    /**
     * Get a User's Location.
     * @param   userId      The userId of the User whose Location is to be retrieved.
     * @return              The Location of the user.
     */
    @GET
    @Path("/user/{userId}")
    public Location getUserLocation(String userId) {
        User user = ofy().load().type(User.class).id(userId).now();
        return user.location;
    }

    /**
     * Update a User's Location.
     * @param   userId      The userId of the User whose Location is to be updated.
     * @param   location    The new Location of the User.
     * @return              The updated User.\
     *
     * @TODO                EVERYTHING.
     */
    @PUT
    @Path("/user/{userId}")
    public User updateUserLocation(String userId, Location location) {
        return null;
    }


// /**
// * Return a collection of users
// *
// * @param count The number of users
// * @return a list of Users
// */
// @ApiMethod(name = "listQuote")
// public CollectionResponse<Quote> listQuote(@Nullable @Named("cursor") String cursorString,
// @Nullable @Named("count") Integer count) {

// Query<Quote> query = ofy().load().type(Quote.class);
// if (count != null) query.limit(count);
// if (cursorString != null && cursorString != "") {
// query = query.startAt(Cursor.fromWebSafeString(cursorString));
// }

// List<Quote> records = new ArrayList<Quote>();
// QueryResultIterator<Quote> iterator = query.iterator();
// int num = 0;
// while (iterator.hasNext()) {
// records.add(iterator.next());
// if (count != null) {
// num++;
// if (num == count) break;
// }
// }

// //Find the next cursor
// if (cursorString != null && cursorString != "") {
// Cursor cursor = iterator.getCursor();
// if (cursor != null) {
// cursorString = cursor.toWebSafeString();
// }
// }
// return CollectionResponse.<Quote>builder().setItems(records).setNextPageToken(cursorString).build();
// }

// /**
// * This inserts a new <code>Quote</code> object.
// * @param quote The object to be added.
// * @return The object to be added.
// */

// /**
// * This updates an existing <code>Quote</code> object.
// * @param quote The object to be added.
// * @return The object to be updated.
// */
// @ApiMethod(name = "updateQuote")
// public Quote updateQuote(Quote quote)throws NotFoundException {
// if (findRecord(quote.getId()) == null) {
// throw new NotFoundException("Quote Record does not exist");
// }
// ofy().save().entity(quote).now();
// return quote;
// }

// /**
// * This deletes an existing <code>Quote</code> object.
// * @param id The id of the object to be deleted.
// */
// @ApiMethod(name = "removeQuote")
// public void removeQuote(@Named("id") Long id) throws NotFoundException {
// Quote record = findRecord(id);
// if(record == null) {
// throw new NotFoundException("Quote Record does not exist");
// }
// ofy().delete().entity(record).now();
// }

// //Private method to retrieve a <code>Quote</code> record
// private Quote findRecord(Long id) {
// return ofy().load().type(Quote.class).id(id).now();
// //or return ofy().load().type(Quote.class).filter("id",id).first.now();
// }

}