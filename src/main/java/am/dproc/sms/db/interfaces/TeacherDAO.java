package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Teacher;

public interface TeacherDAO {

	public Integer addTeacher(Teacher teacher);

	public int[] addTeachers(List<Teacher> teachers);

	public Teacher getTeacher(Integer id);

	public Teacher getTeacherByEmail(String email);

	public List<Teacher> getTeachers();

	public List<Teacher> getTeachersBySchoolId(Integer schoolId);

	public Integer updateTeacher(Teacher teacher);

	public Integer updateTeacherFirstame(Integer id, String name);

	public Integer updateTeacherLastname(Integer id, String surname);

	public Integer updateTeacherEmail(Integer id, String email);

	public Integer updateTeacherPassword(Integer id, String password);

//	public Integer updateTeacherSchoolId(Integer id, Integer schoolId);

	public Integer deleteTeacher(Integer id);
}
