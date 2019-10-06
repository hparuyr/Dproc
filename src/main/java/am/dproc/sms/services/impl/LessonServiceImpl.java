package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.LessonDAO;
import am.dproc.sms.models.Lesson;
import am.dproc.sms.services.interfaces.LessonService;
import am.dproc.sms.services.interfaces.TopicService;

@Service
public class LessonServiceImpl implements LessonService {

	@Autowired
	LessonDAO lessonDAO;

	@Autowired
	TopicService topicService;

	@Override
	public Integer addLesson(Lesson lesson) {
		if (lesson.getName() == null || lesson.getContent() == null || lesson.getCourseID() == null) {
			return -1;
		}
		return this.lessonDAO.addLesson(lesson, lesson.getCourseID());
	}

	@Override
	public Lesson getLesson(Integer id) {
		Lesson lesson = this.lessonDAO.getLesson(id);
		lesson.setListOfTopics(this.topicService.getLessonTopics(id));
		return lesson;
	}

	@Override
	public List<Lesson> getCourseLessons(Integer courseID) {
		return this.lessonDAO.getLessonsOfCourse(courseID);
	}

	@Override
	public List<Lesson> getAllLesson() {
		return lessonDAO.getAllLessons();
	}

	@Override
	public Integer editLesson(Lesson lesson) {

		boolean bool = false;

		if (lesson.getName() != null) {
			if (this.lessonDAO.editLessonName(lesson.getId(), lesson.getName()) == 0) {
				return -1;
			}
			bool = true;
		}
		if (lesson.getContent() != null) {
			if (this.lessonDAO.editLessonContent(lesson.getId(), lesson.getContent()) == 0) {
				return -1;
			}
			bool = true;
		}

		return bool ? 1 : 0;
	}

	@Override
	public Integer deleteLesson(Integer id) {
		if (getLesson(id).getListOfTopics().size() == 0) {
			return lessonDAO.deleteLesson(id);
		}
		return 0;
	}

}
