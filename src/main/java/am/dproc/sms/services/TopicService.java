package am.dproc.sms.services;

import java.util.List;

import am.dproc.sms.modules.Topic;

public interface TopicService {

	public List<Topic> getLessonTopics(Integer lessonID);

	public List<Topic> getAllTopics();

	public Integer deleteTopic(Integer lessonID);

	public Integer addTopic(Topic topic);
	
	public Integer addTopic(List<Topic> topics);
	
	public Integer addListOfTopic(List<List<Topic>> topics);
	
	public Integer addTopic(Topic topic, Integer lessonID);

}
