package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Topic;

public interface TopicService {

	public List<Topic> getLessonTopics(Integer lessonID);
	
	public Topic getTopic(Integer id);

	public List<Topic> getAllTopics();

	public Integer deleteTopic(Integer lessonID);

	public Integer addTopic(Topic topic);	
		
	public Integer editTopic(Topic topic);

}
