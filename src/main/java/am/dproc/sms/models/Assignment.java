package am.dproc.sms.models;

public class Assignment {

    private Integer id;
    private Long startDate;
    private Long endDate;
    private String title;
    private String description;
    private Integer teacherId;
    private Integer lessonId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startedDate) {
        this.startDate = startedDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long deadLine) {
        this.endDate = deadLine;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", teacherId=" + teacherId +
                ", lessonId=" + lessonId +
                '}';
    }
}
