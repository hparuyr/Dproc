package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.StudentInfoDAO;
import am.dproc.sms.models.StudentInfo;

@Repository
public class StudentInfoDAODBImpl implements StudentInfoDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String ADD_STUDENT_INFO = "INSERT INTO mydb.STUDENT_INFO (PASSPORT_ID, SOCIAL_CARD_ID, BIRTH_DATE, IMAGE_URL, CREATION_DATE, CHANGE_DATE, ADMIN_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_STUDENT_INFO_BY_STUDENT_ID = "SELECT * FROM mydb.STUDENT_INFO WHERE STUDENT_ID = ?";
	private static final String UPDATE_STUDENT_INFO_PASSPORT_ID = "UPDATE mydb.STUDENT_INFO SET PASSPORT_ID = ?, CHANGE_DATE = ?, WHERE ADMIN_ID = ?";
	private static final String UPDATE_STUDENT_INFO_SOCIAL_CARD_ID = "UPDATE mydb.STUDENT_INFO SET SOCIAL_CARD_ID = ?, CHANGE_DATE = ?, WHERE ADMIN_ID = ?";
	private static final String UPDATE_STUDENT_INFO_BIRTH_DATE = "UPDATE mydb.STUDENT_INFO SET BIRTH_DATE= ?, CHANGE_DATE = ?, WHERE ADMIN_ID = ?";
	private static final String UPDATE_STUDENT_INFO_IMAGE_URL = "UPDATE mydb.STUDENT_INFO SET IMAGE_URL = ?, CHANGE_DATE = ?, WHERE ADMIN_ID = ?";
	private static final String DELETE_STUDENT_INFO_BY_STUDENT_ID = "DELETE FROM mydb.STUDENT_INFO WHERE STUDENT_ID = ?";

	@Override
	public Integer addStudentInfo(StudentInfo studentInfo) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(ADD_STUDENT_INFO,
				new Object[] { studentInfo.getPassportId(), studentInfo.getSocialCardId(), studentInfo.getBirthDate(),
						studentInfo.getImageUrl(), currentTimeMillis, currentTimeMillis, studentInfo.getStudentId(), });
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
	public Integer updateStudentInfoPassportId(Integer studentId, Integer passportId) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_STUDENT_INFO_PASSPORT_ID,
				new Object[] { passportId, currentTimeMillis, studentId });
	}

	@Override
	public Integer updateStudentInfoSocialCardId(Integer studentId, Integer socialCardId) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_STUDENT_INFO_SOCIAL_CARD_ID,
				new Object[] { socialCardId, currentTimeMillis, studentId });
	}

	@Override
	public Integer updateStudentInfoBirthDate(Integer studentId, Long birthDate) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_STUDENT_INFO_BIRTH_DATE,
				new Object[] { birthDate, currentTimeMillis, studentId });
	}

	@Override
	public Integer updateStudentInfoImageUrl(Integer studentId, String imageUrl) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_STUDENT_INFO_IMAGE_URL,
				new Object[] { imageUrl, currentTimeMillis, studentId });
	}

	@Override
	public Integer deleteStudentInfo(Integer studentId) {
		return jdbctemplate.update(DELETE_STUDENT_INFO_BY_STUDENT_ID, studentId);
	}

	private static class StudentInfoMapper implements RowMapper<StudentInfo> {
		@Override
		public StudentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			StudentInfo studentInfo = new StudentInfo();
			studentInfo.setPassportId(rs.getInt("PASSPORT_ID"));
			studentInfo.setSocialCardId(rs.getInt("SOCIAL_CARD_ID"));
			//studentInfo.setBirthDate(rs.getLong("birthDate"));
			studentInfo.setImageUrl(rs.getString("IMAGE_URL"));
			return studentInfo;
		}

	}
}
