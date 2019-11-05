package am.dproc.sms.services.interfaces;

import am.dproc.sms.models.TeacherInfo;

public interface TeacherInfoService {

	Integer addTeacherInfo(TeacherInfo teacherInfo);

	TeacherInfo getTeacherInfoByTeacherId(Integer teacherId);

	Integer updateTeacherInfo(TeacherInfo teacherInfo);

	Integer deleteTeacherInfo(Integer teacherId);

}
