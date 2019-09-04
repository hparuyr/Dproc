package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public Integer getCourseID(String name) {
		return jdbctemplate.queryForObject(GET_COURSE_ID, Integer.class, name);
	}

	@Override
	public ResponseEntity<Integer> deleteCourse(Integer id) {
		if (jdbctemplate.update(DELETE_COURSE_BY_ID, id) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
	}

	@Override
	public ResponseEntity<Integer> addCourse(Course course) {
		Long currentTimeMillis = new java.util.Date().getTime();
		if (jdbctemplate.update(ADD_COURSE, new Object[] { course.getName(), course.getDuration(),
				course.getDescription(), course.getLocation(), currentTimeMillis, currentTimeMillis }) == 1) {
			return ResponseEntity.status(HttpStatus.CREATED).body(getCourseID(course.getName()));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
	}

	@Override
	public ResponseEntity<Integer> editCourseName(Integer id, String name) {
		Long currentTimeMillis = new java.util.Date().getTime();
		if (jdbctemplate.update(EDIT_COURSE_NAME, new Object[] { name, currentTimeMillis, id }) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
	}

	@Override
	public ResponseEntity<Integer> editCourseDuration(Integer id, String duration) {
		Long currentTimeMillis = new java.util.Date().getTime();
		if (jdbctemplate.update(EDIT_COURSE_DURATION, new Object[] { duration, currentTimeMillis, id }) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
	}

	@Override
	public ResponseEntity<Integer> editCourseDescription(Integer id, String description) {
		Long currentTimeMillis = new java.util.Date().getTime();
		if (jdbctemplate.update(EDIT_COURSE_DESCRIPTION, new Object[] { description, currentTimeMillis, id }) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
	}

	@Override
	public ResponseEntity<Integer> editCourseLocation(Integer id, String location) {
		Long currentTimeMillis = new java.util.Date().getTime();
		if (jdbctemplate.update(EDIT_COURSE_LOCATION, new Object[] { location, currentTimeMillis, id }) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
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
			course.setCreationDate(new Date(rs.getLong("creation_date")));
			course.setChangeDate(new Date(rs.getLong("change_date")));
			return course;
		}

	}

}
