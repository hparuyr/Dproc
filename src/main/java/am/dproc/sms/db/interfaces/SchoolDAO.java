package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.School;

public interface SchoolDAO {

	Integer addSchool(School school);

	School getSchool(Integer id);

	List<School> getSchools();

	Integer updateSchoolName(Integer id, String name);

	Integer updateSchoolAddress(Integer id, String address);

	Integer updateSchoolPhoneNumber(Integer id, String phoneNumber);

	Integer updateSchoolEMail(Integer id, String eMail);

	Integer deleteSchool(Integer id);

}
