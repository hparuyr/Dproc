package am.dproc.sms.services.interfaces;

import am.dproc.sms.models.AdminInfo;

public interface AdminInfoService {

	Integer addAdminInfo(AdminInfo adminInfo);

	AdminInfo getAdminInfoByAdminId(Integer adminId);

	Integer updateAdminInfo(AdminInfo adminInfo);

	Integer deleteAdminInfo(Integer adminId);

}
