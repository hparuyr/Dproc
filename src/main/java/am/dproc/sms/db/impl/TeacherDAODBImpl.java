package am.dproc.sms.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.TeacherDAO;
import am.dproc.sms.models.Teacher;
import am.dproc.sms.models.TeacherInfo;

@Repository
public class TeacherDAODBImpl implements TeacherDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String ADD_TEACHER = "INSERT INTO mydb.USER (FIRSTNAME, LASTNAME, EMAIL, PASSWORD, STATUS, TYPE, CREATION_DATE) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_TEACHER_BY_ID = "SELECT * FROM mydb.USER LEFT JOIN `USER_INFO` on `USER`.`ID` = `USER_INFO`.`USER_ID` WHERE ID = ?";
	private static final String GET_TEACHER_BY_EMAIL = "SELECT * FROM mydb.USER WHERE ID = ? AND TYPE = 2";
	private static final String GET_TEACHERS = "SELECT * FROM mydb.USER LEFT JOIN `USER_INFO` on `USER`.`ID` = `USER_INFO`.`USER_ID` WHERE TYPE = 2";
	private static final String GET_TEACHERS_BY_SCHOOL_ID = "SELECT DISTINCT `USER`.`ID`, `USER`.`FIRSTNAME`, `USER`.`LASTNAME`, `USER`.`EMAIL`, `USER`.`PASSWORD`, "
			+ "`USER`.`TYPE`, `USER_INFO`.`USER_ID`, `USER_INFO`.`PASSPORT_ID`, `USER_INFO`.`SOCIAL_CARD_ID`, `USER_INFO`.`BIRTH_DATE`, `USER_INFO`.`PHONE_NUMBER`,"
			+ " `USER_INFO`.`ADDRESS`, `USER_INFO`.`IMAGE_URL`, `USER_INFO`.`GENDER` FROM `GROUP` JOIN `GROUP_COURSE` ON `GROUP`.`ID` = `GROUP_COURSE`.`GROUP_ID`"
			+ " JOIN `USER` ON `GROUP_COURSE`.`TEACHER_ID` = `USER`.`ID` JOIN `USER_INFO` ON `USER`.`ID` = `USER_INFO`.`USER_ID` WHERE `GROUP`.`SCHOOL_ID` = ?";
	private static final String UPDATE_TEACHER = "UPDATE mydb.USER SET FIRSTNAME = ?, LASTNAME = ?, EMAIL= ?, PASSWORD = ? WHERE ID = ?";
	private static final String UPDATE_TEACHER_FIRSTNAME = "UPDATE mydb.USER SET FIRSTNAME = ? WHERE ID = ?";
	private static final String UPDATE_TEACHER_LASTNAME = "UPDATE mydb.USER SET LASTNAME = ? WHERE ID = ?";
	private static final String UPDATE_TEACHER_EMAIL = "UPDATE mydb.USER SET EMAIL= ? WHERE ID = ?";
	private static final String UPDATE_TEACHER_PASSWORD = "UPDATE mydb.USER SET PASSWORD = ? WHERE ID = ?";
	private static final String DELETE_TEACHER_BY_ID = "DELETE FROM mydb.USER WHERE ID = ?";

	@Override
	public Integer addTeacher(Teacher teacher) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbctemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(ADD_TEACHER, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, teacher.getFirstname());
				ps.setString(2, teacher.getLastname());
				ps.setString(3, teacher.getEmail());
				ps.setString(4, teacher.getPassword());
				ps.setInt(5, 1);
				ps.setLong(6, 2);
				ps.setLong(7, System.currentTimeMillis());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	@Override
	public int[] addTeachers(List<Teacher> teachers) {
		return jdbctemplate.execute(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(ADD_TEACHER, Statement.RETURN_GENERATED_KEYS);
				for (Teacher teacher : teachers) {
					ps.setString(1, teacher.getFirstname());
					ps.setString(2, teacher.getLastname());
					ps.setString(3, teacher.getEmail());
					ps.setString(4, teacher.getPassword());
					// add StudentStatus type to student object
					ps.setInt(5, 1);
					ps.setInt(6, 2);
					ps.setLong(7, System.currentTimeMillis());
					ps.addBatch();
				}
				ps.executeBatch();
				return ps;
			}
		}, new PreparedStatementCallback<int[]>() {
			@Override
			public int[] doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ResultSet rs = ps.getGeneratedKeys();
				int curId;
				int[] ids = new int[teachers.size()];
				int current = 0;
				while (rs.next()) {
					curId = rs.getInt(1);
					ids[current] = curId;
					current++;
					System.out.println(curId);
				}
				return ids;
			}
		});
	}
	
	@Override
	public Teacher getTeacher(Integer id) {
		try {
			return jdbctemplate.queryForObject(GET_TEACHER_BY_ID, new TeacherMapper(), id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Teacher getTeacherByEmail(String email) {
		try {
			return jdbctemplate.queryForObject(GET_TEACHER_BY_EMAIL, new TeacherMapper(), email);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Teacher> getTeachers() {
		return jdbctemplate.query(GET_TEACHERS, new TeacherMapper());
	}

	@Override
	public List<Teacher> getTeachersBySchoolId(Integer schoolId) {
		return jdbctemplate.query(GET_TEACHERS_BY_SCHOOL_ID, new TeacherMapper(), schoolId);
	}

	@Override
	public Integer updateTeacher(Teacher teacher) {
		return jdbctemplate.update(UPDATE_TEACHER, teacher.getFirstname(), teacher.getLastname(), teacher.getEmail(), teacher.getPassword(), teacher.getId());
	}

	@Override
	public Integer updateTeacherFirstame(Integer id, String firstname) {
		return jdbctemplate.update(UPDATE_TEACHER_FIRSTNAME, firstname, id);
	}
	
	@Override
	public Integer updateTeacherLastname(Integer id, String lastname) {
		return jdbctemplate.update(UPDATE_TEACHER_LASTNAME, lastname, id);
	}

	@Override
	public Integer updateTeacherEmail(Integer id, String email) {
		return jdbctemplate.update(UPDATE_TEACHER_EMAIL, email, id);
	}

	@Override
	public Integer updateTeacherPassword(Integer id, String password) {
		return jdbctemplate.update(UPDATE_TEACHER_PASSWORD, password, id);
	}

	@Override
	public Integer deleteTeacher(Integer id) {
		return jdbctemplate.update(DELETE_TEACHER_BY_ID, id);
	}

	private static class TeacherMapper implements RowMapper<Teacher> {
		@Override
		public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
			Teacher teacher = new Teacher();
			teacher.setId(rs.getInt("ID"));
			teacher.setFirstname(rs.getString("FIRSTNAME"));
			teacher.setLastname(rs.getString("LASTNAME"));
			teacher.setEmail(rs.getString("EMAIL"));
			teacher.setPassword(rs.getString("PASSWORD"));
//			teacher.setStatus(rs.getInt("STATUS"));
			Integer userId;
			try {
				userId = rs.getInt("USER_ID");
			} catch (SQLException e) {
				userId = null;
			}
			if (userId > 0) {
				TeacherInfo teacherInfo = new TeacherInfo();
				teacherInfo.setUserId(userId);
				teacherInfo.setPassportId(rs.getString("PASSPORT_ID"));
				teacherInfo.setSocialCardId(rs.getString("SOCIAL_CARD_ID"));
				teacherInfo.setBirthDate(rs.getLong("BIRTH_DATE"));
				teacherInfo.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				teacherInfo.setAddress(rs.getString("ADDRESS"));
				teacherInfo.setImageUrl(rs.getString("IMAGE_URL"));
				teacherInfo.setGender(rs.getInt("GENDER"));
				teacher.setTeacherInfo(teacherInfo);
			}
			return teacher;
		}

	}

}
