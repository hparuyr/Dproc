package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Topic;

public interface TopicService {
	
	public Integer addTopic(Topic topic);

	public List<Topic> getLessonTopics(Integer lessonID);

	public Topic getTopic(Integer id);

	public List<Topic> getAllTopics();

	public Integer editTopic(Topic topic);

	public Integer deleteTopic(Integer lessonID);

}
