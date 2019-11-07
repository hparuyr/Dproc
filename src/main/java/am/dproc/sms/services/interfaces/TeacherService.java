package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Teacher;

public interface TeacherService {

	public Integer addTeacher(Teacher teacher);

	public Teacher getTeacher(Integer id);

	public List<Teacher> getSchoolTeachers(Integer schoolId);

	public List<Teacher> getTeachers();
	
	public Integer updateTeacher(Teacher teacher);

	public Integer deleteTeacher(Integer id);
}
