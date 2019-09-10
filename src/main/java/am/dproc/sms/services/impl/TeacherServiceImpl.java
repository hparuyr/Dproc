package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.TeacherDAO;
import am.dproc.sms.models.Teacher;
import am.dproc.sms.services.interfaces.TeacherInfoService;
import am.dproc.sms.services.interfaces.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	TeacherDAO teacher;

	@Autowired
	TeacherInfoService teacherInfo;

	@Override
	public Integer addTeacher(Teacher teacher) {
		return this.teacher.addTeacher(teacher);
	}

	@Override
	public Teacher gerTeacher(Integer id) {
		Teacher teacher = this.teacher.getTeacher(id);
		teacher.setTeacherInfo(this.teacherInfo.getTeacherInfoByTeacherId(id));
		return teacher;
	}

	@Override
	public List<Teacher> getSchoolTeachers(Integer schoolId) {
		return this.teacher.getTeachersBySchoolId(schoolId);
	}

	@Override
	public List<Teacher> getTeachers() {
		return teacher.getTeachers();
	}

	@Override
	public Integer updateTeacher(Teacher teacher) {
		if (teacher.getName() != null) {
			return this.teacher.updateTeacherName(teacher.getId(), teacher.getName());
		} else if (teacher.getSurname() != null) {
			return this.teacher.updateTeacherSurname(teacher.getId(), teacher.getSurname());
		} else if (teacher.getEmail() != null) {
			return this.teacher.updateTeacherEmail(teacher.getId(), teacher.getEmail());
		} else if (teacher.getPassword() != null) {
			return this.teacher.updateTeacherPassword(teacher.getId(), teacher.getPassword());
		} else if (teacher.getSchoolId() != null) {
			return this.teacher.updateTeacherSchoolId(teacher.getId(), teacher.getSchoolId());
		}
		return 0;
	}

	@Override
	public Integer deleteTeacher(Integer id) {
		return this.teacher.deleteTeacher(id);
	}

}
