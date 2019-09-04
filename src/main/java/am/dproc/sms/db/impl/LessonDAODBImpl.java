package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.root.LessonDAO;
import am.dproc.sms.models.Lesson;

@Repository
public class LessonDAODBImpl implements LessonDAO {

	@Autowired
	private JdbcTemplate jdbctemplate;

	private static final String GET_LESSONS = "SELECT * FROM mydb.LESSON";
	private static final String GET_LESSONS_BY_ID = "SELECT * FROM mydb.LESSON WHERE ID = ?";
	private static final String GET_LESSONS_BY_COURSE_ID = "SELECT * FROM mydb.LESSON WHERE COURSE_ID = ?";
	private static final String GET_LESSONS_IDs_BY_COURSE_ID = "SELECT ID FROM mydb.LESSON WHERE COURSE_ID = ?";
	private static final String GET_LESSON_ID = "SELECT ID FROM mydb.LESSON WHERE NAME = ?";
	private static final String ADD_LESSON = "INSERT INTO mydb.LESSON (NAME, CONTENT, COURSE_ID) VALUES(?, ?, ?)";
	private static final String DELETE_LESSONS_BY_COURSE_ID = "DELETE FROM mydb.LESSON WHERE COURSE_ID = ?";
	private static final String DELETE_LESSON_BY_ID = "DELETE FROM mydb.LESSON WHERE ID = ?";

	// Works
	@Override
	public Lesson getLesson(Integer id) {
		return jdbctemplate.queryForObject(GET_LESSONS_BY_ID, new LessonMapper(), id);
	}

	// Works
	@Override
	public List<Lesson> getLessonsOfCourse(Integer courseID) {
		return jdbctemplate.query(GET_LESSONS_BY_COURSE_ID, new LessonMapper(), courseID);
	}

	// Works
	@Override
	public List<Lesson> getAllLessons() {
		return jdbctemplate.query(GET_LESSONS, new LessonMapper());
	}

	// Works
	@Override
	public List<Integer> getLessonsIDs(Integer courseID) {
		return jdbctemplate.queryForList(GET_LESSONS_IDs_BY_COURSE_ID, Integer.class, courseID);
	}

	@Override
	public Integer getLessonID(String name) {
		return jdbctemplate.queryForObject(GET_LESSON_ID, Integer.class, name);
	}

	// Works
	@Override
	public Integer deleteLesson(Integer id) {
		return jdbctemplate.update(DELETE_LESSON_BY_ID, id);
	}

	// Works
	@Override
	public Integer deleteLessonsOfCourse(Integer courseID) {
		return jdbctemplate.update(DELETE_LESSONS_BY_COURSE_ID, courseID);
	}

	// ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
	@Override
	public Integer deleteAllLessons() {
		return null;
	}

	// Works
	@Override
	public Integer addLesson(Lesson lesson, Integer courseID) {
		return jdbctemplate.update(ADD_LESSON, new Object[] { lesson.getName(), lesson.getContent(), courseID });
	}

	private static class LessonMapper implements RowMapper<Lesson> {
		@Override
		public Lesson mapRow(ResultSet rs, int rowNum) throws SQLException {
			Lesson lesson = new Lesson();
			lesson.setId(rs.getInt("id"));
			lesson.setName(rs.getString("name"));
			lesson.setContent(rs.getString("content"));
			lesson.setCourseID(rs.getInt("course_id"));
			return lesson;
		}

	}

}
