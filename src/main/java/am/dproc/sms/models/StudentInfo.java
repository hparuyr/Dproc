package am.dproc.sms.models;

public class StudentInfo {
	private String passportId;
	private String socialCardId;
	private Long birthDate;
	private String imageUrl;
	private Integer studentId;

	public String getPassportId() {
		return passportId;
	}

	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}

	public String getSocialCardId() {
		return socialCardId;
	}

	public void setSocialCardId(String socialCardId) {
		this.socialCardId = socialCardId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
