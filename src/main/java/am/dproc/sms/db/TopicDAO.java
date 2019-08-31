package am.dproc.sms.db;

import java.util.List;

import am.dproc.sms.modules.Topic;

public interface TopicDAO {

	public List<Topic> getTopicsOfLesson(Integer lessonID);
	
	public List<Topic> getAllTopics();
	
	public Integer deleteTopicsOfLesson(Integer lessonsID);
	
	public Integer deleteAllTopics();
	
	public Integer addTopic(Topic topic);
	
	public Integer addTopic(Topic topic, Integer lessonID);
	
}
