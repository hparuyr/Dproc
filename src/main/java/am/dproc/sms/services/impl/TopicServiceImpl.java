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
	public Integer addTopic(Topic topic) {
		if (topic.getVideoURL() == null || topic.getWebPageURL() == null || topic.getLessonID() == null) {
			return 0;
		}
		return this.topic.addTopic(topic);
	}

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
	public Integer editTopic(Topic topic) {

		boolean bool = false;

		if (topic.getVideoURL() != null) {
			if (this.topic.editTopicVideoURL(topic.getId(), topic.getVideoURL()) == 0) {
				return -1;
			}
			bool = true;
		}
		if (topic.getWebPageURL() != null) {
			if (this.topic.editTopicWebPageURL(topic.getId(), topic.getWebPageURL()) == 0) {
				return -1;
			}
			bool = true;
		}

		return bool == true ? 1 : 0;
	}

	@Override
	public Integer deleteTopic(Integer id) {
		return topic.deleteTopic(id);
	}

}
