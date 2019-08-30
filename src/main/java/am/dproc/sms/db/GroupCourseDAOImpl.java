package am.dproc.sms.db;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GroupCourseDAOImpl implements GroupCourseDAO{
	@Autowired
	JdbcTemplate template;
	
	public static String CREATE_GROUP_COURSE_SQL = "INSERT INTO GROUP_COURSE(COURSE_ID, GROUP_ID, START_DATE, FINISHED, TEACHER)"
			+ " VALUES (?, ?, ?, ?, ?)";
	public static String GET_GROUP_COURSE_BY_GROUP_SQL = "SELECT * FROM GROUP_COURSE WHERE GROUP_ID=?";
	public static String GET_GROUP_COURSE_BY_COURSE_SQL = "SELECT * FROM GROUP_COURSE WHERE COURSE_ID=?";
	public static String GET_GROUP_COURSES_SQL = "SELECT * FROM GROUP_COURSE";
	
	@Override
	public int create(int groupId, int courseId, long startDate, boolean finished, int teacherId) {
		return template.update(CREATE_GROUP_COURSE_SQL, groupId, courseId, startDate, finished, teacherId);
	}
}
