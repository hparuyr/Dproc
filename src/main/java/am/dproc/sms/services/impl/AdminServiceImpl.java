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
	AdminDAO adminDao;

	@Autowired
	AdminInfoService adminInfo;

	@Override
	public Integer addAdmin(Admin admin) {
		return this.adminDao.addAdmin(admin);
	}

	@Override
	public Admin getAdmin(Integer id) {
		Admin admin = this.adminDao.getAdmin(id);
//		admin.setAdminInfo(this.adminInfo.getAdminInfoByAdminId(id));
		return admin;
	}

	@Override
	public List<Admin> getSchoolAdmins(Integer schoolId) {
		return this.adminDao.getAdminsBySchoolId(schoolId);
	}

	@Override
	public List<Admin> getAdmins() {
		return adminDao.getAdmins();
	}

	@Override
	public Integer updateAdmin(Admin admin) {
		return	adminDao.updateAdmin(admin);
	}

	@Override
	public Integer deleteAdmin(Integer id) {
		return this.adminDao.deleteAdmin(id);
	}

}
