package entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

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

    }

    public User(String userId,
                String name,
                String gmailId,
                Location location,
                String timestamp) {
        this.userId = userId;
        this.name = name;
        this.gmailId = gmailId;
        this.location = location;
        this.timestamp = timestamp;
    }
}
