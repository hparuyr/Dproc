package am.dproc.sms.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
	public Integer deleteClassroom(Integer id) {
		return jdbctemplate.update(DELETE_CLASSROOM_BY_ID, id);
	}

	@Override
	public Integer addClassroom(Classroom classroom) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbctemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(ADD_CLASSROOM, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, classroom.getNumber());
			ps.setInt(2, classroom.getCapacity());
			ps.setString(3, classroom.getType());
			ps.setString(4, classroom.getSubject());
			ps.setLong(5, new java.util.Date().getTime());
			ps.setLong(6, new java.util.Date().getTime());
			ps.setInt(7, classroom.getSchoolID());
			return ps;
		}, keyHolder);

		return (Integer) keyHolder.getKey().intValue();
	}

	@Override
	public Integer editClassroomNumber(Integer id, Integer number) {
		return jdbctemplate.update(EDIT_CLASSROOM_NUMBER, new Object[] { number, new java.util.Date().getTime(), id });
	}

	@Override
	public Integer editClassroomCapacity(Integer id, Integer capacity) {
		return jdbctemplate.update(EDIT_CLASSROOM_CAPACITY,
				new Object[] { capacity, new java.util.Date().getTime(), id });
	}

	@Override
	public Integer editClassroomType(Integer id, String type) {
		return jdbctemplate.update(EDIT_CLASSROOM_TYPE, new Object[] { type, new java.util.Date().getTime(), id });
	}

	@Override
	public Integer editClassroomSubject(Integer id, String subject) {
		return jdbctemplate.update(EDIT_CLASSROOM_SUBJECT,
				new Object[] { subject, new java.util.Date().getTime(), id });
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
