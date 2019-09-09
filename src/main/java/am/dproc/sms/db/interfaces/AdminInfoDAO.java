package am.dproc.sms.db.interfaces;

import am.dproc.sms.models.AdminInfo;

public interface AdminInfoDAO {

	public Integer addAdminInfo(AdminInfo adminInfo);
	
	public AdminInfo getAdminInfo(Integer adminId);
	
	public Integer updateAdminInfoPassportId(Integer adminId, Integer passportId);
	
	public Integer updateAdminInfoSocialCardId(Integer adminId, Integer socialCardId);
	
	public Integer updateAdminInfoBirthDate(Integer adminId, Long birthDate);
	
	public Integer updateAdminInfoImageUrl(Integer adminId, String imageUrl);
	
	public Integer deleteAdminInfo(Integer adminId);
}
