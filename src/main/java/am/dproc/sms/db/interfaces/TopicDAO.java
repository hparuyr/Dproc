package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Topic;

public interface TopicDAO {
	
	public Integer addTopic(Topic topic);

	public List<Topic> getTopicsOfLesson(Integer lessonID);

	public Topic getTopic(Integer id);

	public List<Topic> getAllTopics();

	public Integer editTopicVideoURL(Integer id, String videoURL);

	public Integer editTopicWebPageURL(Integer id, String webPageURL);

	public Integer deleteTopic(Integer id);

}
