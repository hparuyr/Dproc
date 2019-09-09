package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Lesson;

public interface LessonService {
	
	public Lesson getLesson(Integer id);
	
	public List<Lesson> getCourseLessons(Integer courseID);
	
	public List<Lesson> getAllLesson();
	
	public Integer deleteLesson(Integer id);
				
	public Integer addLesson(Lesson lesson);
		
	public Integer editLesson(Lesson lesson);
	
}
