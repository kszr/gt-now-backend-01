package entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import object.Location;

/**
 * A class representing a Building entity.
 */
@Entity
public class Building {
	@Id
	private Long buildingId;
	private String name;
	private String address;
	private Location location;
	
	private Building() {
		this.buildingId = 0L;
		this.name = null;
		this.address = null;
		this.location = null;		
	}
	
	@JsonCreator
    public Building(
        @JsonProperty("BuildingId") Long buildingId,
        @JsonProperty("Name") String name,
        @JsonProperty("Address") String address,
        @JsonProperty("Location") Location location
    ) {
        this.buildingId = buildingId;
        this.name = name;
        this.address = address;
        this.location = location;
    }
    
    public Long getBuildingId() {
    	return this.buildingId;
    }
    
    public void setBuildingId(Long buildingId) {
    	this.buildingId = buildingId;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getAddress() {
    	return this.address;
    }
    
    public void setAddress(String address) {
    	this.address = address;
    }
    
    public Location getLocation() {
    	return this.location;
    }
    
    public void setLocation(Location location) {
    	this.location = location;
    }
}
