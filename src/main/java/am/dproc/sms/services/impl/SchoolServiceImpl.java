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
	SchoolDAO school;
	
	@Autowired
	GroupService group;
	
	@Autowired
	AdminService admin;
	
	@Autowired
	TeacherService teacher;


	@Override
	public Integer addSchool(School school) {
		return this.school.addSchool(school);
	}

	@Override
	public School getSchool(Integer id) {
		School school = this.school.getSchool(id);
		school.setListOfGroups(this.group.getSchoolGroups(id));
		school.setListOfAdmins(this.admin.getSchoolAdmins(id));
		school.setListOfTeachers(this.teacher.getSchoolTeachers(id));
		return school;
	}

	@Override
	public List<School> getSchools() {
		return school.getSchooles();
	}

	@Override
	public Integer updateSchool(School school) {
		if (school.getName() != null) {
			return this.school.updateSchoolName(school.getId(), school.getName());
		} else if (school.getAddress() != null) {
			return this.school.updateSchoolAddress(school.getId(), school.getAddress());
		} 
		return 0;
	}

	@Override
	public Integer deleteSchool(Integer id) {
		return this.school.deleteSchool(id);
	}

}
