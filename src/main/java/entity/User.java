package entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {
    @Id
    private String userId;
    private String name;
    private String gmailId;
    private Location location;
    private String timestamp; //Location was last updated at this time.

    public class Location {
        public double latitude;
        public double longitude;

        public Location(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }   

    private User() {
        this.userId = null;
        this.name = null;
        this.gmailId = null;
        this.location = null;
        this.timestamp = null;
    }

    @JsonCreator
    public User(
        @JsonProperty("UserId") String userId,
        @JsonProperty("Name") String name,
        @JsonProperty("GmailId") String gmailId,
        @JsonProperty("Location") Location location,
        @JsonProperty("Timestamp") String timestamp
    ) {
        this.userId = userId;
        this.name = name;
        this.gmailId = gmailId;
        this.location = location;
        this.timestamp = timestamp;
    }
    
    public String getUserId() {
    	return this.userId;
    }
    
    public void setUserId(String userId) {
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
}
