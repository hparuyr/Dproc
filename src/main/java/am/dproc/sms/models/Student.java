package am.dproc.sms.models;

public class Student extends User{
	private Integer status;
	private StudentInfo studentInfo;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public StudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	@Override
	public String toString() {
		String student = super.toString();
		return student + "Student [status=" + status + ", studentInfo=" + studentInfo + "]";
	}
	
	
}
