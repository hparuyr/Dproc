package am.dproc.sms.models;

import java.util.Date;

public class ScheduleRecord {

    private Integer id;
    private Integer groupCourseId;
    private Integer classroomId;
    private Integer lessonId;
    private Date startDate;
    private Date endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupCourseId() {
        return groupCourseId;
    }

    public void setGroupCourseId(Integer groupCourseId) {
        this.groupCourseId = groupCourseId;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
