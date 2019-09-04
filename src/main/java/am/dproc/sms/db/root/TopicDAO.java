package am.dproc.sms.db.root;

import java.util.List;

import org.springframework.http.ResponseEntity;

import am.dproc.sms.models.Topic;

public interface TopicDAO {

	public List<Topic> getTopicsOfLesson(Integer lessonID);
	
	public Topic getTopic(Integer id);
	
	public List<Topic> getAllTopics();
	
	public Integer getTopicID(String videoURL, String webPageURL, Integer lessonID);
		
	public ResponseEntity<Integer> deleteTopic(Integer id);
	
	public ResponseEntity<Integer> addTopic(Topic topic);
		
	public ResponseEntity<Integer> editTopicVideoURL(Integer id, String videoURL);

	public ResponseEntity<Integer> editTopicWebPageURL(Integer id, String webPageURL);
	
}
