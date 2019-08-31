package am.dproc.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.TopicDAO;
import am.dproc.sms.modules.Topic;

@Service
public class TopicServiceImpl implements TopicService {
	
	@Autowired
	TopicDAO topic;

	// Works
	@Override
	public List<Topic> getLessonTopics(Integer lessonID) {
		return topic.getTopicsOfLesson(lessonID);
	}

	// Works
	@Override
	public List<Topic> getAllTopics() {
		return topic.getAllTopics();
	}

	// Works
	@Override
	public Integer deleteTopic(Integer lessonID) {
		return topic.deleteTopicsOfLesson(lessonID);
	}

	// Works
	@Override
	public Integer addTopic(Topic topic) {
		return this.topic.addTopic(topic);
	}
	
	// Works
	@Override
	public Integer addTopic(List<Topic> topics) {
		Integer i = 0;
		for (int j = 0; j < topics.size(); j++) {
			i += topic.addTopic(topics.get(j));
		}
		return i;
	}
	
	// Works
	@Override
	public Integer addListOfTopic(List<List<Topic>> topics) {
		for (int i = 0; i < topics.size(); i++) {
			for (int j = 0; j < topics.get(i).size(); j++) {
				topic.addTopic(topics.get(i).get(j));
			}
		}
		return 1;
	}
	
	// Works
	@Override
	public Integer addTopic(Topic topic, Integer lessonID) {
		return this.topic.addTopic(topic, lessonID);
	}

}
