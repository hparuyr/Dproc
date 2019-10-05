package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.StudentDAO;
import am.dproc.sms.helpers.RandomPassGenerator;
import am.dproc.sms.models.Student;
import am.dproc.sms.models.StudentStatus;
import am.dproc.sms.services.interfaces.EmailService;
import am.dproc.sms.services.interfaces.StudentInfoService;
import am.dproc.sms.services.interfaces.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDAO student;
	
	@Autowired
	StudentInfoService studentInfo;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	EmailService emailService;

	@Override
	public Integer addStudent(Student student) {
		Student existingStudent = this.student.getStudentByEmail(student.getEmail());
		if(existingStudent != null) {
			return 0;
		}
		String randomPass = RandomPassGenerator.alphaNumericString(12);
		student.setPassword(passwordEncoder.encode(randomPass));
		student.setStatus(StudentStatus.PENDING.ordinal());
		student.setGroupId(1);
		int id = this.student.addStudent(student);
		if(id > 0) {
			String msg = "Your temporary password: "+randomPass+"\nPlease login to complete your account information";
			emailService.send(msg, "Temporary_Password", student.getEmail());
//			emailService.send(msg, "Temporary_Password", new String[]{student.getEmail(),"gevorg.ghazaryan00@gmail.com","tigranuhi.mkrt@gmail.com",
//					"gorhakobiann@gmail.com","tigranuhi89@rambler.ru"});
			return id;
		}
		return -1;
	}
	
	
	
	@Override
	public int[] addStudents(List<Student> students) {
		return student.addStudents(students);
	}

	@Override
	public Student getStudent(Integer id) {
		Student student = this.student.getStudent(id);
		student.setStudentInfo(this.studentInfo.getStudentInfoByStudentId(id));
		return student;
	}

	@Override
	public Student getStudentByEmail(String email) {
		Student student = this.student.getStudentByEmail(email);
		student.setStudentInfo(this.studentInfo.getStudentInfoByStudentId(student.getId()));
		return student;
		
	}
	
	@Override
	public List<Student> getGroupStudents(Integer groupId) {
		return this.student.getStudentsByGroupId(groupId);
	}

	@Override
	public List<Student> getStudents() {
		return student.getStudents();
	}

	@Override
	public Integer updateStudent(Student student) {
		if (student.getName() != null) {
			return this.student.updateStudentName(student.getId(), student.getName());
		} else if (student.getSurname() != null) {
			return this.student.updateStudentSurname(student.getId(), student.getSurname());
		} else if (student.getEmail() != null) {
			return this.student.updateStudentEmail(student.getId(), student.getEmail());
		} else if (student.getPassword() != null) {
			return this.student.updateStudentPassword(student.getId(), student.getPassword());
		} else if (student.getStatus() != null) {
			return this.student.updateStudentStatus(student.getId(), student.getStatus());
		} else if (student.getGroupId() != null) {
			return this.student.updateStudentGroupId(student.getId(), student.getGroupId());
		}
		return 0;
	}

	@Override
	public Integer deleteStudent(Integer id) {
		return this.student.deleteStudent(id);
	}

}
