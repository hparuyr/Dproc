package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Teacher;

public interface TeacherService {

	Integer addTeacher(Teacher teacher);

	Teacher getTeacher(Integer id);

	List<Teacher> getSchoolTeachers(Integer schoolId);

	List<Teacher> getTeachers();
	
	Integer updateTeacher(Teacher teacher);

	Integer deleteTeacher(Integer id);
}
