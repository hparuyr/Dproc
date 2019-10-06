package am.dproc.sms.models;

public class Topic {

	private Integer id;
	private Integer lessonID;
	private String videoURL;
	private String webPageURL;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLessonID() {
		return lessonID;
	}

	public void setLessonID(Integer lessonID) {
		this.lessonID = lessonID;
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
}
