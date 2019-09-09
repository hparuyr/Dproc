package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.TopicDAO;
import am.dproc.sms.models.Topic;
import am.dproc.sms.services.interfaces.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	TopicDAO topic;

	@Override
	public List<Topic> getLessonTopics(Integer lessonID) {
		return topic.getTopicsOfLesson(lessonID);
	}

	@Override
	public Topic getTopic(Integer id) {
		return topic.getTopic(id);
	}

	@Override
	public List<Topic> getAllTopics() {
		return topic.getAllTopics();
	}

	@Override
	public Integer deleteTopic(Integer id) {
		return topic.deleteTopic(id);
	}

	@Override
	public Integer addTopic(Topic topic) {
		if (topic.getVideoURL() == null || topic.getWebPageURL() == null || topic.getLessonID() == null) {
			return 0;
		}
		return this.topic.addTopic(topic);
	}

	@Override
	public Integer editTopic(Topic topic) {
		if (topic.getVideoURL() != null) {
			return this.topic.editTopicVideoURL(topic.getId(), topic.getVideoURL());
		}
		if (topic.getWebPageURL() != null) {
			return this.topic.editTopicWebPageURL(topic.getId(), topic.getWebPageURL());
		}

		return 0;
	}

}
