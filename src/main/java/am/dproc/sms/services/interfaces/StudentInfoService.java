package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.StudentInfo;

public interface StudentInfoService {

	public Integer addStudentInfo(StudentInfo studentInfo);

	public int[] addStudentInfos(List<StudentInfo> infos);

	public StudentInfo getStudentInfoByStudentId(Integer studentId);

	public Integer updateStudentInfo(StudentInfo studentInfo);

	public Integer deleteStudentInfo(Integer studentId);

}
