package am.dproc.sms.models;

import java.util.Date;
import java.util.List;

public class Course {

	private Integer id;
	private String name;
	private String duration;
	private String description;
	private String location;
	private Date creationDate;
	private Date changeDate;
	private List<Lesson> listOfLessons;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Lesson> getListOfLessons() {
		return listOfLessons;
	}

	public void setListOfLessons(List<Lesson> listOfLessons) {
		this.listOfLessons = listOfLessons;
	}

	@Override
	public String toString() {
		return "Course [" + "ID : " + id + "\n Name : " + name + " Duration : " + duration + " Description : "
				+ description + " Location : " + location + "]";
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

}
