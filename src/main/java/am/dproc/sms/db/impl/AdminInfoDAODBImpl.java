package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.AdminInfoDAO;
import am.dproc.sms.models.AdminInfo;

@Repository
public class AdminInfoDAODBImpl implements AdminInfoDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String ADD_ADMIN_INFO = "INSERT INTO mydb.ADMIN_INFO (PASSPORT_ID, SOCIAL_CARD_ID, BIRTH_DATE, IMAGE_URL, CREATION_DATE,CHANGE_DATE, ADMIN_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ADMIN_INFO_BY_ADMIN_ID = "SELECT * FROM mydb.ADMIN_INFO WHERE ADMIN_ID = ?";
	private static final String UPDATE_ADMIN_INFO_PASSPORT_ID = "UPDATE mydb.ADMIN_INFO SET PASSPORT_ID = ?, CHANGE_DATE = ?, WHERE ADMIN_ID = ?";
	private static final String UPDATE_ADMIN_INFO_SOCIAL_CARD_ID = "UPDATE mydb.ADMIN_INFO SET SOCIAL_CARD_ID = ?, CHANGE_DATE = ?, WHERE ADMIN_ID = ?";
	private static final String UPDATE_ADMIN_INFO_BIRTH_DATE = "UPDATE mydb.ADMIN_INFO SET BIRTH_DATE= ?, CHANGE_DATE = ?, WHERE ADMIN_ID = ?";
	private static final String UPDATE_ADMIN_INFO_IMAGE_URL = "UPDATE mydb.ADMIN_INFO SET IMAGE_URL = ?, CHANGE_DATE = ?, WHERE ADMIN_ID = ?";
	private static final String DELETE_ADMIN_INFO_BY_ADMIN_ID = "DELETE FROM mydb.ADMIN_INFO WHERE ADMIN_ID = ?";

	@Override
	public Integer addAdminInfo(AdminInfo adminInfo) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(ADD_ADMIN_INFO,
				new Object[] { adminInfo.getPassportId(), adminInfo.getSocialCardId(), adminInfo.getBirthDate(),
						adminInfo.getImageUrl(), currentTimeMillis, currentTimeMillis, adminInfo.getAdminId(), });
	}

	@Override
	public AdminInfo getAdminInfo(Integer adminId) {
		return jdbctemplate.queryForObject(GET_ADMIN_INFO_BY_ADMIN_ID, new AdminInfoMapper(), adminId);
	}

	@Override
	public Integer updateAdminInfoPassportId(Integer adminId, Integer passportId) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_ADMIN_INFO_PASSPORT_ID,
				new Object[] { passportId, currentTimeMillis, adminId });
	}

	@Override
	public Integer updateAdminInfoSocialCardId(Integer adminId, Integer socialCardId) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_ADMIN_INFO_SOCIAL_CARD_ID,
				new Object[] { socialCardId, currentTimeMillis, adminId });
	}

	@Override
	public Integer updateAdminInfoBirthDate(Integer adminId, Long birthDate) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_ADMIN_INFO_BIRTH_DATE,
				new Object[] { birthDate, currentTimeMillis, adminId });
	}

	@Override
	public Integer updateAdminInfoImageUrl(Integer adminId, String imageUrl) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_ADMIN_INFO_IMAGE_URL, new Object[] { imageUrl, currentTimeMillis, adminId });
	}

	@Override
	public Integer deleteAdminInfo(Integer adminId) {
		return jdbctemplate.update(DELETE_ADMIN_INFO_BY_ADMIN_ID, adminId);
	}

	private static class AdminInfoMapper implements RowMapper<AdminInfo> {
		@Override
		public AdminInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			AdminInfo adminInfo = new AdminInfo();
			adminInfo.setPassportId(rs.getInt("passportId"));
			adminInfo.setSocialCardId(rs.getInt("socialCardId"));
			adminInfo.setBirthDate(rs.getLong("birthDate"));
			adminInfo.setCreationDate(rs.getLong("creation_date"));
			adminInfo.setAdminId(rs.getInt("adminId"));
			return adminInfo;
		}

	}

}
