package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.ClassroomDAO;
import am.dproc.sms.models.Classroom;

@Repository
public class ClassroomDAODBImpl implements ClassroomDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String GET_CLASSROOM_BY_ID = "SELECT * FROM mydb.CLASSROOM WHERE ID = ?";
	private static final String GET_CLASSROOMS = "SELECT * FROM mydb.CLASSROOM";
	private static final String GET_CLASSROOM_BY_CAPACITY = "SELECT * FROM mydb.CLASSROOM WHERE CAPACITY BETWEEN ? AND ?";
	private static final String DELETE_CLASSROOM_BY_ID = "DELETE FROM mydb.CLASSROOM WHERE ID = ?";
	private static final String GET_CLASSROOM_ID = "SELECT ID FROM mydb.CLASSROOM WHERE NUMBER = ? AND CAPACITY = ? AND TYPE = ? AND SUBJECT = ? AND CREATION_DATE = ? AND SCHOOL_ID = ?";
	private static final String ADD_CLASSROOM = "INSERT INTO mydb.CLASSROOM (NUMBER, CAPACITY, TYPE, SUBJECT, CREATION_DATE, CHANGE_DATE, SCHOOL_ID) VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String EDIT_CLASSROOM_NUMBER = "UPDATE mydb.CLASSROOM SET NUMBER = ?, CHANGE_DATE = ? WHERE ID = ?";
	private static final String EDIT_CLASSROOM_CAPACITY = "UPDATE mydb.CLASSROOM SET CAPACITY = ?, CHANGE_DATE = ? WHERE ID = ?";
	private static final String EDIT_CLASSROOM_TYPE = "UPDATE mydb.CLASSROOM SET TYPE = ?, CHANGE_DATE = ? WHERE ID = ?";
	private static final String EDIT_CLASSROOM_SUBJECT = "UPDATE mydb.CLASSROOM SET SUBJECT = ?, CHANGE_DATE = ? WHERE ID = ?";

	@Override
	public Classroom getClassroom(Integer id) {
		return jdbctemplate.queryForObject(GET_CLASSROOM_BY_ID, new ClassroomMapper(), id);
	}

	@Override
	public List<Classroom> getClassrooms() {
		return jdbctemplate.query(GET_CLASSROOMS, new ClassroomMapper());
	}

	@Override
	public List<Classroom> getClassrooms(Integer min, Integer max) {
		return jdbctemplate.query(GET_CLASSROOM_BY_CAPACITY, new Object[] { min, max }, new ClassroomMapper());
	}

	@Override
	public Integer getClassroomID(Classroom classroom, Long currentTimeMillis) {
		return jdbctemplate.queryForObject(GET_CLASSROOM_ID, Integer.class,
				new Object[] { classroom.getNumber(), classroom.getCapacity(), classroom.getType(),
						classroom.getSubject(), currentTimeMillis, classroom.getSchoolID() });
	}

	@Override
	public Integer deleteClassroom(Integer id) {
		return jdbctemplate.update(DELETE_CLASSROOM_BY_ID, id);
	}

	@Override
	public Integer addClassroom(Classroom classroom) {
		Long currentTimeMillis = new java.util.Date().getTime();
		jdbctemplate.update(ADD_CLASSROOM,
				new Object[] { classroom.getNumber(), classroom.getCapacity(), classroom.getType(),
						classroom.getSubject(), currentTimeMillis, currentTimeMillis, classroom.getSchoolID() });
		return getClassroomID(classroom, currentTimeMillis);
	}

	@Override
	public Integer editClassroomNumber(Integer id, Integer number) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(EDIT_CLASSROOM_NUMBER, new Object[] { number, currentTimeMillis, id });
	}

	@Override
	public Integer editClassroomCapacity(Integer id, Integer capacity) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(EDIT_CLASSROOM_CAPACITY, new Object[] { capacity, currentTimeMillis, id });
	}

	@Override
	public Integer editClassroomType(Integer id, String type) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(EDIT_CLASSROOM_TYPE, new Object[] { type, currentTimeMillis, id });
	}

	@Override
	public Integer editClassroomSubject(Integer id, String subject) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(EDIT_CLASSROOM_SUBJECT, new Object[] { subject, currentTimeMillis, id });
	}

	private static class ClassroomMapper implements RowMapper<Classroom> {
		@Override
		public Classroom mapRow(ResultSet rs, int rowNum) throws SQLException {
			Classroom classroom = new Classroom();
			classroom.setId(rs.getInt("id"));
			classroom.setNumber(rs.getInt("number"));
			classroom.setCapacity(rs.getInt("capacity"));
			classroom.setType(rs.getString("type"));
			classroom.setSubject(rs.getString("subject"));
			classroom.setCreationDate(rs.getLong("creation_date"));
			classroom.setSchoolID(rs.getInt("school_id"));
			return classroom;
		}

	}

}
