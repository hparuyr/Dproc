package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.SchoolDAO;
import am.dproc.sms.models.School;
import am.dproc.sms.services.interfaces.AdminService;
import am.dproc.sms.services.interfaces.GroupService;
import am.dproc.sms.services.interfaces.SchoolService;
import am.dproc.sms.services.interfaces.TeacherService;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	SchoolDAO schoolDAO;

	@Autowired
	GroupService groupService;

	@Autowired
	AdminService adminService;

	@Autowired
	TeacherService teacherService;

	@Override
	public Integer addSchool(School school) {
		if (school.getAddress() == null || school.geteMail() == null || school.getName() == null || school.getPhoneNumber() == null) {
			return -1;
		}
		return schoolDAO.addSchool(school);
	}

	@Override
	public School getSchool(Integer id) {
		School school = this.schoolDAO.getSchool(id);
		school.setListOfGroups(this.groupService.getSchoolGroups(id));
		school.setListOfAdmins(this.adminService.getSchoolAdmins(id));
		school.setListOfTeachers(this.teacherService.getSchoolTeachers(id));
		return school;
	}

	@Override
	public List<School> getSchools() {
		return schoolDAO.getSchools();
	}

	@Override
	public Integer updateSchool(School school) {
		boolean bool = false;

		if (school.getName() != null) {
			if (this.schoolDAO.updateSchoolName(school.getId(), school.getName()) == 0) {
				return -1;
			}
			bool = true;
		}

		if (school.getAddress() != null) {
			if (this.schoolDAO.updateSchoolAddress(school.getId(), school.getAddress()) == 1)
				return -1;
			bool = true;
		}

		if (school.getPhoneNumber() != null) {
			if (this.schoolDAO.updateSchoolPhoneNumber(school.getId(), school.getPhoneNumber()) == 1)
				return -1;
			bool = true;
		}

		if (school.geteMail() != null) {
			if (this.schoolDAO.updateSchoolEMail(school.getId(), school.geteMail()) == 1)
				return -1;
			bool = true;
		}
		return bool ? 1 : 0;
	}

	@Override
	public Integer deleteSchool(Integer id) {
		return this.schoolDAO.deleteSchool(id);
	}

}
