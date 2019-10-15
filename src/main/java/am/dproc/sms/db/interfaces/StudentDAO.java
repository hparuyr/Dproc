package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Student;

public interface StudentDAO {

	public Integer addStudent(Student student);

	public int[] addStudents(List<Student> students);

	public Student getStudent(Integer id);

	public Student getStudentByEmail(String email);

	public List<Student> getStudents();

	public List<Student> getStudentsByGroupId(Integer groupId);

	public String getStudentStatus(Integer id);

	public Integer updateStudent(Student student);

	public Integer updateStudentFirstname(Integer id, String firstname);

	public Integer updateStudentLastname(Integer id, String lastname);

	public Integer updateStudentEmail(Integer id, String email);

	public Integer updateStudentPassword(Integer id, String password);

	public Integer updateStudentStatus(Integer id, int status);

	public Integer deleteStudent(Integer id);
}
