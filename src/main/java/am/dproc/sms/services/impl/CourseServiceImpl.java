package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Integer> deleteCourse(Integer id) {
		return course.deleteCourse(id);
	}

	@Override
	public ResponseEntity<Integer> addCourse(Course course) {
		return this.course.addCourse(course);
	}

	@Override
	public ResponseEntity<Integer> editCourse(Course course) {
		if (course.getName() != null) {
			return this.course.editCourseName(course.getId(), course.getName());
		} else if (course.getDuration() != null) {
			return this.course.editCourseDuration(course.getId(), course.getDuration());
		} else if (course.getDescription() != null) {
			return this.course.editCourseDescription(course.getId(), course.getDescription());
		} else if (course.getLocation() != null) {
			return this.course.editCourseLocation(course.getId(), course.getLocation());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
	}

}
