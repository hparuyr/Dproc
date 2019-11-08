package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.TeacherDAO;
import am.dproc.sms.helpers.RandomPassGenerator;
import am.dproc.sms.models.Teacher;
import am.dproc.sms.models.TeacherInfo;
import am.dproc.sms.services.interfaces.EmailService;
import am.dproc.sms.services.interfaces.GroupService;
import am.dproc.sms.services.interfaces.TeacherInfoService;
import am.dproc.sms.services.interfaces.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	TeacherDAO teacherDao;

	@Autowired
	TeacherInfoService teacherInfo;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	EmailService emailService;

	@Autowired
	GroupService groupService;

	@Override
	public Integer addTeacher(Teacher teacher) {
		Teacher existingTeacher = teacherDao.getTeacherByEmail(teacher.getEmail());
		if (existingTeacher != null) {
			return 0;
		}
		String randomPass = RandomPassGenerator.alphaNumericString(12);
		teacher.setPassword(passwordEncoder.encode(randomPass));
//		teacher.setStatus(TeacherStatus.PENDING.ordinal());
		int id = teacherDao.addTeacher(teacher);
		if (id > 0) {
			TeacherInfo teacherInfo = teacher.getTeacherInfo();
			teacherInfo.setUserId(id);
			this.teacherInfo.addTeacherInfo(teacherInfo);

			String msg = String.format("Your temporary password: %s%nPlease login to complete your account information", randomPass);
//					"Your temporary password: " + randomPass + "\nPlease login to complete your account information";
//			emailService.send(msg, "Temporary_Password", teacher.getEmail());
//			emailService.send(msg, "Temporary_Password", new String[]{student.getEmail(),"gevorg.ghazaryan00@gmail.com","tigranuhi.mkrt@gmail.com",
//					"gorhakobiann@gmail.com","tigranuhi89@rambler.ru"});
			return id;
		}
		return -1;
	}

	@Override
	public Teacher getTeacher(Integer id) {
		Teacher teacher = teacherDao.getTeacher(id);
		return teacher;
	}

	@Override
	public List<Teacher> getSchoolTeachers(Integer schoolId) {
		return teacherDao.getTeachersBySchoolId(schoolId);
	}

	@Override
	public List<Teacher> getTeachers() {
		return teacherDao.getTeachers();
	}

	@Override
	public Integer updateTeacher(Teacher teacher) {
		return teacherDao.updateTeacher(teacher);
	}

	@Override
	public Integer deleteTeacher(Integer id) {
		return teacherDao.deleteTeacher(id);
	}

}
