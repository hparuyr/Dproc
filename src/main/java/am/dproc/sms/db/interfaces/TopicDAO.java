package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Topic;

public interface TopicDAO {
	
	Integer addTopic(Topic topic);

	List<Topic> getTopicsOfLesson(Integer lessonID);

	Topic getTopic(Integer id);

	List<Topic> getAllTopics();

	Integer editTopicVideoURL(Integer id, String videoURL);

	Integer editTopicWebPageURL(Integer id, String webPageURL);

	Integer deleteTopic(Integer id);

}
