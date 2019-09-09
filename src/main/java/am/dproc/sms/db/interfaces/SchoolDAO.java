package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.School;

public interface SchoolDAO {
	public Integer addSchool(School school);
	
	public School getSchool(Integer id);
	
	public List<School> getSchooles();
	
	public Integer updateSchoolName(Integer id, String name);
	
	public Integer updateSchoolAddress(Integer id, String addresss);
	
	public Integer deleteSchool(Integer id);
			
	
	
	
}
