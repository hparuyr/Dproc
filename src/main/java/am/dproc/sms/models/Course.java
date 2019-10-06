package am.dproc.sms.models;

import java.util.List;

public class Course {

	private Integer id;
	private Integer schoolID;
	private String name;
	private String description;
	private Integer duration;
	private String durationUnitType;
	private List<Lesson> listOfLessons;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSchoolID() {
		return schoolID;
	}

	public void setSchoolID(Integer schoolID) {
		this.schoolID = schoolID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getDurationUnitType() {
		return durationUnitType;
	}

	public void setDurationUnitType(String durationUnitType) {
		this.durationUnitType = durationUnitType;
	}

	public List<Lesson> getListOfLessons() {
		return listOfLessons;
	}

	public void setListOfLessons(List<Lesson> listOfLessons) {
		this.listOfLessons = listOfLessons;
	}

}
