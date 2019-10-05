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

	private static final String GET_COURSE_BY_ID = "SELECT * FROM mydb.COURSE WHERE ID = ?";
	private static final String GET_COURSES_BY_GROUP_ID = "SELECT C.* FROM COURSE C JOIN GROUP_COURSE GC ON C.ID = GC.COURSE_ID WHERE GC.GROUP_ID = ?";
	private static final String GET_COURSES = "SELECT * FROM mydb.COURSE";
	private static final String DELETE_COURSE_BY_ID = "DELETE FROM mydb.COURSE WHERE ID = ?";
	private static final String ADD_COURSE = "INSERT INTO mydb.COURSE (NAME, DURATION, DESCRIPTION, LOCATION, CREATION_DATE, CHANGE_DATE) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String EDIT_COURSE_NAME = "UPDATE mydb.COURSE SET NAME = ?, CHANGE_DATE = ? WHERE ID = ?";
	private static final String EDIT_COURSE_DURATION = "UPDATE mydb.COURSE SET DURATION = ?, CHANGE_DATE = ? WHERE ID = ?";
	private static final String EDIT_COURSE_DESCRIPTION = "UPDATE mydb.COURSE SET DESCRIPTION = ?, CHANGE_DATE = ? WHERE ID = ?";
	private static final String EDIT_COURSE_LOCATION = "UPDATE mydb.COURSE SET LOCATION = ?, CHANGE_DATE = ? WHERE ID = ?";

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
	public Integer deleteCourse(Integer id) {
		return jdbctemplate.update(DELETE_COURSE_BY_ID, id);
	}

	@Override
	public Integer addCourse(Course course) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbctemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(ADD_COURSE, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, course.getName());
			ps.setString(2, course.getDuration());
			ps.setString(3, course.getDescription());
			ps.setString(4, course.getLocation());
			ps.setLong(5, new java.util.Date().getTime());
			ps.setLong(6, new java.util.Date().getTime());
			return ps;
		}, keyHolder);

		return (Integer) keyHolder.getKey().intValue();
	}

	@Override
	public Integer editCourseName(Integer id, String name) {
		return jdbctemplate.update(EDIT_COURSE_NAME, new Object[] { name, new java.util.Date().getTime(), id });
	}

	@Override
	public Integer editCourseDuration(Integer id, String duration) {
		return jdbctemplate.update(EDIT_COURSE_DURATION, new Object[] { duration, new java.util.Date().getTime(), id });
	}

	@Override
	public Integer editCourseDescription(Integer id, String description) {
		return jdbctemplate.update(EDIT_COURSE_DESCRIPTION,
				new Object[] { description, new java.util.Date().getTime(), id });
	}

	@Override
	public Integer editCourseLocation(Integer id, String location) {
		return jdbctemplate.update(EDIT_COURSE_LOCATION, new Object[] { location, new java.util.Date().getTime(), id });
	}

	private static class CourseMapper implements RowMapper<Course> {
		@Override
		public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
			Course course = new Course();
			course.setId(rs.getInt("id"));
			course.setName(rs.getString("name"));
			course.setDuration(rs.getString("duration"));
			course.setDescription(rs.getString("description"));
			course.setLocation(rs.getString("location"));
			course.setCreationDate(rs.getLong("creation_date"));
			return course;
		}

	}

}
