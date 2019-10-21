package am.dproc.sms.models;

public class Teacher extends User{

	private TeacherInfo teacherInfo;

	public TeacherInfo getTeacherInfo() {
		return teacherInfo;
	}

    public void setTeacherInfo(TeacherInfo teacherInfo) {
        this.teacherInfo = teacherInfo;
    }
}
