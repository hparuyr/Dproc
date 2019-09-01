package am.dproc.sms.services;

import java.util.List;

import am.dproc.sms.modules.Lesson;

public interface LessonService {
	
	public Lesson getLesson(Integer id);
	
	public List<Lesson> getCourseLessons(Integer courseID);
	
	public List<Lesson> getAllLesson();
	
	public Integer deleteLesson(Integer id);
	
	public Integer deleteLessonsOfCourse(Integer courseID);
			
	public Integer addLesson(Lesson lesson);
	
	public Integer addLesson(List<Lesson> lessons);
	
}