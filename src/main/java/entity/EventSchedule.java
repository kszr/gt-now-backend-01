package entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

/**
 * A class representing an EventSchedule entity.
 */
@Entity
public class EventSchedule {
	@Id
	private Long eventScheduleId;
	private Long userId; //The user to whom this EventSchedule belongs.
	private String name; //Name of the EventSchedule.
	private String description;
	private String daysOfWeek; //Comma-separated list
	private DateTime startTime; //Time and date of first event.
	private DateTime endTime; //Time and date of last event.
	private Long buildingId;
	private DateTime endDate; //The date on which this recurring event ends.
	
	private EventSchedule() {
		this.eventScheduleId = 0L;
		this.userId = 0L;
		this.name = null;
		this.description = null;
		this.daysOfWeek = null;
		this.startTime = null;
		this.endTime = null;
		this.buildingId = 0L;
		this.endDate = null;
	}
	
	@JsonCreator
	public EventSchedule(
			@JsonProperty("EventScheduleId") Long eventScheduleId,
			@JsonProperty("UserId") Long userId,
			@JsonProperty("Name") String name,
			@JsonProperty("Description") String description,
			@JsonProperty("DaysOfWeek") String daysOfWeek,
			@JsonProperty("StartTime") DateTime startTime,
			@JsonProperty("EndTime") DateTime endTime,
			@JsonProperty("BuildingId") Long buildingId,
			@JsonProperty("EndDate") DateTime endDate
			) {
		this.eventScheduleId = eventScheduleId;
		this.userId = userId;
		this.name = name;
		this.description = description;
		this.daysOfWeek = daysOfWeek;
		this.startTime = startTime;
		this.endTime = endTime;
		this.buildingId = buildingId;
		this.endDate = endDate;
	}
	
	public Long getEventScheduleId() {
		return this.eventScheduleId;
	}
	
	public void setEventScheduleId(Long eventScheduleId) {
		this.eventScheduleId = eventScheduleId;
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
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDaysOfWeek() {
		return this.daysOfWeek;
	}
	
	public void setDaysOfWeek(String daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}
	
	public DateTime getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}
	
	public DateTime getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(DateTime endTime) {
		this.endTime = endTime;
	}
	
	public Long getBuildingId() {
		return this.buildingId;
	}
	
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
	
	public DateTime getEndDate() {
		return this.endDate;
	}
	
	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}
}
