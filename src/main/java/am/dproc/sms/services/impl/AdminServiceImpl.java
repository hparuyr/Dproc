package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.AdminDAO;
import am.dproc.sms.models.Admin;
import am.dproc.sms.services.interfaces.AdminInfoService;
import am.dproc.sms.services.interfaces.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO admin;

	@Autowired
	AdminInfoService adminInfo;

	@Override
	public Integer addAdmin(Admin admin) {
		return this.admin.addAdmin(admin);
	}

	@Override
	public Admin getAdmin(Integer id) {
		Admin admin = this.admin.getAdmin(id);
		admin.setAdminInfo(this.adminInfo.getAdminInfoByAdminId(id));
		return admin;
	}

	@Override
	public List<Admin> getSchoolAdmins(Integer schoolId) {
		return this.admin.getAdminsBySchoolId(schoolId);
	}

	@Override
	public List<Admin> getAdmins() {
		return admin.getAdmins();
	}

	@Override
	public Integer updateAdmin(Admin admin) {
		if (admin.getFirstname() != null) {
			return this.admin.updateAdminName(admin.getId(), admin.getFirstname());
		} else if (admin.getLastname() != null) {
			return this.admin.updateAdminSurname(admin.getId(), admin.getLastname());
		} else if (admin.getEmail() != null) {
			return this.admin.updateAdminEmail(admin.getId(), admin.getEmail());
		} else if (admin.getPassword() != null) {
			return this.admin.updateAdminPassword(admin.getId(), admin.getPassword());
		} else if (admin.getSchoolId() != null) {
			return this.admin.updateAdminSchoolId(admin.getId(), admin.getSchoolId());
		}
		return 0;
	}

	@Override
	public Integer deleteAdmin(Integer id) {
		return this.admin.deleteAdmin(id);
	}

}
