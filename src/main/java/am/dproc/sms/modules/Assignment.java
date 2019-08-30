package am.dproc.sms.modules;

public class Assignment {
	
	private Integer id;
	private Integer startedDate;
	//private String dateDue;
	private Integer deadLine;
	private Integer teacherIdGivenAsi;
	private String title;
	private String description;
	private Integer creationDate;
	private Integer changeDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTeacherIdGivenAsi() {
		return teacherIdGivenAsi;
	}
	public void setTeacherIdGivenAsi(Integer teacherIdGivenAsi) {
		this.teacherIdGivenAsi = teacherIdGivenAsi;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getStartedDate() {
		return startedDate;
	}
	public void setStartedDate(Integer startedDate) {
		this.startedDate = startedDate;
	}
	public Integer getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(Integer deadLine) {
		this.deadLine = deadLine;
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
		return "Assignment [id=" + id + ", startedDate=" + startedDate + ", deadLine=" + deadLine
				+ ", teacherIdGivenAsi=" + teacherIdGivenAsi + ", title=" + title + ", description=" + description
				+ ", creationDate=" + creationDate + ", changeDate=" + changeDate + "]";
	}
	

	
	
	

}
