package am.dproc.sms.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.GroupCourseDAO;
import am.dproc.sms.models.GroupCourse;

@Repository
public class GroupCourseDAOImpl implements GroupCourseDAO {
	@Autowired
	JdbcTemplate template;

	public static String CREATE_SQL = "INSERT INTO GROUP_COURSE(GROUP_ID, COURSE_ID, TEACHER_ID, START_DATE, IS_FINISHED, CREATION_DATE, CHANGE_DATE)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
	public static String GET_BY_ID_SQL = "SELECT * FROM GROUP_COURSE WHERE ID=?";
	public static String GET_BY_GROUP_AND_COURSE_SQL = "SELECT * FROM GROUP_COURSE WHERE GROUP_ID=? AND COURSE_ID=?";
	public static String GET_BY_GROUP_SQL = "SELECT * FROM GROUP_COURSE WHERE GROUP_ID=?";
	public static String GET_BY_COURSE_SQL = "SELECT * FROM GROUP_COURSE WHERE COURSE_ID=?";
	public static String GET_ALL_SQL = "SELECT * FROM GROUP_COURSE";
	public static String GET_BY_TEACHER_ID_SQL = "SELECT * FROM GROUP_COURSE WHERE TEACHER_ID = ?";
	public static String UPDATE_SQL = "UPDATE GROUP_COURSE SET GROUP_ID=?, COURSE_ID=?, TEACHER_ID=?, START_DATE=?, IS_FINISHED=?, CHANGE_DATE=? WHERE ID=?";
	public static String DELETE_BY_GROUP_SQL = "DELETE FROM GROUP_COURSE WHERE GROUP_ID=?";
	public static String DELETE_BY_COURSE_SQL = "DELETE FROM GROUP_COURSE WHERE COURSE_ID=?";
	public static String DELETE_ALL_SQL = "DELETE FROM GROUP_COURSE";

	@Override
	public int create(GroupCourse groupCourse) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		long currentTimeStamp = System.currentTimeMillis();
//		return template.update(CREATE_SQL, groupId, courseId, teacherId, startDate, isFinished, currentTimeStamp, currentTimeStamp);
		template.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, groupCourse.getGroupId());
				ps.setInt(2, groupCourse.getCourseId());
				ps.setInt(3, groupCourse.getTeacherId());
				ps.setLong(4, groupCourse.getStartDate());
				ps.setBoolean(5, groupCourse.isFinished());
				ps.setLong(6, currentTimeStamp);
				ps.setLong(7, currentTimeStamp);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public GroupCourse getById(int id) {
		return template.queryForObject(GET_BY_ID_SQL, new Object[] { id }, new GroupCourseMapper());
	}

	@Override
	public GroupCourse getByGroupAndCourse(int groupId, int courseId) {
		return template.queryForObject(GET_BY_GROUP_AND_COURSE_SQL, new Object[] { groupId, courseId },
				new GroupCourseMapper());
	}

	@Override
	public List<GroupCourse> getByGroupID(int groupId) {
		return template.query(GET_BY_GROUP_SQL, new Object[] { groupId }, new GroupCourseMapper());
	}

	@Override
	public List<GroupCourse> getByCourseID(int courseId) {
		return template.query(GET_BY_COURSE_SQL, new Object[] { courseId }, new GroupCourseMapper());
	}

	@Override
	public List<GroupCourse> getAll() {
		return template.query(GET_ALL_SQL, new GroupCourseMapper());
	}
	
	@Override
	public List<GroupCourse> getByTeacherID(Integer id) {
		return template.query(GET_BY_TEACHER_ID_SQL, new GroupCourseMapper(), id);
	}

	@Override
	public int update(GroupCourse groupCourse) {
		return template.update(UPDATE_SQL, groupCourse.getGroupId(), groupCourse.getCourseId(),
				groupCourse.getTeacherId(), groupCourse.getStartDate(), groupCourse.isFinished(),
				System.currentTimeMillis(), groupCourse.getId());
	}

	@Override
	public int deleteByGroupID(int groupId) {
		return template.update(DELETE_BY_GROUP_SQL, groupId);
	}

	@Override
	public int deleteByCourseID(int courseId) {
		return template.update(DELETE_BY_COURSE_SQL, courseId);
	}

	@Override
	public int deleteAll() {
		return template.update(DELETE_ALL_SQL);
	}

	private static class GroupCourseMapper implements RowMapper<GroupCourse> {
		@Override
		public GroupCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
			GroupCourse groupCourse = new GroupCourse();
			groupCourse.setId(rs.getInt("ID"));
			groupCourse.setGroupId(rs.getInt("GROUP_ID"));
			groupCourse.setCourseId(rs.getInt("COURSE_ID"));
			groupCourse.setTeacherId(rs.getInt("TEACHER_ID"));
			groupCourse.setStartDate(rs.getLong("START_DATE"));
			return groupCourse;
		}
	}
}
