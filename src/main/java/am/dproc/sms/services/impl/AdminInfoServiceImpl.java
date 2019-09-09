package am.dproc.sms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.AdminInfoDAO;
import am.dproc.sms.models.AdminInfo;
import am.dproc.sms.services.interfaces.AdminInfoService;

@Service
public class AdminInfoServiceImpl implements AdminInfoService {

	@Autowired
	AdminInfoDAO adminInfo;

	@Override
	public Integer addAdminInfo(AdminInfo adminInfo) {
		return this.adminInfo.addAdminInfo(adminInfo);
	}

	@Override
	public AdminInfo getAdminInfoByAdminId(Integer adminId) {
		return this.adminInfo.getAdminInfo(adminId);
	}

	@Override
	public Integer updateAdminInfo(AdminInfo adminInfo) {
		if (adminInfo.getPassportId() != null) {
			return this.adminInfo.updateAdminInfoPassportId(adminInfo.getAdminId(), adminInfo.getPassportId());
		} else if (adminInfo.getSocialCardId() != null) {
			return this.adminInfo.updateAdminInfoSocialCardId(adminInfo.getAdminId(), adminInfo.getSocialCardId());
		} else if (adminInfo.getBirthDate() != null) {
			return this.adminInfo.updateAdminInfoBirthDate(adminInfo.getAdminId(), adminInfo.getBirthDate());
		} else if (adminInfo.getImageUrl() != null) {
			return this.adminInfo.updateAdminInfoImageUrl(adminInfo.getAdminId(), adminInfo.getImageUrl());
		} 
		return 0;
	}

	@Override
	public Integer deleteAdminInfo(Integer adminId) {
		return this.adminInfo.deleteAdminInfo(adminId);
	}

}
