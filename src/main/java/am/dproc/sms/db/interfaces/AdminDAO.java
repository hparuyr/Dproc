package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Admin;

public interface AdminDAO {
	
	public Integer addAdmin(Admin admin);

	public Admin getAdmin(Integer id);
	
	public List<Admin> getAdmins();
	
	public List<Admin> getAdminsBySchoolId(Integer schoolId);
	
	public Integer updateAdminName(Integer id, String name);
	
	public Integer updateAdminSurname(Integer id, String surname);
	
	public Integer updateAdminEmail(Integer id, String email);
	
	public Integer updateAdminPassword(Integer id, String password);
	
	public Integer updateAdminSchoolId(Integer id, Integer schoolId);
	
	public Integer deleteAdmin(Integer id);
}
