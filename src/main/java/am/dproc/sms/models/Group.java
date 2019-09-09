package am.dproc.sms.models;

import java.util.Date;
import java.util.List;

public class Group {
	private Integer id;
	private String name;
	private Date creationDate;
	private Integer schoolId;
	private List<Student> listOfStudents;
	
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
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public List<Student> getListOfStudents() {
		return listOfStudents;
	}
	public void setListOfStudents(List<Student> listOfStudents) {
		this.listOfStudents = listOfStudents;
	}
}
