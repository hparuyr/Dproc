package am.dproc.sms.models;

import java.util.List;

public abstract class User extends Person {
	private List<Group> groups;
	private List<Course> courses;
	
	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "User [  getId()=" + getId() + ", getFirstname()="
				+ getFirstname() + ", getLastname()=" + getLastname() + ", getEmail()=" + getEmail()
				+ ", getPassword()=" + getPassword() + ", groups=" + groups + ", courses=" + courses + "]";
	}
	
	

	
}
