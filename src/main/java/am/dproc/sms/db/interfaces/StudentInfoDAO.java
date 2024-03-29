package am.dproc.sms.db.interfaces;

import am.dproc.sms.models.StudentInfo;

public interface StudentInfoDAO {

	public Integer addStudentInfo(StudentInfo studentInfo);

	public StudentInfo getStudentInfo(Integer studentId);

	public Integer updateStudentInfoPassportId(Integer studentId, Integer passportId);

	public Integer updateStudentInfoSocialCardId(Integer studentId, Integer socialCardId);

	public Integer updateStudentInfoBirthDate(Integer studentId, Long birthDate);

	public Integer updateStudentInfoImageUrl(Integer studentId, String imageUrl);

	public Integer deleteStudentInfo(Integer studentId);

}