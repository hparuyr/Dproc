package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.root.CourseDAO;
import am.dproc.sms.models.Course;

@Repository
public class CourseDAODBImpl implements CourseDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String GET_COURSE_BY_ID = "SELECT * FROM mydb.COURSE WHERE ID = ?";
	private static final String GET_COURSE_ID = "SELECT ID FROM mydb.COURSE WHERE NAME = ?";
	private static final String GET_COURSES = "SELECT * FROM mydb.COURSE";
	private static final String DELETE_COURSE_BY_ID = "DELETE FROM mydb.COURSE WHERE ID = ?";
	private static final String ADD_COURSE = "INSERT INTO mydb.COURSE (NAME, DURATION, DESCRIPTION, LOCATION) VALUES(?, ?, ?, ?)";

	// Works
	@Override
	public Course getCourse(Integer id) {
		// How to handle error
		return jdbctemplate.queryForObject(GET_COURSE_BY_ID, new CourseMapper(), id);
	}

	// Works
	@Override
	public List<Course> getCourses() {
		return jdbctemplate.query(GET_COURSES, new CourseMapper());
	}

	@Override
	public Integer getCourseID(String name) {
		return jdbctemplate.queryForObject(GET_COURSE_ID, Integer.class, name);
	}

	// Works
	@Override
	public Integer deleteCourse(Integer id) {
		return jdbctemplate.update(DELETE_COURSE_BY_ID, id);
	}

	// Works
	@Override
	public Integer addCourse(Course course) {
		return jdbctemplate.update(ADD_COURSE,
				new Object[] { course.getName(), course.getDuration(), course.getDescription(), course.getLocation() });
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
			return course;
		}

	}

}
