package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Admin;

public interface AdminDAO {

	Integer addAdmin(Admin admin);

	Admin getAdmin(Integer id);

	List<Admin> getAdmins();

	List<Admin> getAdminsBySchoolId(Integer schoolId);

	Integer updateAdminName(Integer id, String name);

	Integer updateAdminSurname(Integer id, String surname);

	Integer updateAdminEmail(Integer id, String email);

	Integer updateAdminPassword(Integer id, String password);

	Integer updateAdminSchoolId(Integer id, Integer schoolId);

	Integer deleteAdmin(Integer id);
}
