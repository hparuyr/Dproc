package am.dproc.sms.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.LessonDAO;
import am.dproc.sms.modules.Lesson;
import am.dproc.sms.modules.Topic;

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
		lesson.setListOfURLs(this.topic.getLessonTopics(id));
		return lesson;
	}

	// Works
	@Override
	public List<Lesson> getCourseLessons(Integer courseID) {
		List<Lesson> lessons = this.lesson.getLessonsOfCourse(courseID);
		List<Integer> listOfLessonsIDs = this.lesson.getLessonsIDs(courseID);
		for (int i = 0; i < lessons.size(); i++) {
			lessons.get(i).setListOfURLs(topic.getLessonTopics(listOfLessonsIDs.get(i)));
		}
		return lessons;
	}

	// Works
	@Override
	public List<Lesson> getAllLesson() {
		return lesson.getAllLessons();
	}

	// Works
	@Override
	public Integer deleteLesson(Integer id) {
		return topic.deleteTopic(id) + lesson.deleteLesson(id);
	}

	// Works
	@Override
	public Integer deleteLessonsOfCourse(Integer courseID) {
		List<Integer> listOfLessonsIDs = lesson.getLessonsIDs(courseID);
		for (int i = 0; i < listOfLessonsIDs.size(); i++) {
			topic.deleteTopic(listOfLessonsIDs.get(i));
		}
		return lesson.deleteLessonsOfCourse(courseID);
	}

	// Works
	@Override
	public Integer addLesson(Lesson lesson) {
		this.lesson.addLesson(lesson, lesson.getCourseID());
		Integer lessonID = this.lesson.getLessonID(lesson.getName());
		for (int i = 0; i < lesson.getListOfURLs().size(); i++) {
			if (lesson.getListOfURLs().get(i).getLessonID() == null) {
				lesson.getListOfURLs().get(i).setLessonID(lessonID);
			}
		}
		return topic.addTopic(lesson.getListOfURLs());
	}
	
	// Works
	@Override
	public Integer addLesson(List<Lesson> lessons) {
		for (int i = 0; i < lessons.size(); i++) {
			lesson.addLesson(lessons.get(i), lessons.get(i).getCourseID());
		}
		List<List<Topic>> topics = new ArrayList<>();
		for (int i = 0; i < lessons.size(); i++) {
			if (lessons.get(i).getListOfURLs() != null) {
				Integer lessonID = this.lesson.getLessonID(lessons.get(i).getName());
				for (int j = 0; j < lessons.get(i).getListOfURLs().size(); j++) {
					if (lessons.get(i).getListOfURLs().get(j).getLessonID() == null) {
						lessons.get(i).getListOfURLs().get(j).setLessonID(lessonID);
					}
				}
				topics.add(lessons.get(i).getListOfURLs());
			}
		}
		return topic.addListOfTopic(topics);
	}

}
