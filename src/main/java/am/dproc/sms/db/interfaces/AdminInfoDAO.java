package am.dproc.sms.db.interfaces;

import am.dproc.sms.models.AdminInfo;

public interface AdminInfoDAO {

	Integer addAdminInfo(AdminInfo adminInfo);

	AdminInfo getAdminInfo(Integer adminId);

	Integer updateAdminInfoPassportId(Integer adminId, Integer passportId);

	Integer updateAdminInfoSocialCardId(Integer adminId, Integer socialCardId);

	Integer updateAdminInfoBirthDate(Integer adminId, Long birthDate);

	Integer updateAdminInfoImageUrl(Integer adminId, String imageUrl);

	Integer deleteAdminInfo(Integer adminId);
}
