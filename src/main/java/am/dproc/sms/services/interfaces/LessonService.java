package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Lesson;

public interface LessonService {
	
	Integer addLesson(Lesson lesson);

	Lesson getLesson(Integer id);

	List<Lesson> getCourseLessons(Integer courseID);

	List<Lesson> getAllLesson();

	Integer updateLesson(Lesson lesson);

	Integer deleteLesson(Integer id);

}
