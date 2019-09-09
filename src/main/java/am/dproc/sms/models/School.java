package am.dproc.sms.models;

import java.util.List;

public class School {
	private Integer id;
	private String name;
	private String address;
	private Long creationDate;
	private List<Group> listOfGroups;
	private List<Admin> listOfAdmins;
	private List<Teacher> listOfTeachers;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}
	public List<Group> getListOfGroups() {
		return listOfGroups;
	}
	public void setListOfGroups(List<Group> listOfGroups) {
		this.listOfGroups = listOfGroups;
	}
	public List<Admin> getListOfAdmins() {
		return listOfAdmins;
	}
	public void setListOfAdmins(List<Admin> listOfAdmins) {
		this.listOfAdmins = listOfAdmins;
	}
	public List<Teacher> getListOfTeachers() {
		return listOfTeachers;
	}
	public void setListOfTeachers(List<Teacher> listOfTeachers) {
		this.listOfTeachers = listOfTeachers;
	}
}
