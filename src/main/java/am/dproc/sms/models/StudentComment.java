package am.dproc.sms.models;

public class StudentComment {

	private Integer id;
	private String comment;
	private Long creationDateMillis;
	private Integer topicID;
	private Integer studentID;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public Long getCreationDateMillis() {
		return creationDateMillis;
	}

	public void setCreationDateMillis(Long creationDateMillis) {
		this.creationDateMillis = creationDateMillis;
	}
}
