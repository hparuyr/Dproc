package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.StudentInfo;

public interface StudentInfoDAO {

	Integer addStudentInfo(StudentInfo studentInfo);

	int[] addStudentInfos(List<StudentInfo> infos);

	StudentInfo getStudentInfo(Integer studentId);

	Integer updateStudentInfoPassportId(Integer studentId, String passportId);

	Integer updateStudentInfoSocialCardId(Integer studentId, String socialCardId);

	Integer updateStudentInfoBirthDate(Integer studentId, Long birthDate);

	Integer updateStudentInfoImageUrl(Integer studentId, String imageUrl);

	Integer deleteStudentInfo(Integer studentId);

}