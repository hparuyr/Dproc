package am.dproc.sms.services.interfaces;

import am.dproc.sms.models.TeacherInfo;

public interface TeacherInfoService {
	
public Integer addTeacherInfo(TeacherInfo teacherInfo);
	
	public TeacherInfo getTeacherInfoByTeacherId(Integer teacherId);
	
	public Integer updateTeacherInfo(TeacherInfo teacherInfo);
	
	public Integer deleteTeacherInfo(Integer teacherId);

}
