package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Student;

public interface StudentService {

	Integer addStudent(Student student);

	int[] addStudents(List<Student> students);

	Student getStudent(Integer id);

	Student getStudentByEmail(String email);

	List<Student> getGroupStudents(Integer groupId);

	List<Student> getStudents();

	Integer updateStudent(Student student);

	Integer deleteStudent(Integer id);
}
