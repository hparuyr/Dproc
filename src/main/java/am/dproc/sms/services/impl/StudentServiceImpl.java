package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.StudentDAO;
import am.dproc.sms.models.Student;
import am.dproc.sms.services.interfaces.StudentInfoService;
import am.dproc.sms.services.interfaces.StudentService;

@Service
public class StudentServiceImpl implements  StudentService{
	
	@Autowired
	StudentDAO student;
	@Autowired
	StudentInfoService studentInfo;

	@Override
	public Integer addStudent(Student student) {
		return this.student.addStudent(student);
	}

	@Override
	public Student getStudent(Integer id) {
		Student student = this.student.getStudent(id);
		student.setStudentInfo(this.studentInfo.getStudentInfoByStudentId(id));
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
		}
		else if (student.getPassword() != null) {
			return this.student.updateStudentPassword(student.getId(), student.getPassword());
		}else if (student.getStatus() != null) {
			return this.student.updateStudentStatus(student.getId(), student.getStatus());
		}else if (student.getGroupId() != null) {
			return this.student.updateStudentGroupId(student.getId(), student.getGroupId());
		}
		return 0;
	}

	@Override
	public Integer deleteStudent(Integer id) {
		return this.student.deleteStudent(id);
	}

	
}
