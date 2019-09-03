package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.root.CourseDAO;
import am.dproc.sms.models.Course;
import am.dproc.sms.services.root.CourseService;
import am.dproc.sms.services.root.LessonService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseDAO course;

	@Autowired
	LessonService lesson;

	// Works
	@Override
	public Course getCourse(Integer id) {
		Course course = this.course.getCourse(id);
		course.setListOfLessons(lesson.getCourseLessons(id));
		return course;
	}

	// Works
	@Override
	public List<Course> getCourses() {
		return course.getCourses();
	}

	// Works
	@Override
	public Integer deleteCourse(Integer id) {
		return lesson.deleteLessonsOfCourse(id) + course.deleteCourse(id);
	}
	
	// Works
	@Override
	public Integer addCourse(Course course) {
		this.course.addCourse(course);
		Integer courseID = this.course.getCourseID(course.getName());
		if (course.getListOfLessons() != null) {
			for (int i = 0; i < course.getListOfLessons().size(); i++) {
				if (course.getListOfLessons().get(i).getCourseID() == null) {
					course.getListOfLessons().get(i).setCourseID(courseID);
				}
			}
		} else {
			return 1;
		}
		return lesson.addLesson(course.getListOfLessons());
	}
	
}
