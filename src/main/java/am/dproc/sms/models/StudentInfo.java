package am.dproc.sms.models;

public class StudentInfo {
	private Integer passportId;
	private Integer socialCardId;
	private Long birthDate;
	private String imageUrl;
	private Long creationDate;
	private Integer studentId;

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

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Long getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Long birthDate) {
		this.birthDate = birthDate;
	}

}
