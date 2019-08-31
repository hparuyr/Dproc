package am.dproc.sms.db;

import java.util.List;

import am.dproc.sms.modules.Lesson;

public interface LessonDAO {
	
	public Lesson getLesson(Integer id);
	
	public List<Lesson> getLessonsOfCourse(Integer courseID);
	
	public List<Lesson> getAllLessons();
	
	public List<Integer> getLessonsIDs(Integer courseID);
	
	public Integer getLessonID(String name);
	
	public Integer deleteLesson(Integer id);
	
	public Integer deleteLessonsOfCourse(Integer courseID);
	
	public Integer deleteAllLessons();
	
	public Integer addLesson(Lesson lesson, Integer courseID);

}
