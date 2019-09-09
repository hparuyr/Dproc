package am.dproc.sms.services.root;

import java.util.List;

import am.dproc.sms.models.Admin;



public interface AdminService {


	public Integer addAdmin(Admin admin);
	
	public Admin getAdmin(Integer id);
	
	public List<Admin> getSchoolAdmins(Integer schoolId);
	
	public List<Admin> getAdmins();
	
	public Integer updateAdmin(Admin admin);
	
	public Integer deleteAdmin(Integer id);
}
