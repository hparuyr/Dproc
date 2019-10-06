package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Topic;

public interface TopicService {
	
	Integer addTopic(Topic topic);

	List<Topic> getLessonTopics(Integer lessonID);

	Topic getTopic(Integer id);

	List<Topic> getAllTopics();

	Integer editTopic(Topic topic);

	Integer deleteTopic(Integer lessonID);

}
