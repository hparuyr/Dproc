package am.dproc.sms.modules;

import java.util.List;

public class Course {

	private Integer id;
	private String name;
	private String duration;
	private String description;
	private String location;
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

	// ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		final Course course = (Course) obj;
		return id == course.id && (name == course.name || (name != null && name.equals(course.getName())))
				&& (duration == course.duration || (duration != null && duration.equals(course.getDuration())))
				&& (description == course.description || (description != null && description.equals(course.getDescription())))
				&& (location == course.location || (location != null && location.equals(course.getLocation())))
				&& (listOfLessons == course.listOfLessons || (listOfLessons != null && listOfLessons.equals(course.getListOfLessons())));
	}

	// ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((listOfLessons == null) ? 0 : listOfLessons.hashCode());
		return result;
	}

}
