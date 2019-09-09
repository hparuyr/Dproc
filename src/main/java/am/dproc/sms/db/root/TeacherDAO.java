package am.dproc.sms.db.root;

import java.util.List;

import am.dproc.sms.models.Teacher;

public interface TeacherDAO {

	public Integer addTeacher(Teacher teacher);

	public Teacher getTeacher(Integer id);
	
	public List<Teacher> getTeachers();
	
	public List<Teacher> getTeachersBySchoolId(Integer schoolId);
	
	public Integer updateTeacherName(Integer id, String name);
	
	public Integer updateTeacherSurname(Integer id, String surname);
	
	public Integer updateTeacherEmail(Integer id, String email);
	
	public Integer updateTeacherPassword(Integer id, String password);
	
	public Integer updateTeacherSchoolId(Integer id, Integer schoolId);
	
	public Integer deleteTeacher(Integer id);
}
