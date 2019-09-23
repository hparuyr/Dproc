package am.dproc.sms.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.StudentInfoDAO;
import am.dproc.sms.models.Group;
import am.dproc.sms.models.StudentInfo;

@Repository
public class StudentInfoDAODBImpl implements StudentInfoDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String ADD_STUDENT_INFO = "INSERT INTO mydb.STUDENT_INFO (PASSPORT_ID, SOCIAL_CARD_ID, BIRTH_DATE, IMAGE_URL, STUDENT_ID, CREATION_DATE, CHANGE_DATE) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
						studentInfo.getImageUrl(), studentInfo.getStudentId(), currentTimeMillis, currentTimeMillis });
	}

	@Override
	public int[] addStudentInfos(List<StudentInfo> infos) {
		Long currentTimeMillis = System.currentTimeMillis();
		return jdbctemplate.batchUpdate(ADD_STUDENT_INFO,new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, infos.get(i).getPassportId());
				ps.setString(2, infos.get(i).getSocialCardId());
				ps.setLong(3, infos.get(i).getBirthDate());
				ps.setString(4, infos.get(i).getImageUrl());
				ps.setInt(5, infos.get(i).getStudentId());
				ps.setLong(6, currentTimeMillis);
				ps.setLong(7, currentTimeMillis);
			}
			
			@Override
			public int getBatchSize() {
				return infos.size();
			}
		});
	}

	
	
	
	@Override
	public StudentInfo getStudentInfo(Integer studentId) {
		return jdbctemplate.queryForObject(GET_STUDENT_INFO_BY_STUDENT_ID, new StudentInfoMapper(), studentId);
	}

	@Override
	public Integer updateStudentInfoPassportId(Integer studentId, String passportId) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_STUDENT_INFO_PASSPORT_ID,
				new Object[] { passportId, currentTimeMillis, studentId });
	}

	@Override
	public Integer updateStudentInfoSocialCardId(Integer studentId, String socialCardId) {
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
			studentInfo.setPassportId(rs.getString("PASSPORT_ID"));
			studentInfo.setSocialCardId(rs.getString("SOCIAL_CARD_ID"));
			studentInfo.setBirthDate(rs.getLong("BIRTH_DATE"));
			;
			studentInfo.setImageUrl(rs.getString("IMAGE_URL"));
//			studentInfo.setCreationDate(rs.getLong("creation_date"));
			studentInfo.setStudentId(rs.getInt("STUDENT_ID"));
			return studentInfo;
		}

	}
}
