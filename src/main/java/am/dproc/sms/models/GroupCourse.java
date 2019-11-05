package am.dproc.sms.models;

public class GroupCourse {

    private Integer id;
    private Integer groupId;
    private Integer courseId;
    private Integer teacherId;
    private Long startDate;
    private Boolean isFinished;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    @Override
    public String toString() {
        return "GroupCourseBean [id=" + id + ", groupId=" + groupId + ", courseId=" + courseId + ", teacherId="
                + teacherId + ", startDate=" + startDate + ", isFinished=" + isFinished + "]";
    }

}
