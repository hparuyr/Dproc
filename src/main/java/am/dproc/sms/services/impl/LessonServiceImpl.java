package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.root.LessonDAO;
import am.dproc.sms.models.Lesson;
import am.dproc.sms.services.root.LessonService;
import am.dproc.sms.services.root.TopicService;

@Service
public class LessonServiceImpl implements LessonService {

	@Autowired
	LessonDAO lesson;

	@Autowired
	TopicService topic;

	// Works
	@Override
	public Lesson getLesson(Integer id) {
		Lesson lesson = this.lesson.getLesson(id);
		lesson.setListOfTopics(this.topic.getLessonTopics(id));
		return lesson;
	}

	@Override
	public List<Lesson> getCourseLessons(Integer courseID) {
		return this.lesson.getLessonsOfCourse(courseID);
	}

	@Override
	public List<Lesson> getAllLesson() {
		return lesson.getAllLessons();
	}

	@Override
	public ResponseEntity<Integer> deleteLesson(Integer id) {
		return lesson.deleteLesson(id);
	}

	@Override
	public ResponseEntity<Integer> addLesson(Lesson lesson) {
		return this.lesson.addLesson(lesson, lesson.getCourseID());
	}

	@Override
	public ResponseEntity<Integer> editLesson(Lesson lesson) {
		if (lesson.getName() != null) {
			return this.lesson.editLessonName(lesson.getId(), lesson.getName());
		} else if (lesson.getContent() != null) {
			return this.lesson.editLessonContent(lesson.getId(), lesson.getContent());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
	}

}
