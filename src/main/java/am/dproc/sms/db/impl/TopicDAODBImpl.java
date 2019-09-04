package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.root.TopicDAO;
import am.dproc.sms.models.Topic;

@Repository
public class TopicDAODBImpl implements TopicDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String DELETE_ALL_TOPICS = "DELETE FROM mydb.TOPIC WHERE ID > 0";
	private static final String DELETE_TOPICS_BY_LESSON_ID = "DELETE FROM mydb.TOPIC WHERE LESSON_ID = ?";
	private static final String GET_TOPICS_ID_BY_LESSON_ID = "SELECT * FROM mydb.TOPIC WHERE LESSON_ID = ?";
	private static final String ADD_TOPIC = "INSERT INTO mydb.TOPIC (VIDEO_URL, WEB_PAGE_URL, LESSON_ID) VALUES (?, ?, ?)";
	private static final String GET_TOPIC = "SELECT * FROM mydb.TOPIC";

	// Works
	@Override
	public List<Topic> getTopicsOfLesson(Integer lessonID) {
		return jdbctemplate.query(GET_TOPICS_ID_BY_LESSON_ID, new TopicMapper(), lessonID);
	}

	// Works
	@Override
	public List<Topic> getAllTopics() {
		List<Topic> list = jdbctemplate.query(GET_TOPIC, new TopicMapper());
		return list;
	}

	// Works
	@Override
	public Integer deleteTopicsOfLesson(Integer lessonsID) {
		return jdbctemplate.update(DELETE_TOPICS_BY_LESSON_ID, lessonsID);
	}

	// ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
	@Override
	public Integer deleteAllTopics() {
		return jdbctemplate.update(DELETE_ALL_TOPICS);
	}

	// Works
	@Override
	public Integer addTopic(Topic topic) {
		return jdbctemplate.update(ADD_TOPIC, new Object[] { topic.getVideoURL(), topic.getWebPageURL(), topic.getLessonID() });
	}

	// Works
	@Override
	public Integer addTopic(Topic topic, Integer lessonID) {
		return jdbctemplate.update(ADD_TOPIC, new Object[] { topic.getVideoURL(), topic.getWebPageURL(), lessonID });
	}

	private static class TopicMapper implements RowMapper<Topic> {
		@Override
		public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
			Topic topic = new Topic();
			topic.setId(rs.getInt("id"));
			topic.setWebPageURL(rs.getString("web_page_url"));
			topic.setVideoURL(rs.getString("video_url"));
			topic.setLessonID(rs.getInt("lesson_id"));
			return topic;
		}

	}

}
