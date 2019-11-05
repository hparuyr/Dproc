package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.School;

public interface SchoolService {

	Integer addSchool(School school);

	School getSchool(Integer id);

	List<School> getSchools();

	Integer updateSchool(School school);

	Integer deleteSchool(Integer id);

}
