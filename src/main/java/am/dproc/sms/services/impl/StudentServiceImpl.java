package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.StudentDAO;
import am.dproc.sms.helpers.RandomPassGenerator;
import am.dproc.sms.models.Student;
import am.dproc.sms.models.StudentInfo;
import am.dproc.sms.models.StudentStatus;
import am.dproc.sms.services.interfaces.EmailService;
import am.dproc.sms.services.interfaces.GroupService;
import am.dproc.sms.services.interfaces.StudentInfoService;
import am.dproc.sms.services.interfaces.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDAO studentDao;
	@Autowired
	StudentInfoService studentInfo;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	EmailService emailService;
	@Autowired
	GroupService groupService;

	@Override
	public Integer addStudent(Student student) {
		Student existingStudent = studentDao.getStudentByEmail(student.getEmail());
		if (existingStudent != null) {
			return 0;
		}
		String randomPass = RandomPassGenerator.alphaNumericString(12);
		student.setPassword(passwordEncoder.encode(randomPass));
		student.setStatus(StudentStatus.PENDING.ordinal());
		int id = studentDao.addStudent(student);
		if (id > 0) {
			StudentInfo studentInfo = student.getStudentInfo();
			studentInfo.setUserId(id);
			this.studentInfo.addStudentInfo(studentInfo);

			String msg = String.format("Your temporary password: %s%nPlease login to complete your account information", randomPass);
//			String msg = "Your temporary password: " + randomPass
//					+ "\nPlease login to complete your account information";
//			emailService.send(msg, "Temporary_Password", student.getEmail());
//			emailService.send(msg, "Temporary_Password", new String[]{student.getEmail(),"gevorg.ghazaryan00@gmail.com","tigranuhi.mkrt@gmail.com",
//					"gorhakobiann@gmail.com","tigranuhi89@rambler.ru"});
			return id;
		}
		return -1;
	}

	@Override
	public int[] addStudents(List<Student> students) {
		return studentDao.addStudents(students);
	}

	@Override
	public Student getStudent(Integer id) {
		Student student = studentDao.getStudent(id);
//		student.setStudentInfo(this.studentInfo.getStudentInfoByStudentId(id));
		student.setGroups(groupService.getGroupsByStudentId(id));
		return student;
	}

	@Override
	public Student getStudentByEmail(String email) {
		Student student = studentDao.getStudentByEmail(email);
		if (student != null) {
			student.setStudentInfo(this.studentInfo.getStudentInfoByStudentId(student.getId()));
		}
		return student;

	}

	@Override
	public List<Student> getStudents() {
		return studentDao.getStudents();
	}

	@Override
	public List<Student> getGroupStudents(Integer groupId) {
		return studentDao.getStudentsByGroupId(groupId);
	}

	@Override
	public Integer updateStudent(Student student) {
		return this.studentDao.updateStudent(student);
	}

	@Override
	public Integer deleteStudent(Integer id) {
		return this.studentDao.deleteStudent(id);
	}

}
