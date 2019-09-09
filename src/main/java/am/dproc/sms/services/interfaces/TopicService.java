package am.dproc.sms.services.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import am.dproc.sms.models.Topic;

public interface TopicService {

	public List<Topic> getLessonTopics(Integer lessonID);
	
	public Topic getTopic(Integer id);

	public List<Topic> getAllTopics();

	public ResponseEntity<Integer> deleteTopic(Integer lessonID);

	public ResponseEntity<Integer> addTopic(Topic topic);	
		
	public ResponseEntity<Integer> editTopic(Topic topic);

}
