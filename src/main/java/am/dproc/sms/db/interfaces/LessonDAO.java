package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Lesson;

public interface LessonDAO {

	public Lesson getLesson(Integer id);

	public List<Lesson> getLessonsOfCourse(Integer courseID);

	public List<Lesson> getAllLessons();

	public Integer deleteLesson(Integer id);

	public Integer addLesson(Lesson lesson, Integer courseID);

	public Integer editLessonName(Integer id, String name);

	public Integer editLessonContent(Integer id, String content);

}
