package entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import object.Location;

/**
 * A class representing a User entity.
 */
@Entity
public class User {
    @Id
    private Long userId;
    private String name;
    private String gmailId;
    private Location location;
    private String timestamp; //Location was last updated at this time.
 
    private User() {
        this.userId = 0L;
        this.name = null;
        this.gmailId = null;
        this.location = null;
        this.timestamp = null;
    }

    @JsonCreator
    public User(
        @JsonProperty("UserId") Long userId,
        @JsonProperty("Name") String name,
        @JsonProperty("GmailId") String gmailId,
        @JsonProperty("Location") Location location
    ) {
        this.userId = userId;
        this.name = name;
        this.gmailId = gmailId;
        this.location = location;
    }
    
    public Long getUserId() {
    	return this.userId;
    }
    
    public void setUserId(Long userId) {
    	this.userId = userId;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getGmailId() {
    	return this.gmailId;
    }
    
    public void setGmailId(String gmailId) {
    	this.gmailId = gmailId;
    }
    
    public Location getLocation() {
    	return this.location;
    }
    
    public void setLocation(double lat, double lon) {
    	this.location = new Location(lat, lon);
    }
    
    public String getTimestamp() {
    	return this.timestamp;
    }
    
    public void setTimestamp(String timestamp) {
    	this.timestamp = timestamp;
    }
}
