package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Student;

public interface StudentService {

	public Integer addStudent(Student student);

	public Student getStudent(Integer id);

	public Student getStudentByEmail(String email);

	public List<Student> getGroupStudents(Integer groupId);

	public List<Student> getStudents();

	public Integer updateStudent(Student student);

	public Integer deleteStudent(Integer id);
}
