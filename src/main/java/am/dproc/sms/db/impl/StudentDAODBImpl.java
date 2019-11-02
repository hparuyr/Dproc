package am.dproc.sms.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.StudentDAO;
import am.dproc.sms.models.Student;
import am.dproc.sms.models.StudentInfo;
import am.dproc.sms.enums.StudentStatus;

@Repository
public class StudentDAODBImpl implements StudentDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String ADD_STUDENT = "INSERT INTO mydb.USER (FIRSTNAME, LASTNAME, EMAIL, PASSWORD, STATUS, TYPE, CREATION_DATE) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_STUDENT_BY_ID = "SELECT * FROM mydb.USER LEFT JOIN `USER_INFO` on `USER`.`ID` = `USER_INFO`.`USER_ID` WHERE ID = ?";
	private static final String GET_STUDENT_BY_EMAIL = "SELECT * FROM mydb.USER WHERE EMAIL = ? AND TYPE = 1";
	private static final String GET_STUDENTS = "SELECT * FROM mydb.USER LEFT JOIN `USER_INFO` on `USER`.`ID` = `USER_INFO`.`USER_ID` WHERE TYPE = 1";
	private static final String GET_STUDENTS_BY_GROUP_ID = "SELECT `USER`.`ID`, `USER`.`FIRSTNAME`, `USER`.`LASTNAME`, `USER`.`LASTNAME`, `USER`.`EMAIL`, `USER`.`PASSWORD`, "
														+ "`USER`.`STATUS`, `USER`.`TYPE` FROM `STUDENT_GROUP` join `USER` on"
														+ " `STUDENT_GROUP`.`STUDENT_ID`= `USER`.`ID`  WHERE `GROUP_ID`= ?";
	private static final String GET_STUDENT_STATUS_BY_ID = "SELECT STATUS FROM mydb.USER WHERE ID = ?";
	private static final String UPDATE_STUDENT = "UPDATE mydb.USER SET FIRSTNAME = ?, LASTNAME = ?, EMAIL = ?, PASSWORD = ?, STATUS = ? WHERE ID = ?";
	private static final String UPDATE_STUDENT_FIRST_NAME = "UPDATE mydb.USER SET FIRSTNAME = ? WHERE ID = ?";
	private static final String UPDATE_STUDENT_LAST_NAME = "UPDATE mydb.USER SET LASTNAME = ? WHERE ID = ?";
	private static final String UPDATE_STUDENT_EMAIL = "UPDATE mydb.USER SET EMAIL= ? WHERE ID = ?";
	private static final String UPDATE_STUDENT_PASSWORD = "UPDATE mydb.USER SET PASSWORD = ? WHERE ID = ?";
	private static final String UPDATE_STUDENT_STATUS = "UPDATE mydb.USER SET STATUS = ? WHERE ID = ?";
	private static final String DELETE_STUDENT_BY_ID = "DELETE FROM mydb.USER WHERE ID = ?";

	@Override
	public Integer addStudent(Student student) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbctemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(ADD_STUDENT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, student.getFirstname());
			ps.setString(2, student.getLastname());
			ps.setString(3, student.getEmail());
			ps.setString(4, student.getPassword());
			ps.setInt(5, student.getStatus());
			ps.setLong(6, 1);
			ps.setLong(7, System.currentTimeMillis());
			return ps;
		}, keyHolder);
		return (Integer) keyHolder.getKey();
	}

	@Override
	public int[] addStudents(List<Student> students) {

		return jdbctemplate.execute((PreparedStatementCreator) con -> {
			PreparedStatement ps = con.prepareStatement(ADD_STUDENT, Statement.RETURN_GENERATED_KEYS);
			for (Student student : students) {
				ps.setString(1, student.getFirstname());
				ps.setString(2, student.getLastname());
				ps.setString(3, student.getEmail());
				ps.setString(4, student.getPassword());
				// add StudentStatus type to student object
				ps.setInt(5, StudentStatus.PENDING.ordinal());
				ps.setLong(6, System.currentTimeMillis());
				ps.setLong(7, System.currentTimeMillis());
				ps.addBatch();
			}
			ps.executeBatch();
			return ps;
		}, ps -> {
			ResultSet rs = ps.getGeneratedKeys();
			int curId;
			int[] ids = new int[students.size()];
			int current = 0;
			while (rs.next()) {
				curId = rs.getInt(1);
				ids[current] = curId;
				current++;
				System.out.println(curId);
			}
			return ids;
		});
	}

	@Override
	public Student getStudent(Integer id) {
		try {
			return jdbctemplate.queryForObject(GET_STUDENT_BY_ID, new StudentMapper(), id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Student getStudentByEmail(String email) {
		try {
			return jdbctemplate.queryForObject(GET_STUDENT_BY_EMAIL, new StudentMapper(), email);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Student> getStudents() {
		return jdbctemplate.query(GET_STUDENTS, new StudentMapper());
	}

	@Override
	public List<Student> getStudentsByGroupId(Integer groupId) {
		return jdbctemplate.query(GET_STUDENTS_BY_GROUP_ID, new StudentMapper(), groupId);
	}

	@Override
	public String getStudentStatus(Integer id) {
		return jdbctemplate.queryForObject(GET_STUDENT_STATUS_BY_ID, String.class, id);
	}

	@Override
	public Integer updateStudent(Student student) {
		return jdbctemplate.update(UPDATE_STUDENT, student.getFirstname(), student.getLastname(), student.getEmail(),
				student.getPassword(), student.getStatus(), student.getId());
	}
	
	@Override
	public Integer updateStudentFirstName(Integer id, String firstName) {
		return jdbctemplate.update(UPDATE_STUDENT_FIRST_NAME, firstName, id);
	}

	@Override
	public Integer updateStudentLastName(Integer id, String lastName) {
		return jdbctemplate.update(UPDATE_STUDENT_LAST_NAME, lastName, id);
	}

	@Override
	public Integer updateStudentEmail(Integer id, String email) {
		return jdbctemplate.update(UPDATE_STUDENT_EMAIL, email, id);
	}

	@Override
	public Integer updateStudentPassword(Integer id, String password) {
		return jdbctemplate.update(UPDATE_STUDENT_PASSWORD, password, id);
	}

	@Override
	public Integer updateStudentStatus(Integer id, int status) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_STUDENT_STATUS, status, currentTimeMillis, id);
	}

	@Override
	public Integer deleteStudent(Integer id) {
		return jdbctemplate.update(DELETE_STUDENT_BY_ID, id);
	}

	private static class StudentMapper implements RowMapper<Student> {
		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student student = new Student();
			student.setId(rs.getInt("ID"));
			student.setFirstname(rs.getString("FIRSTNAME"));
			student.setLastname(rs.getString("LASTNAME"));
			student.setEmail(rs.getString("EMAIL"));
			student.setPassword(rs.getString("PASSWORD"));
			student.setStatus(rs.getInt("STATUS"));
			Integer userId;
			try{
				userId = rs.getInt("USER_ID");
			} catch (SQLException e) {
				userId = null;
			}
			
			if(userId != null) {
				StudentInfo studentInfo = new StudentInfo();
				studentInfo.setPassportId(rs.getString("PASSPORT_ID"));
				studentInfo.setSocialCardId(rs.getString("SOCIAL_CARD_ID"));
				studentInfo.setBirthDate(rs.getLong("BIRTH_DATE"));
				studentInfo.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				studentInfo.setAddress(rs.getString("ADDRESS"));
				studentInfo.setImageUrl(rs.getString("IMAGE_URL"));
				studentInfo.setGender(rs.getInt("GENDER"));
				student.setStudentInfo(studentInfo);
			}
			return student;
		}
	}
}
