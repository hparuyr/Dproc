package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Teacher;

public interface TeacherDAO {

	Integer addTeacher(Teacher teacher);

	int[] addTeachers(List<Teacher> teachers);

	Teacher getTeacher(Integer id);

	Teacher getTeacherByEmail(String email);

	List<Teacher> getTeachers();

	List<Teacher> getTeachersBySchoolId(Integer schoolId);

	Integer updateTeacher(Teacher teacher);

	Integer updateTeacherFirstame(Integer id, String name);

	Integer updateTeacherLastname(Integer id, String surname);

	Integer updateTeacherEmail(Integer id, String email);

	Integer updateTeacherPassword(Integer id, String password);

	//	public Integer updateTeacherSchoolId(Integer id, Integer schoolId);

	Integer deleteTeacher(Integer id);
}
