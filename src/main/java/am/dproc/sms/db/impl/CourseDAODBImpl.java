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

import am.dproc.sms.db.interfaces.CourseDAO;
import am.dproc.sms.models.Course;

@Repository
public class CourseDAODBImpl implements CourseDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String ADD_COURSE = ""
			+ "INSERT "
			+ "INTO mydb.COURSE (SCHOOL_ID, NAME, DESCRIPTION, DURATION, DURATION_UNIT_TYPE, FINISHED, CREATION_DATE) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_COURSE_BY_ID = ""
			+ "SELECT SCHOOL_ID, NAME, DESCRIPTION, DURATION, DURATION_UNIT_TYPE, FINISHED "
			+ "FROM mydb.COURSE "
			+ "WHERE ID = ?";
	private static final String GET_COURSES = ""
			+ "SELECT SCHOOL_ID, NAME, DESCRIPTION, DURATION, DURATION_UNIT_TYPE, FINISHED "
			+ "FROM mydb.COURSE";
	private static final String EDIT_COURSE_NAME = ""
			+ "UPDATE mydb.COURSE "
			+ "SET NAME = ?, CHANGE_DATE = ? "
			+ "WHERE ID = ?";
	private static final String EDIT_COURSE_DURATION = ""
			+ "UPDATE mydb.COURSE "
			+ "SET DURATION = ?, CHANGE_DATE = ? "
			+ "WHERE ID = ?";
	private static final String EDIT_COURSE_DESCRIPTION = ""
			+ "UPDATE mydb.COURSE "
			+ "SET DESCRIPTION = ?, CHANGE_DATE = ? "
			+ "WHERE ID = ?";
	private static final String EDIT_COURSE_DURATION_UNIT_TYPE = ""
			+ "UPDATE mydb.COURSE "
			+ "SET DURATION_UNIT_TYPE = ?, CHANGE_DATE = ? "
			+ "WHERE ID = ?";
	private static final String EDIT_COURSE_FINISHED = ""
			+ "UPDATE mydb.COURSE "
			+ "SET FINISHED = ?, CHANGE_DATE = ? "
			+ "WHERE ID = ?";
	private static final String DELETE_COURSE_BY_ID = ""
			+ "DELETE "
			+ "FROM mydb.COURSE "
			+ "WHERE ID = ?";
	private static final String GET_COURSES_BY_GROUP_ID = ""
			+ "SELECT C.* "
			+ "FROM COURSE C JOIN GROUP_COURSE GC ON C.ID = GC.COURSE_ID "
			+ "WHERE GC.GROUP_ID = ?";

	@Override
	public Integer addCourse(Course course) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbctemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(ADD_COURSE, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, course.getSchoolID());
			ps.setString(2, course.getName());
			ps.setString(3, course.getDescription());
			ps.setString(4, course.getDuration());
			ps.setString(5, course.getDurationUnitType());
			ps.setBoolean(6, course.getFinished());
			ps.setLong(7, System.currentTimeMillis());
			return ps;
		}, keyHolder);

		return (Integer) keyHolder.getKey().intValue();
	}

	@Override
	public Course getCourse(Integer id) {
		return jdbctemplate.queryForObject(GET_COURSE_BY_ID, new CourseMapper(), id);
	}

	@Override
	public List<Course> getCourses() {
		return jdbctemplate.query(GET_COURSES, new CourseMapper());
	}

	@Override
	public List<Course> getCoursesByGroupId(Integer groupId) {
		return jdbctemplate.query(GET_COURSES_BY_GROUP_ID, new CourseMapper(), groupId);
	}

	@Override
	public Integer editCourseName(Integer id, String name) {
		return jdbctemplate.update(EDIT_COURSE_NAME, name, System.currentTimeMillis(), id );
	}

	@Override
	public Integer editCourseDescription(Integer id, String description) {
		return jdbctemplate.update(EDIT_COURSE_DESCRIPTION, description, System.currentTimeMillis(), id );
	}

	@Override
	public Integer editCourseDuration(Integer id, String duration) {
		return jdbctemplate.update(EDIT_COURSE_DURATION, duration, System.currentTimeMillis(), id );
	}

	@Override
	public Integer editCourseDurationUnitType(Integer id, String durationUnitType) {
		return jdbctemplate.update(EDIT_COURSE_DURATION_UNIT_TYPE, durationUnitType, System.currentTimeMillis(), id );
	}

	@Override
	public Integer editCourseFinished(Integer id, Boolean finished) {
		return jdbctemplate.update(EDIT_COURSE_FINISHED, finished, System.currentTimeMillis(), id );
	}

	@Override
	public Integer deleteCourse(Integer id) {
		return jdbctemplate.update(DELETE_COURSE_BY_ID, id);
	}

	private static class CourseMapper implements RowMapper<Course> {
		@Override
		public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

			Course course = new Course();
			course.setId(rs.getInt("ID"));
			course.setId(rs.getInt("SCHOOL_ID"));
			course.setName(rs.getString("NAME"));
			course.setDescription(rs.getString("DESCRIPTION"));
			course.setDuration(rs.getString("DURATION"));
			course.setDurationUnitType(rs.getString("DURATION_UNIT_TYPE"));
			course.setFinished(rs.getBoolean("FINISHED"));

			return course;
		}

	}

}
