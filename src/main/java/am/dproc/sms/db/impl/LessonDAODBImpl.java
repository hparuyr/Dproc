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

import am.dproc.sms.db.interfaces.LessonDAO;
import am.dproc.sms.models.Lesson;

@Repository
public class LessonDAODBImpl implements LessonDAO {

	@Autowired
	private JdbcTemplate jdbctemplate;

	private static final String ADD_LESSON = ""
			+ "INSERT "
			+ "INTO mydb.LESSON (COURSE_ID, NAME, CONTENT, CREATION_DATE) "
			+ "VALUES(?, ?, ?, ?, ?)";
	private static final String GET_LESSONS = ""
			+ "SELECT COURSE_ID, NAME, CONTENT "
			+ "FROM mydb.LESSON";
	private static final String GET_LESSONS_BY_ID = ""
			+ "SELECT COURSE_ID, NAME, CONTENT "
			+ "FROM mydb.LESSON "
			+ "WHERE ID = ?";
	private static final String GET_LESSONS_BY_COURSE_ID = ""
			+ "SELECT COURSE_ID, NAME, CONTENT "
			+ "FROM mydb.LESSON "
			+ "WHERE COURSE_ID = ?";
	private static final String EDIT_LESSON_NAME = ""
			+ "UPDATE mydb.LESSON "
			+ "SET NAME = ?, CHANGE_DATE = ? "
			+ "WHERE ID = ?";
	private static final String EDIT_LESSON_CONTENT = ""
			+ "UPDATE mydb.LESSON "
			+ "SET CONTENT = ?, CHANGE_DATE = ? "
			+ "WHERE ID = ?";
	private static final String DELETE_LESSON_BY_ID = ""
			+ "DELETE "
			+ "FROM mydb.LESSON "
			+ "WHERE ID = ?";

	@Override
	public Integer addLesson(Lesson lesson, Integer courseID) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbctemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(ADD_LESSON, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, courseID);
			ps.setString(2, lesson.getName());
			ps.setString(3, lesson.getContent());
			ps.setLong(4, System.currentTimeMillis());
			return ps;
		}, keyHolder);

		return (Integer) keyHolder.getKey().intValue();
	}

	@Override
	public Lesson getLesson(Integer id) {
		return jdbctemplate.queryForObject(GET_LESSONS_BY_ID, new LessonMapper(), id);
	}

	@Override
	public List<Lesson> getLessonsOfCourse(Integer courseID) {
		return jdbctemplate.query(GET_LESSONS_BY_COURSE_ID, new LessonMapper(), courseID);
	}

	@Override
	public List<Lesson> getAllLessons() {
		return jdbctemplate.query(GET_LESSONS, new LessonMapper());
	}

	@Override
	public Integer editLessonName(Integer id, String name) {
		return jdbctemplate.update(EDIT_LESSON_NAME, name, System.currentTimeMillis(), id);
	}

	@Override
	public Integer editLessonContent(Integer id, String content) {
		return jdbctemplate.update(EDIT_LESSON_CONTENT, content, System.currentTimeMillis(), id);
	}

	@Override
	public Integer deleteLesson(Integer id) {
		return jdbctemplate.update(DELETE_LESSON_BY_ID, id);
	}

	private static class LessonMapper implements RowMapper<Lesson> {
		@Override
		public Lesson mapRow(ResultSet rs, int rowNum) throws SQLException {
			Lesson lesson = new Lesson();

			lesson.setId(rs.getInt("ID"));
			lesson.setId(rs.getInt("COURSE_ID"));
			lesson.setName(rs.getString("NAME"));
			lesson.setContent(rs.getString("CONTENT"));
			return lesson;
		}

	}

}
