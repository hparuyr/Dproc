package am.dproc.sms.models;

public class UserInfo {
	private Integer userId;
	private String passportId;
	private String socialCardId;
	private Long birthDate;
	private String phoneNumber;
	private String address	;
	private String imageUrl;
	private Integer gender;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
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
	public Long getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Long birthDate) {
		this.birthDate = birthDate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", passportId=" + passportId + ", socialCardId=" + socialCardId
				+ ", birthDate=" + birthDate + ", phoneNumber=" + phoneNumber + ", address=" + address + ", imageUrl="
				+ imageUrl + ", gender=" + gender + "]";
	}
	
	
}
