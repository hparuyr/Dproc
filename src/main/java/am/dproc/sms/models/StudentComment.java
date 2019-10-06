package am.dproc.sms.models;

public class StudentComment {

	private Integer id;
	private Integer topicID;
	private Integer studentID;
	private String comment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTopicID() {
		return topicID;
	}

	public void setTopicID(Integer topicID) {
		this.topicID = topicID;
	}

	public Integer getStudentID() {
		return studentID;
	}

	public void setStudentID(Integer studentID) {
		this.studentID = studentID;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
