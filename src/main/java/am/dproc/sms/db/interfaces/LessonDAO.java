package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Lesson;

public interface LessonDAO {
	
	Integer addLesson(Lesson lesson, Integer courseId);

	Lesson getLesson(Integer id);

	List<Lesson> getLessonsOfCourse(Integer courseId);

	List<Lesson> getAllLessons();

	Integer updateLessonName(Integer id, String name);

	Integer updateLessonContent(Integer id, String content);

	Integer deleteLesson(Integer id);

}
