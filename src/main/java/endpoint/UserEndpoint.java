// import java.io.IOException;

// import javax.inject.Inject;
// import javax.servlet.http.HttpServletRequest;
// import javax.ws.rs.FormParam;
// import javax.ws.rs.GET;
// import javax.ws.rs.Path;
// import javax.ws.rs.Produces;
// import javax.ws.rs.core.MediaType;

// import com.googlecode.objectify.TxnType;

// @Path("/api")
// public class UserEndpoint {
//     @GET
//     @Path("/user/{userId}")
//     @Produces(MediaType.APPLICATION_JSON)
//     public User getUser(String userId) {
//         return ofy().load().type(User.class).id(userId).now();
//     }
// }
package com.mindstorm.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.cmd.Query;

import static com.mindstorm.api.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Named;

@Api(name = "userEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "api.mindstorm.com", ownerName = "api.mindstorm.com", packagePath=""))
public class UserEndpoint {

// Make sure to add this endpoint to your web.xml file if this is a web application.

    public UserEndpoint() {

    }

/**
* Return a collection of users
*
* @param count The number of users
* @return a list of Users
*/
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


    //@ApiMethod(name = "createUser")
    @POST
    @Path("/user")
    public User createUser(User user) throws ConflictException {
        //If if is not null, then check if it exists. If yes, throw an Exception
        //that it is already present
        if (user.userId != null) {
            if (findRecord(user.userId) != null) {
                throw new ConflictException("Object already exists");
            }
        }
        //Since our @Id field is a Long, Objectify will generate a unique value for us
        //when we use put
        ofy().save().entity(user).now();
        return user;
    }

    @GET
    @Path("/user/{userId}")
    public User getUser(String userId) throws ConflictException {
        //If if is not null, then check if it exists. If yes, throw an Exception
        //that it is already present
        // if (userId == null) {
        //     if (findRecord(user.userId) != null) {
        //         throw new ConflictException("Object already exists");
        //     }
        // }
        //Since our @Id field is a Long, Objectify will generate a unique value for us
        //when we use put
        return ofy().load().type(User.class).id(userId).now();
    }


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

//Private method to retrieve a <code>Quote</code> record
private Quote findRecord(Long id) {
return ofy().load().type(Quote.class).id(id).now();
//or return ofy().load().type(Quote.class).filter("id",id).first.now();
}

}