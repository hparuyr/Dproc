package am.dproc.sms.db.interfaces;

import am.dproc.sms.models.TeacherInfo;

public interface TeacherInfoDAO {

	Integer addTeacherInfo(TeacherInfo teacherInfo);

	TeacherInfo getTeacherInfo(Integer teacherId);

	Integer updateTeacherInfoPassportId(Integer teacherId, String passportId);

	Integer updateTeacherInfoSocialCardId(Integer teacherId, String socialCardId);

	Integer updateTeacherInfoBirthDate(Integer teacherId, Long birthDate);

	Integer updateTeacherInfoImageUrl(Integer teacherId, String imageUrl);

	Integer deleteTeacherInfo(Integer teacherId);
}
