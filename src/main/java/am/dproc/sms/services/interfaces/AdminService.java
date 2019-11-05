package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Admin;

public interface AdminService {

	Integer addAdmin(Admin admin);

	Admin getAdmin(Integer id);

	List<Admin> getSchoolAdmins(Integer schoolId);

	List<Admin> getAdmins();

	Integer updateAdmin(Admin admin);

	Integer deleteAdmin(Integer id);
}
