package am.dproc.sms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.modules.CourseBean;

@Repository
public class CourseDAOImpl implements CourseDAO {
	@Autowired
	JdbcTemplate template;
	
	public static final String CREATE_COURSE_SQL = "INSERT INTO COURSE(`NAME`, `DURATION`, `DESCRIPTION`, `LOCATION`)"
			+ " VALUES (?, ?, ?, ?)";
	public static final String GET_COURSE_BY_ID_SQL = "SELECT * FROM COURSE WHERE ID=?";
	public static final String GET_COURSE_BY_NAME_SQL = "SELECT * FROM COURSE WHERE NAME=?";
	public static final String GET_COURSES_SQL = "SELECT * FROM COURSE";
			
	@Override
	public int create(String name, int duration, String description, String location) {
		return template.update(CREATE_COURSE_SQL, name, duration, description, location);
	}

	@Override
	public CourseBean getById(int id) {
		return template.queryForObject(GET_COURSE_BY_ID_SQL, new Object[] {id}, new CourseMapper());
	}

	@Override
	public List<CourseBean> getByName(String name) {
		return template.query(GET_COURSE_BY_ID_SQL, new Object[] {name}, new CourseMapper());
	}
	
	@Override
	public List<CourseBean> getAll() {
		return template.query(GET_COURSES_SQL, new CourseMapper());
	}

	private static class CourseMapper implements RowMapper<CourseBean>{

		@Override
		public CourseBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			CourseBean course = new CourseBean();
			course.setId(rs.getInt("ID"));
			course.setName(rs.getString("NAME"));
			course.setDuration(rs.getInt("DURATION"));
			course.setDescription(rs.getString("DESCRIPTION"));
			course.setLocation(rs.getString("LOCATION"));
			return course;
		}
		
	}
}
