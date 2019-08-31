package am.dproc.sms.modules;

public class Topic {

	private Integer id;
	private String videoURL;
	private String webPageURL;
	private Integer lessonID;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	public String getWebPageURL() {
		return webPageURL;
	}

	public void setWebPageURL(String webPageURL) {
		this.webPageURL = webPageURL;
	}

	public Integer getLessonID() {
		return lessonID;
	}

	public void setLessonID(Integer lessonID) {
		this.lessonID = lessonID;
	}

	@Override
	public String toString() {
		return "Url ["
				+ "ID : " + id
				+ " Video Url : " + videoURL
				+ " Web Page Url : " + webPageURL
				+ "]";
	}

}
