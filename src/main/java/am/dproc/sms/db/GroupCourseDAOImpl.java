package am.dproc.sms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.modules.GroupCourseBean;

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

	@Override
	public List<GroupCourseBean> getByGroupID(int groupId) {
		return template.query(GET_GROUP_COURSE_BY_GROUP_SQL, new Object[] {groupId}, new GroupCourseMapper());
	}

	@Override
	public List<GroupCourseBean> getByCourseID(int courseId) {
		return template.query(GET_GROUP_COURSE_BY_GROUP_SQL, new Object[] {courseId}, new GroupCourseMapper());
	}
	
	private static class GroupCourseMapper implements RowMapper<GroupCourseBean>{
		@Override
		public GroupCourseBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			GroupCourseBean groupCourse = new GroupCourseBean();
			groupCourse.setId(rs.getInt("ID"));
			groupCourse.setGroupId(rs.getInt("GROUP_ID"));
			groupCourse.setCourseId(rs.getInt("COURSE_ID"));
			groupCourse.setStartDate(rs.getLong("START_DATE"));
			groupCourse.setFinished(rs.getBoolean("FINISHED"));
			groupCourse.setTeacher(rs.getInt("TEACHER"));
			return groupCourse;
		}
	}
}
