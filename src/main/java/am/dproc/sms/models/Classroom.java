package am.dproc.sms.models;

public class Classroom {

	private Integer id;
	private Integer number;
	private Integer capacity;
	private String type;
	private String subject;
	private Long creationDateMillis;
	private Integer schoolID;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Long getCreationDate() {
		return creationDateMillis;
	}

	public void setCreationDate(Long creationDateMillis) {
		this.creationDateMillis = creationDateMillis;
	}

	public Integer getSchoolID() {
		return schoolID;
	}

	public void setSchoolID(Integer schoolID) {
		this.schoolID = schoolID;
	}

}
