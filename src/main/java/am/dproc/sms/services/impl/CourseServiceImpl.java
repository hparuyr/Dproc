package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.CourseDAO;
import am.dproc.sms.models.Course;
import am.dproc.sms.services.interfaces.CourseService;
import am.dproc.sms.services.interfaces.LessonService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseDAO course;

	@Autowired
	LessonService lesson;

	@Override
	public Course getCourse(Integer id) {
		Course course = this.course.getCourse(id);
		course.setListOfLessons(lesson.getCourseLessons(id));
		return course;
	}

	@Override
	public List<Course> getCourses() {
		return course.getCourses();
	}
	
	@Override
	public Integer deleteCourse(Integer id) {
		if (getCourse(id).getListOfLessons().size() == 0) {
			return course.deleteCourse(id);
		}
		return 0;
	}

	@Override
	public Integer addCourse(Course course) {
		if (course.getName() == null || course.getDuration() == null || course.getDescription() == null || course.getLocation() == null) {
			return 0;
		}
		return this.course.addCourse(course);
	}

	@Override
	public Integer editCourse(Course course) {
		if (course.getName() != null) {
			return this.course.editCourseName(course.getId(), course.getName());
		}
		if (course.getDuration() != null) {
			return this.course.editCourseDuration(course.getId(), course.getDuration());
		}
		if (course.getDescription() != null) {
			return this.course.editCourseDescription(course.getId(), course.getDescription());
		}
		if (course.getLocation() != null) {
			return this.course.editCourseLocation(course.getId(), course.getLocation());
		}

		return 0;
	}

}
