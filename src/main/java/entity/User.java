package main.java.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {
    @Id String userId;
    String name;
    String gmailId;
    Location location;
    String timestamp; //Location was last updated at this time.

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
}
