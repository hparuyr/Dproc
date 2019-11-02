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
import am.dproc.sms.enums.ClassroomType;
import am.dproc.sms.models.Classroom;

@Repository
public class ClassroomDAODBImpl implements ClassroomDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String ADD_CLASSROOM = ""
			+ "INSERT "
			+ "INTO mydb.CLASSROOM (SCHOOL_ID, NAME, CAPACITY, TYPE, SUBJECT, CREATION_DATE) "
			+ "VALUES(?, ?, ?, ?, ?, ?)";
	private static final String GET_CLASSROOM_BY_ID = ""
			+ "SELECT ID, SCHOOL_ID, NAME, CAPACITY, TYPE, SUBJECT "
			+ "FROM mydb.CLASSROOM "
			+ "WHERE ID = ?";
	private static final String GET_CLASSROOMS = ""
			+ "SELECT ID, SCHOOL_ID, NAME, CAPACITY, TYPE, SUBJECT "
			+ "FROM mydb.CLASSROOM";
	private static final String GET_CLASSROOM_BY_CAPACITY = ""
			+ "SELECT ID, SCHOOL_ID, NAME, CAPACITY, TYPE, SUBJECT "
			+ "FROM mydb.CLASSROOM "
			+ "WHERE CAPACITY BETWEEN ? AND ?";
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
			if (ClassroomType.FOR_SEMINAR.toString().toLowerCase().equals(classroom.getType().toLowerCase())) {
				ps.setInt(4, ClassroomType.FOR_SEMINAR.index());
			} else if (ClassroomType.FOR_LECTURE.toString().toLowerCase().equals(classroom.getType().toLowerCase())) {
				ps.setInt(4, ClassroomType.FOR_LECTURE.index());
			} else if (ClassroomType.GENERAL_PURPOSE.toString().toLowerCase().equals(classroom.getType().toLowerCase())) {
				ps.setInt(4, ClassroomType.GENERAL_PURPOSE.index());
			}
			ps.setString(5, classroom.getSubject());
			ps.setLong(6, System.currentTimeMillis());
			return ps;
		}, keyHolder);

		return keyHolder.getKey().intValue();
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
	public List<Classroom> getClassroomsByCapacity(Integer min, Integer max) {
		return jdbctemplate.query(GET_CLASSROOM_BY_CAPACITY, new ClassroomMapper(), min, max);
	}

	@Override
	public Integer updateClassroomName(Integer id, String name) {
		return jdbctemplate.update(EDIT_CLASSROOM_NAME, name, System.currentTimeMillis(), id);
	}

	@Override
	public Integer updateClassroomCapacity(Integer id, Integer capacity) {
		return jdbctemplate.update(EDIT_CLASSROOM_CAPACITY, capacity, System.currentTimeMillis(), id);
	}

	@Override
	public Integer updateClassroomType(Integer id, String type) {
		if (ClassroomType.FOR_LECTURE.toString().toLowerCase().equals(type.toLowerCase())) {
			return jdbctemplate.update(EDIT_CLASSROOM_TYPE, ClassroomType.FOR_LECTURE.index(),
					System.currentTimeMillis(), id);
		} else if (ClassroomType.FOR_SEMINAR.toString().toLowerCase().equals(type.toLowerCase())) {
			return jdbctemplate.update(EDIT_CLASSROOM_TYPE, ClassroomType.FOR_SEMINAR.index(),
					System.currentTimeMillis(), id);
		} else if (ClassroomType.GENERAL_PURPOSE.toString().toLowerCase().equals(type.toLowerCase())) {
			return jdbctemplate.update(EDIT_CLASSROOM_TYPE, ClassroomType.GENERAL_PURPOSE.index(),
					System.currentTimeMillis(), id);
		}
		return -1;
	}

	@Override
	public Integer updateClassroomSubject(Integer id, String subject) {
		return jdbctemplate.update(EDIT_CLASSROOM_SUBJECT, subject, System.currentTimeMillis(), id);
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
			if (ClassroomType.FOR_SEMINAR.index() == rs.getInt("TYPE")) {
				classroom.setType("For Seminar");
			} else if (ClassroomType.FOR_LECTURE.index() == rs.getInt("TYPE")) {
				classroom.setType("For Lecture");
			} else if (ClassroomType.GENERAL_PURPOSE.index() == rs.getInt("TYPE")) {
				classroom.setType("General Purpose");
			}
			classroom.setSubject(rs.getString("SUBJECT"));
			return classroom;
		}

	}

}
