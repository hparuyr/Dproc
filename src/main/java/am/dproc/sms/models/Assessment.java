package am.dproc.sms.models;

public class Assessment {

    private Integer id;
    private Integer score;
    private Integer userId;
    private Integer assignmentCompletedId;
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

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

    public Integer getAssignmentCompletedId() {
        return assignmentCompletedId;
    }

    public void setAssignmentCompletedId(Integer assignmentId) {
        this.assignmentCompletedId = assignmentId;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "id=" + id +
                ", score=" + score +
                ", userId=" + userId +
                ", assignmentCompletedId=" + assignmentCompletedId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
