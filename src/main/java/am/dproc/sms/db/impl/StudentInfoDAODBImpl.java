package am.dproc.sms.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import am.dproc.sms.db.interfaces.StudentInfoDAO;
import am.dproc.sms.models.StudentInfo;

@Repository
public class StudentInfoDAODBImpl implements StudentInfoDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String ADD_STUDENT_INFO = "INSERT INTO mydb.USER_INFO (USER_ID, PASSPORT_ID, SOCIAL_CARD_ID, BIRTH_DATE, PHONE_NUMBER, ADDRESS, IMAGE_URL,"
												+ " GENDER, CREATION_DATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_STUDENT_INFO_BY_STUDENT_ID = "SELECT * FROM mydb.USER_INFO WHERE USER_ID = ?";
	private static final String UPDATE_STUDENT_INFO_PASSPORT_ID = "UPDATE mydb.USER_INFO SET PASSPORT_ID = ?, CHANGE_DATE = ? WHERE USER_ID = ?";
	private static final String UPDATE_STUDENT_INFO_SOCIAL_CARD_ID = "UPDATE mydb.USER_INFO SET SOCIAL_CARD_ID = ?, CHANGE_DATE = ? WHERE USER_ID = ?";
	private static final String UPDATE_STUDENT_INFO_BIRTH_DATE = "UPDATE mydb.USER_INFO SET BIRTH_DATE= ?, CHANGE_DATE = ? WHERE USER_ID = ?";
	private static final String UPDATE_STUDENT_INFO_IMAGE_URL = "UPDATE mydb.USER_INFO SET IMAGE_URL = ?, CHANGE_DATE = ? WHERE USER_ID = ?";
	private static final String DELETE_STUDENT_INFO_BY_STUDENT_ID = "DELETE FROM mydb.USER_INFO WHERE USER_ID = ?";

	@Override
	public Integer addStudentInfo(StudentInfo studentInfo) {
		return jdbctemplate.update(ADD_STUDENT_INFO,
				studentInfo.getUserId(), studentInfo.getPassportId(), studentInfo.getSocialCardId(), studentInfo.getBirthDate(),
				studentInfo.getPhoneNumber(), studentInfo.getAddress(), studentInfo.getImageUrl(), studentInfo.getGender(), System.currentTimeMillis());
	}

	@Override
	public int[] addStudentInfos(List<StudentInfo> infos) {
		return jdbctemplate.batchUpdate(ADD_STUDENT_INFO,new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, infos.get(i).getUserId());
				ps.setString(2, infos.get(i).getPassportId());
				ps.setString(3, infos.get(i).getSocialCardId());
				ps.setLong(4, infos.get(i).getBirthDate());
				ps.setString(5, infos.get(i).getPhoneNumber());
				ps.setString(6, infos.get(i).getAddress());
				ps.setString(7, infos.get(i).getImageUrl());
				ps.setInt(8, infos.get(i).getGender());
				ps.setLong(9, System.currentTimeMillis());
			}

			public int getBatchSize() {
				return infos.size();
			}
		});
	}

	@Override
	public StudentInfo getStudentInfo(Integer studentId) {
		try {
			return jdbctemplate.queryForObject(GET_STUDENT_INFO_BY_STUDENT_ID, new StudentInfoMapper(), studentId);
		} catch (EmptyResultDataAccessException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer updateStudentInfoPassportId(Integer studentId, String passportId) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_STUDENT_INFO_PASSPORT_ID,
				passportId, currentTimeMillis, studentId);
	}

	@Override
	public Integer updateStudentInfoSocialCardId(Integer studentId, String socialCardId) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_STUDENT_INFO_SOCIAL_CARD_ID,
				socialCardId, currentTimeMillis, studentId);
	}

	@Override
	public Integer updateStudentInfoBirthDate(Integer studentId, Long birthDate) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_STUDENT_INFO_BIRTH_DATE,
				birthDate, currentTimeMillis, studentId);
	}

	@Override
	public Integer updateStudentInfoImageUrl(Integer studentId, String imageUrl) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_STUDENT_INFO_IMAGE_URL,
				imageUrl, currentTimeMillis, studentId);
	}

	@Override
	public Integer deleteStudentInfo(Integer studentId) {
		return jdbctemplate.update(DELETE_STUDENT_INFO_BY_STUDENT_ID, studentId);
	}

	private static class StudentInfoMapper implements RowMapper<StudentInfo> {
		@Override
		public StudentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			StudentInfo studentInfo = new StudentInfo();
			studentInfo.setUserId(rs.getInt("USER_ID"));
			studentInfo.setPassportId(rs.getString("PASSPORT_ID"));
			studentInfo.setSocialCardId(rs.getString("SOCIAL_CARD_ID"));
			studentInfo.setBirthDate(rs.getLong("BIRTH_DATE"));
			studentInfo.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			studentInfo.setAddress(rs.getString("ADDRESS"));
			studentInfo.setImageUrl(rs.getString("IMAGE_URL"));
			studentInfo.setGender(rs.getInt("GENDER"));
			return studentInfo;
		}
	}
}
