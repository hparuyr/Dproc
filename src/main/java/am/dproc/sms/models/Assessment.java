package am.dproc.sms.models;

public class Assessment {

	private Integer id;
	private Integer score;
	private Integer userId;
	private Integer assignmentId;
	private Long creationDate;
	private Long changeDate;

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

	public Long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}

	public Long getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Long changeDate) {
		this.changeDate = changeDate;
	}

	@Override
	public String toString() {
		return "Assessment [id=" + id + "score=" + score + ", userId=" + userId
				+ ", assignmentId=" + assignmentId + ", creationDate=" + creationDate + ", changeDate=" + changeDate
				+ "]";
	}

}
