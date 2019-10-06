package am.dproc.sms.models;

public class TeacherInfo {

    private Integer passportId;
    private Integer socialCardId;
    private Long birthDate;
    private String imageUrl;
    private Long creationDate;
    private Integer teacherId;

    public Integer getPassportId() {
        return passportId;
    }

    public void setPassportId(Integer passportId) {
        this.passportId = passportId;
    }

    public Integer getSocialCardId() {
        return socialCardId;
    }

    public void setSocialCardId(Integer socialCardId) {
        this.socialCardId = socialCardId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
    }
}
