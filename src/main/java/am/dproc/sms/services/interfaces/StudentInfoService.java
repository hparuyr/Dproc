package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.StudentInfo;

public interface StudentInfoService {

	Integer addStudentInfo(StudentInfo studentInfo);

	int[] addStudentInfos(List<StudentInfo> infos);

	StudentInfo getStudentInfoByStudentId(Integer studentId);

	Integer updateStudentInfo(StudentInfo studentInfo);

	Integer deleteStudentInfo(Integer studentId);

}
