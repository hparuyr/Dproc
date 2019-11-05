package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Student;

public interface StudentDAO {

	Integer addStudent(Student student);

	int[] addStudents(List<Student> students);

	Student getStudent(Integer id);

	Student getStudentByEmail(String email);

	List<Student> getStudents();

	List<Student> getStudentsByGroupId(Integer groupId);

	String getStudentStatus(Integer id);

	Integer updateStudent(Student student);

	Integer updateStudentFirstName(Integer id, String firstName);

	Integer updateStudentLastName(Integer id, String lastName);

	Integer updateStudentEmail(Integer id, String email);

	Integer updateStudentPassword(Integer id, String password);

	public Integer updateStudentStatus(Integer id, int status);

	public Integer deleteStudent(Integer id);
}
