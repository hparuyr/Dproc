package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.TeacherInfoDAO;
import am.dproc.sms.models.TeacherInfo;

@Repository
public class TeacherInfoDAODBImpl implements TeacherInfoDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String ADD_TEACHER_INFO = "INSERT INTO mydb.USER_INFO (USER_ID, PASSPORT_ID, SOCIAL_CARD_ID, BIRTH_DATE, PHONE_NUMBER, ADDRESS, IMAGE_URL,"
												+ "GENDER, CREATION_DATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_TEACHER_INFO_BY_TEACHER_ID = "SELECT * FROM mydb.USER_INFO WHERE ADMIN_ID = ?";
	private static final String UPDATE_TEACHER_INFO_PASSPORT_ID = "UPDATE mydb.USER_INFO SET PASSPORT_ID = ?, CHANGE_DATE = ? WHERE USER_ID = ?";
	private static final String UPDATE_TEACHER_INFO_SOCIAL_CARD_ID = "UPDATE mydb.USER_INFO SET SOCIAL_ID = ?, CHANGE_DATE = ? WHERE USER_ID = ?";
	private static final String UPDATE_TEACHER_INFO_BIRTH_DATE = "UPDATE mydb.USER_INFO SET BIRTH_DATE= ?, CHANGE_DATE = ? WHERE USER_ID = ?";
	private static final String UPDATE_TEACHER_INFO_IMAGE_URL = "UPDATE mydb.USER_INFO SET IMAGE_URL = ?, CHANGE_DATE = ? WHERE USER_ID = ?";
	private static final String DELETE_TEACHER_INFO_BY_TEACHER_ID = "DELETE FROM mydb.USER_INFO WHERE USER_ID = ?";

	@Override
	public Integer addTeacherInfo(TeacherInfo teacherInfo) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(ADD_TEACHER_INFO,
				teacherInfo.getUserId(), teacherInfo.getPassportId(), teacherInfo.getSocialCardId(), teacherInfo.getBirthDate(), teacherInfo.getPhoneNumber(),
				teacherInfo.getAddress(), teacherInfo.getImageUrl(), teacherInfo.getGender(), currentTimeMillis);
	}

	@Override
	public TeacherInfo getTeacherInfo(Integer teacherId) {
		return jdbctemplate.queryForObject(GET_TEACHER_INFO_BY_TEACHER_ID, new TeacherInfoMapper(), teacherId);
	}

	@Override
	public Integer updateTeacherInfoPassportId(Integer teacherId, String passportId) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_TEACHER_INFO_PASSPORT_ID,
				passportId, currentTimeMillis, teacherId);
	}

	@Override
	public Integer updateTeacherInfoSocialCardId(Integer teacherId, String socialCardId) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_TEACHER_INFO_SOCIAL_CARD_ID,
				socialCardId, currentTimeMillis, teacherId);
	}

	@Override
	public Integer updateTeacherInfoBirthDate(Integer teacherId, Long birthDate) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_TEACHER_INFO_BIRTH_DATE,
				birthDate, currentTimeMillis, teacherId);
	}

	@Override
	public Integer updateTeacherInfoImageUrl(Integer teacherId, String imageUrl) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_TEACHER_INFO_IMAGE_URL,
				imageUrl, currentTimeMillis, teacherId);
	}

	@Override
	public Integer deleteTeacherInfo(Integer teacherId) {
		return jdbctemplate.update(DELETE_TEACHER_INFO_BY_TEACHER_ID, teacherId);
	}

	private static class TeacherInfoMapper implements RowMapper<TeacherInfo> {
		@Override
		public TeacherInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			TeacherInfo teacherInfo = new TeacherInfo();
			teacherInfo.setUserId(rs.getInt("USER_ID"));
			teacherInfo.setPassportId(rs.getString("PASSPORT_ID"));
			teacherInfo.setSocialCardId(rs.getString("SOCIAL_CARD_ID"));
			teacherInfo.setBirthDate(rs.getLong("BIRTH_DATE"));
			teacherInfo.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			teacherInfo.setAddress(rs.getString("ADDRESS"));
			teacherInfo.setImageUrl(rs.getString("IMAGE_URL"));
			teacherInfo.setGender(rs.getInt("GENDER"));
			return teacherInfo;
		}

	}

}
