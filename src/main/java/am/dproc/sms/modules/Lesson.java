package am.dproc.sms.modules;

import java.util.List;

public class Lesson {

	private Integer id;
	private String name;
	private String content;
	private Integer courseID;
	private List<Topic> listOfTopics;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCourseID() {
		return courseID;
	}

	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	public List<Topic> getListOfURLs() {
		return listOfTopics;
	}

	public void setListOfURLs(List<Topic> listOfURLs) {
		this.listOfTopics = listOfURLs;
	}
	
	@Override
	public String toString() {
		return "Lesson ["
				+ "ID : " + id
				+ " Name : " + name
				+ " Content : " + content
				+ "]";
	}

}
