package am.dproc.sms.models;

public class Assessment {

	private Integer id;
	private String title;
	private Integer score;
	private Integer userId;
	private Integer assignmentId;
	private Integer creationDate;
	private Integer changeDate;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Integer assignmentId) {
		this.assignmentId = assignmentId;
	}

	public Integer getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Integer creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Integer changeDate) {
		this.changeDate = changeDate;
	}

	@Override
	public String toString() {
		return "Assessment [id=" + id + ", title=" + title + ", score=" + score + ", userId=" + userId
				+ ", assignmentId=" + assignmentId + ", creationDate=" + creationDate + ", changeDate=" + changeDate
				+ "]";
	}

	

}
