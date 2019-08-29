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
	
	@Override
	public int create(int groupId, int courseId) {
		template.update(CREATE_GROUP_COURSE_SQL, 1,2,3,4,5);
		// TODO Auto-generated method stub
		return 0;
	}
}
