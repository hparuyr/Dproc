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

	private static final String ADD_CLASSROOM = ""
			+ "INSERT "
			+ "INTO mydb.CLASSROOM (NUMBER, CAPACITY, TYPE, SUBJECT, CREATION_DATE, CHANGE_DATE, SCHOOL_ID) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_CLASSROOM_BY_ID = ""
			+ "SELECT ID, SCHOOL_ID, NAME, CAPACITY, TYPE, SUBJECT, CREATION_DATE "
			+ "FROM mydb.CLASSROOM "
			+ "WHERE ID = ?";
	private static final String GET_CLASSROOMS = ""
			+ "SELECT ID, SCHOOL_ID, NAME, CAPACITY, TYPE, SUBJECT, CREATION_DATE "
			+ "FROM mydb.CLASSROOM";
	private static final String GET_CLASSROOM_BY_CAPACITY = ""
			+ "SELECT ID, SCHOOL_ID, NAME, CAPACITY, TYPE, SUBJECT, CREATION_DATE "
			+ "FROM mydb.CLASSROOM "
			+ "WHERE CAPACITY "
			+ "BETWEEN ? AND ?";
	private static final String EDIT_CLASSROOM_NAME = ""
			+ "UPDATE mydb.CLASSROOM "
			+ "SET NAME = ?, CHANGE_DATE = ? "
			+ "WHERE ID = ?";
	private static final String EDIT_CLASSROOM_CAPACITY = ""
			+ "UPDATE mydb.CLASSROOM "
			+ "SET CAPACITY = ?, CHANGE_DATE = ? "
			+ "WHERE ID = ?";
	private static final String EDIT_CLASSROOM_TYPE = ""
			+ "UPDATE mydb.CLASSROOM "
			+ "SET TYPE = ?, CHANGE_DATE = ? "
			+ "WHERE ID = ?";
	private static final String EDIT_CLASSROOM_SUBJECT = ""
			+ "UPDATE mydb.CLASSROOM "
			+ "SET SUBJECT = ?, CHANGE_DATE = ? "
			+ "WHERE ID = ?";
	private static final String DELETE_CLASSROOM_BY_ID = ""
			+ "DELETE "
			+ "FROM mydb.CLASSROOM "
			+ "WHERE ID = ?";
	
	@Override
	public Integer addClassroom(Classroom classroom) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbctemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(ADD_CLASSROOM, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, classroom.getSchoolID());
			ps.setString(2, classroom.getName());
			ps.setInt(3, classroom.getCapacity());
			ps.setString(4, classroom.getType());
			ps.setString(5, classroom.getSubject());
			ps.setLong(6, System.currentTimeMillis());
			ps.setLong(7, System.currentTimeMillis());
			return ps;
		}, keyHolder);

		return (Integer) keyHolder.getKey().intValue();
	}

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
		return jdbctemplate.query(GET_CLASSROOM_BY_CAPACITY, new ClassroomMapper(), min, max);
	}

	@Override
	public Integer editClassroomName(Integer id, String name) {
		return jdbctemplate.update(EDIT_CLASSROOM_NAME, name, System.currentTimeMillis(), id );
	}

	@Override
	public Integer editClassroomCapacity(Integer id, Integer capacity) {
		return jdbctemplate.update(EDIT_CLASSROOM_CAPACITY, capacity, System.currentTimeMillis(), id );
	}

	@Override
	public Integer editClassroomType(Integer id, String type) {
		return jdbctemplate.update(EDIT_CLASSROOM_TYPE, type, System.currentTimeMillis(), id );
	}

	@Override
	public Integer editClassroomSubject(Integer id, String subject) {
		return jdbctemplate.update(EDIT_CLASSROOM_SUBJECT, subject, System.currentTimeMillis(), id );
	}

	@Override
	public Integer deleteClassroom(Integer id) {
		return jdbctemplate.update(DELETE_CLASSROOM_BY_ID, id);
	}

	private static class ClassroomMapper implements RowMapper<Classroom> {
		@Override
		public Classroom mapRow(ResultSet rs, int rowNum) throws SQLException {
			Classroom classroom = new Classroom();
			
			classroom.setId(rs.getInt("ID"));
			classroom.setSchoolID(rs.getInt("SCHOOL_ID"));
			classroom.setName(rs.getString("NAME"));
			classroom.setCapacity(rs.getInt("CAPACITY"));
			classroom.setType(rs.getString("TYPE"));
			classroom.setSubject(rs.getString("SUBJECT"));
			classroom.setCreationDate(rs.getLong("CREATION_DATE"));
			
			return classroom;
		}

	}

}
