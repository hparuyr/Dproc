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

import am.dproc.sms.db.root.TopicDAO;
import am.dproc.sms.models.Topic;

@Repository
public class TopicDAODBImpl implements TopicDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String DELETE_TOPICS_BY_ID = "DELETE FROM mydb.TOPIC WHERE ID = ?";
	private static final String ADD_TOPIC = "INSERT INTO mydb.TOPIC (VIDEO_URL, WEB_PAGE_URL, LESSON_ID, CREATION_DATE, CHANGE_DATE) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_TOPICS = "SELECT * FROM mydb.TOPIC";
	private static final String GET_TOPICS_BY_LESSON_ID = "SELECT * FROM mydb.TOPIC WHERE LESSON_ID = ?";
	private static final String GET_TOPIC_ID = "SELECT ID FROM mydb.TOPIC WHERE VIDEO_URL = ? AND WEB_PAGE_URL = ? AND LESSON_ID = ?";
	private static final String EDIT_TOPIC_VIDEO_URL = "UPDATE mydb.TOPIC SET VIDEO_URL = ?, CHANGE_DATE = ? WHERE ID = ?";
	private static final String EDIT_TOPIC_WEB_PAGE_URL = "UPDATE mydb.TOPIC SET WEB_PAGE_URL = ?, CHANGE_DATE = ? WHERE ID = ?";
	private static final String GET_TOPIC = "SELECT * FROM mydb.TOPIC WHERE ID = ?";

	@Override
	public Topic getTopic(Integer id) {
		return jdbctemplate.queryForObject(GET_TOPIC, new TopicMapper(), id);
	}

	@Override
	public List<Topic> getAllTopics() {
		List<Topic> list = jdbctemplate.query(GET_TOPICS, new TopicMapper());
		return list;
	}

	@Override
	public List<Topic> getTopicsOfLesson(Integer lessonID) {
		return jdbctemplate.query(GET_TOPICS_BY_LESSON_ID, new TopicMapper(), lessonID);
	}

	@Override
	public Integer getTopicID(String videoURL, String webPageURL, Integer lessonID) {
		return jdbctemplate.queryForObject(GET_TOPIC_ID, Integer.class,
				new Object[] { videoURL, webPageURL, lessonID });
	}

	@Override
	public ResponseEntity<Integer> deleteTopic(Integer id) {
		if (jdbctemplate.update(DELETE_TOPICS_BY_ID, id) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
	}

	@Override
	public ResponseEntity<Integer> addTopic(Topic topic) {
		Long currentTimeMillis = new java.util.Date().getTime();
		if (jdbctemplate.update(ADD_TOPIC, new Object[] { topic.getVideoURL(), topic.getWebPageURL(),
				topic.getLessonID(), currentTimeMillis, currentTimeMillis }) == 1) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(getTopicID(topic.getVideoURL(), topic.getWebPageURL(), topic.getLessonID()));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
	}

	@Override
	public ResponseEntity<Integer> editTopicVideoURL(Integer id, String videoURL) {
		Long currentTimeMillis = new java.util.Date().getTime();
		if (jdbctemplate.update(EDIT_TOPIC_VIDEO_URL, new Object[] { videoURL, currentTimeMillis, id }) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
	}

	@Override
	public ResponseEntity<Integer> editTopicWebPageURL(Integer id, String webPageURL) {
		Long currentTimeMillis = new java.util.Date().getTime();
		if (jdbctemplate.update(EDIT_TOPIC_WEB_PAGE_URL, new Object[] { webPageURL, currentTimeMillis, id }) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
	}

	private static class TopicMapper implements RowMapper<Topic> {
		@Override
		public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
			Topic topic = new Topic();
			topic.setId(rs.getInt("id"));
			topic.setWebPageURL(rs.getString("web_page_url"));
			topic.setVideoURL(rs.getString("video_url"));
			topic.setLessonID(rs.getInt("lesson_id"));
			topic.setCreationDate(new Date(rs.getLong("creation_date")));
			topic.setChangeDate(new Date(rs.getLong("change_date")));
			return topic;
		}

	}

}
