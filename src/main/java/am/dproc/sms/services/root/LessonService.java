package am.dproc.sms.services.root;

import java.util.List;

import org.springframework.http.ResponseEntity;

import am.dproc.sms.models.Lesson;

public interface LessonService {
	
	public Lesson getLesson(Integer id);
	
	public List<Lesson> getCourseLessons(Integer courseID);
	
	public List<Lesson> getAllLesson();
	
	public ResponseEntity<Integer> deleteLesson(Integer id);
				
	public ResponseEntity<Integer> addLesson(Lesson lesson);
		
	public ResponseEntity<Integer> editLesson(Lesson lesson);
	
}
