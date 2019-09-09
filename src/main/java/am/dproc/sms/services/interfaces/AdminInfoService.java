package am.dproc.sms.services.root;

import am.dproc.sms.models.AdminInfo;

public interface AdminInfoService {
	
	public Integer addAdminInfo(AdminInfo adminInfo);
	
	public AdminInfo getAdminInfoByAdminId(Integer adminId);
	
	public Integer updateAdminInfo(AdminInfo adminInfo);
	
	public Integer deleteAdminInfo(Integer adminId);

}
