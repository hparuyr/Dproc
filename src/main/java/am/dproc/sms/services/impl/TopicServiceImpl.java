package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.TopicDAO;
import am.dproc.sms.models.Topic;
import am.dproc.sms.services.interfaces.StudentCommentService;
import am.dproc.sms.services.interfaces.TopicService;

@Service
public class TopicServiceImpl implements TopicService {
	
	@Autowired
	StudentCommentService studentCommentService;

	@Autowired
	TopicDAO topicDAO;

	@Override
	public Integer addTopic(Topic topic) {
		if (topic.getVideoURL() == null || topic.getWebPageURL() == null || topic.getLessonId() == null) {
			return -1;
		}
		return topicDAO.addTopic(topic);
	}

	@Override
	public List<Topic> getLessonTopics(Integer lessonID) {
		return topicDAO.getTopicsOfLesson(lessonID);
	}

	@Override
	public Topic getTopic(Integer id) {
		return topicDAO.getTopic(id);
	}

	@Override
	public List<Topic> getAllTopics() {
		return topicDAO.getAllTopics();
	}

	@Override
	public Integer updateTopic(Topic topic) {

		boolean bool = false;

		if (topic.getVideoURL() != null) {
			if (this.topicDAO.updateTopicVideoURL(topic.getId(), topic.getVideoURL()) == 0) {
				return -1;
			}
			bool = true;
		}
		if (topic.getWebPageURL() != null) {
			if (this.topicDAO.updateTopicWebPageURL(topic.getId(), topic.getWebPageURL()) == 0) {
				return -1;
			}
			bool = true;
		}

		return bool ? 1 : 0;
	}

	@Override
	public Integer deleteTopic(Integer id) {
		if (studentCommentService.getCommentsOfTopic(id).size() == 0) {
			return topicDAO.deleteTopic(id);
		}
		return 0;
	}

}
