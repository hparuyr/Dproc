package am.dproc.sms.services.root;

import am.dproc.sms.models.StudentInfo;

public interface StudentInfoService {
	
	public Integer addStudentInfo(StudentInfo studentInfo);
	
	public StudentInfo getStudentInfoByStudentId(Integer studentId);
	
	public Integer updateStudentInfo(StudentInfo studentInfo);
	
	public Integer deleteStudentInfo(Integer studentId);

}
