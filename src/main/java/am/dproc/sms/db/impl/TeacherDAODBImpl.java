package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.TeacherDAO;
import am.dproc.sms.models.Teacher;

@Repository
public class TeacherDAODBImpl implements TeacherDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String ADD_TEACHER = "INSERT INTO mydb.TEACHER (NAME, SURNAME, EMAIL, PASSWORD, CREATION_DATE,CHANGE_DATE, SCHOOL_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_TEACHER_BY_ID = "SELECT * FROM mydb.TEACHER WHERE ID = ?";
	private static final String GET_TEACHERS = "SELECT * FROM mydb.TEACHER";
	private static final String GET_TEACHERS_BY_SCHOOL_ID = "SELECT * FROM mydb.TEACHER WHERE SCHOOL_ID = ?";
	private static final String UPDATE_TEACHER_NAME = "UPDATE mydb.TEACHER SET NAME = ?, CHANGE_DATE = ?, WHERE ID = ?";
	private static final String UPDATE_TEACHER_SURNAME = "UPDATE mydb.TEACHER SET SURNAME = ?, CHANGE_DATE = ?, WHERE ID = ?";
	private static final String UPDATE_TEACHER_EMAIL = "UPDATE mydb.TEACHER SET EMAIL= ?, CHANGE_DATE = ?, WHERE ID = ?";
	private static final String UPDATE_TEACHER_PASSWORD = "UPDATE mydb.TEACHER SET PASSWORD = ?, CHANGE_DATE = ?, WHERE ID = ?";
	private static final String UPDATE_TEACHER_SCHOOL_ID = "UPDATE mydb.TEACHER SET SCHOOL_ID = ?, CHANGE_DATE = ?, WHERE ID = ?";
	private static final String DELETE_TEACHER_BY_ID = "DELETE FROM mydb.TEACHER WHERE ID = ?";

	@Override
	public Integer addTeacher(Teacher teacher) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(ADD_TEACHER,
				new Object[] { teacher.getName(), teacher.getSurname(), teacher.getEmail(), teacher.getPassword(),
						currentTimeMillis, currentTimeMillis, teacher.getSchoolId(), });
	}

	@Override
	public Teacher getTeacher(Integer id) {
		return jdbctemplate.queryForObject(GET_TEACHER_BY_ID, new TeacherMapper(), id);
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
	public Integer updateTeacherName(Integer id, String name) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_TEACHER_NAME, new Object[] { name, currentTimeMillis, id });
	}

	@Override
	public Integer updateTeacherSurname(Integer id, String surname) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_TEACHER_SURNAME, new Object[] { surname, currentTimeMillis, id });
	}

	@Override
	public Integer updateTeacherEmail(Integer id, String email) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_TEACHER_EMAIL, new Object[] { email, currentTimeMillis, id });
	}

	@Override
	public Integer updateTeacherPassword(Integer id, String password) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_TEACHER_PASSWORD, new Object[] { password, currentTimeMillis, id });
	}

	@Override
	public Integer updateTeacherSchoolId(Integer id, Integer schoolId) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_TEACHER_SCHOOL_ID, new Object[] { schoolId, currentTimeMillis, id });
	}

	@Override
	public Integer deleteTeacher(Integer id) {
		return jdbctemplate.update(DELETE_TEACHER_BY_ID, id);
	}

	private static class TeacherMapper implements RowMapper<Teacher> {
		@Override
		public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
			Teacher teacher = new Teacher();
			teacher.setId(rs.getInt("id"));
			teacher.setName(rs.getString("name"));
			teacher.setSurname(rs.getString("surname"));
			teacher.setEmail(rs.getString("email"));
			teacher.setPassword(rs.getString("password"));
			teacher.setCreationDate(rs.getLong("creation_date"));
			teacher.setSchoolId(rs.getInt("schoolId"));
			return teacher;
		}

	}

}
