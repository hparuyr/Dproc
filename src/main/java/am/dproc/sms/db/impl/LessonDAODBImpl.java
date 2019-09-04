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
	private static final String GET_LESSON_ID_BY_COURSE_ID = "SELECT ID FROM mydb.LESSON WHERE NAME = ? AND COURSE_ID = ?";
	private static final String ADD_LESSON = "INSERT INTO mydb.LESSON (NAME, CONTENT, CREATION_DATE, CHANGE_DATE, COURSE_ID) VALUES(?, ?, ?, ?, ?)";
	private static final String DELETE_LESSON_BY_ID = "DELETE FROM mydb.LESSON WHERE ID = ?";
	private static final String EDIT_LESSON_NAME = "UPDATE mydb.LESSON SET NAME = ?, CHANGE_DATE = ? WHERE ID = ?";
	private static final String EDIT_LESSON_CONTENT = "UPDATE mydb.LESSON SET CONTENT = ?, CHANGE_DATE = ? WHERE ID = ?";

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
	public List<Integer> getLessonsIDs(Integer courseID) {
		return jdbctemplate.queryForList(GET_LESSONS_IDs_BY_COURSE_ID, Integer.class, courseID);
	}

	@Override
	public Integer getLessonID(String name) {
		return jdbctemplate.queryForObject(GET_LESSON_ID, Integer.class, name);
	}

	@Override
	public Integer getLessonID(String name, Integer courseID) {
		return jdbctemplate.queryForObject(GET_LESSON_ID_BY_COURSE_ID, Integer.class, new Object[] { name, courseID });
	}

	@Override
	public ResponseEntity<Integer> deleteLesson(Integer id) {
		if (jdbctemplate.update(DELETE_LESSON_BY_ID, id) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
	}

	@Override
	public ResponseEntity<Integer> addLesson(Lesson lesson, Integer courseID) {
		Long currentTimeMillis = new java.util.Date().getTime();
		if (jdbctemplate.update(ADD_LESSON, new Object[] { lesson.getName(), lesson.getContent(), currentTimeMillis,
				currentTimeMillis, courseID }) == 1) {
			return ResponseEntity.status(HttpStatus.CREATED).body(getLessonID(lesson.getName(), courseID));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
	}

	@Override
	public ResponseEntity<Integer> editLessonName(Integer id, String name) {
		Long currentTimeMillis = new java.util.Date().getTime();
		if (jdbctemplate.update(EDIT_LESSON_NAME, new Object[] { name, currentTimeMillis, id }) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
	}

	@Override
	public ResponseEntity<Integer> editLessonContent(Integer id, String content) {
		Long currentTimeMillis = new java.util.Date().getTime();
		if (jdbctemplate.update(EDIT_LESSON_CONTENT, new Object[] { content, currentTimeMillis, id }) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
	}

	private static class LessonMapper implements RowMapper<Lesson> {
		@Override
		public Lesson mapRow(ResultSet rs, int rowNum) throws SQLException {
			Lesson lesson = new Lesson();
			lesson.setId(rs.getInt("id"));
			lesson.setName(rs.getString("name"));
			lesson.setContent(rs.getString("content"));
			lesson.setCourseID(rs.getInt("course_id"));
			lesson.setCreationDate(new Date(rs.getLong("creation_date")));
			lesson.setChangeDate(new Date(rs.getLong("change_date")));
			return lesson;
		}

	}

}
