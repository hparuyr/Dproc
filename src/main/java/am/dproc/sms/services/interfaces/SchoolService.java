package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.School;

public interface SchoolService {

	public Integer addSchool(School school);

	public School getSchool(Integer id);

	public List<School> getSchools();

	public Integer updateSchool(School school);

	public Integer deleteSchool(Integer id);

}
