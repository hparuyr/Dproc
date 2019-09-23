package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Student;

public interface StudentDAO {

	public Integer addStudent(Student student);

	public Student getStudent(Integer id);

	public Student getStudentByEmail(String email);

	public List<Student> getStudents();

	public List<Student> getStudentsByGroupId(Integer groupId);

	public String getStudentStatus(Integer id);

	public Integer updateStudentName(Integer id, String name);

	public Integer updateStudentSurname(Integer id, String surname);

	public Integer updateStudentEmail(Integer id, String email);

	public Integer updateStudentPassword(Integer id, String password);

	public Integer updateStudentStatus(Integer id, String status);

	public Integer updateStudentGroupId(Integer id, Integer groupId);

	public Integer deleteStudent(Integer id);

	public int[] addStudents(List<Student> students);
}
