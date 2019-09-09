package am.dproc.sms.db.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import am.dproc.sms.models.Lesson;

public interface LessonDAO {

	public Lesson getLesson(Integer id);

	public List<Lesson> getLessonsOfCourse(Integer courseID);

	public List<Lesson> getAllLessons();

	public List<Integer> getLessonsIDs(Integer courseID);

	public Integer getLessonID(String name);
	
	public Integer getLessonID(String name, Integer courseID);

	public ResponseEntity<Integer> deleteLesson(Integer id);

	public ResponseEntity<Integer> addLesson(Lesson lesson, Integer courseID);

	public ResponseEntity<Integer> editLessonName(Integer id, String name);

	public ResponseEntity<Integer> editLessonContent(Integer id, String content);

}
