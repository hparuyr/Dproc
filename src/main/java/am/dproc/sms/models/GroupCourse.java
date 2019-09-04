package am.dproc.sms.models;

public class GroupCourse {
	private int id;
	private int groupId;
	private int courseId;
	private int teacherId;
	private long startDate;
	private boolean isFinished;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setIsFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	@Override
	public String toString() {
		return "GroupCourseBean [id=" + id + ", groupId=" + groupId + ", courseId=" + courseId + ", teacherId="
				+ teacherId + ", startDate=" + startDate + ", isFinished=" + isFinished + "]";
	}

}
