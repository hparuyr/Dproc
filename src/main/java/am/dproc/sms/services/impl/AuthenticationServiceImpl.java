package am.dproc.sms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import am.dproc.sms.db.interfaces.StudentDAO;
import am.dproc.sms.helpers.RandomPassGenerator;
import am.dproc.sms.models.Student;
import am.dproc.sms.models.StudentStatus;
import am.dproc.sms.services.interfaces.EmailService;
import am.dproc.sms.services.interfaces.StudentInfoService;

public class AuthenticationServiceImpl {
	@Autowired
	StudentDAO student;
	@Autowired
	StudentInfoService studentInfo;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	EmailService emailService;

	public void register(Student student) {
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
//			return id;
		}
//		return -1;

	}

}
