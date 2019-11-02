package am.dproc.sms.db.interfaces;

import am.dproc.sms.models.TeacherInfo;

public interface TeacherInfoDAO {

	public Integer addTeacherInfo(TeacherInfo teacherInfo);

	public TeacherInfo getTeacherInfo(Integer teacherId);

	public Integer updateTeacherInfoPassportId(Integer teacherId, String passportId);

	public Integer updateTeacherInfoSocialCardId(Integer teacherId, String socialCardId);

	public Integer updateTeacherInfoBirthDate(Integer teacherId, Long birthDate);

	public Integer updateTeacherInfoImageUrl(Integer teacherId, String imageUrl);

	public Integer deleteTeacherInfo(Integer teacherId);
}
