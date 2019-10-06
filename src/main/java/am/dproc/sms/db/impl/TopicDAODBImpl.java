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

import am.dproc.sms.db.interfaces.TopicDAO;
import am.dproc.sms.models.Topic;

@Repository
public class TopicDAODBImpl implements TopicDAO {

	@Autowired
	JdbcTemplate jdbctemplate;
	
	private static final String ADD_TOPIC = ""
			+ "INSERT "
			+ "INTO mydb.TOPIC (LESSON_ID, VIDEO_URL, WEB_PAGE_URL, CREATION_DATE, CHANGE_DATE) "
			+ "VALUES (?, ?, ?, ?, ?)";
	private static final String GET_TOPIC = ""
			+ "SELECT ID, LESSON_ID, VIDEO_URL, WEB_PAGE_URL, CREATION_DATE "
			+ "FROM mydb.TOPIC "
			+ "WHERE ID = ?";
	private static final String GET_TOPICS = ""
			+ "SELECT ID, LESSON_ID, VIDEO_URL, WEB_PAGE_URL, CREATION_DATE "
			+ "FROM mydb.TOPIC";
	private static final String GET_TOPICS_BY_LESSON_ID = ""
			+ "SELECT ID, LESSON_ID, VIDEO_URL, WEB_PAGE_URL, CREATION_DATE "
			+ "FROM mydb.TOPIC "
			+ "WHERE LESSON_ID = ?";
	private static final String EDIT_TOPIC_VIDEO_URL = ""
			+ "UPDATE mydb.TOPIC "
			+ "SET VIDEO_URL = ?, CHANGE_DATE = ? "
			+ "WHERE ID = ?";
	private static final String EDIT_TOPIC_WEB_PAGE_URL = ""
			+ "UPDATE mydb.TOPIC "
			+ "SET WEB_PAGE_URL = ?, CHANGE_DATE = ? "
			+ "WHERE ID = ?";
	private static final String DELETE_TOPICS_BY_ID = ""
			+ "DELETE "
			+ "FROM mydb.TOPIC "
			+ "WHERE ID = ?";

	@Override
	public Integer addTopic(Topic topic) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbctemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(ADD_TOPIC, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, topic.getLessonID());		
			ps.setString(2, topic.getVideoURL());
			ps.setString(3, topic.getWebPageURL());
			ps.setLong(4, System.currentTimeMillis());
			ps.setLong(5, System.currentTimeMillis());
			return ps;
		}, keyHolder);

		return (Integer) keyHolder.getKey().intValue();
	}
	
	@Override
	public Topic getTopic(Integer id) {
		return jdbctemplate.queryForObject(GET_TOPIC, new TopicMapper(), id);
	}

	@Override
	public List<Topic> getAllTopics() {
		return jdbctemplate.query(GET_TOPICS, new TopicMapper());
	}

	@Override
	public List<Topic> getTopicsOfLesson(Integer lessonID) {
		return jdbctemplate.query(GET_TOPICS_BY_LESSON_ID, new TopicMapper(), lessonID);
	}

	@Override
	public Integer editTopicVideoURL(Integer id, String videoURL) {
		return jdbctemplate.update(EDIT_TOPIC_VIDEO_URL, videoURL, System.currentTimeMillis(), id );
	}

	@Override
	public Integer editTopicWebPageURL(Integer id, String webPageURL) {
		return jdbctemplate.update(EDIT_TOPIC_WEB_PAGE_URL, webPageURL, System.currentTimeMillis(), id );
	}
	
	@Override
	public Integer deleteTopic(Integer id) {
		return jdbctemplate.update(DELETE_TOPICS_BY_ID, id);
	}

	private static class TopicMapper implements RowMapper<Topic> {
		@Override
		public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
			Topic topic = new Topic();
			
			topic.setId(rs.getInt("ID"));
			topic.setLessonID(rs.getInt("LESSON_ID"));
			topic.setWebPageURL(rs.getString("WEB_PAGE_URL"));
			topic.setVideoURL(rs.getString("VIDEO_URL"));
			topic.setCreationDate(rs.getLong("CREATION_DATE"));
			
			return topic;
		}

	}

}
