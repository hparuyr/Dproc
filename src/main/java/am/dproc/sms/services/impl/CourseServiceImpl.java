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
	public Integer addCourse(Course course) {
		if (course.getSchoolID() == null || course.getName() == null || course.getDescription() == null
				|| course.getDuration() == null || course.getDurationUnitType() == null
				|| course.getFinished() == null) {
			return 0;
		}
		return this.course.addCourse(course);
	}

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
	public Integer editCourse(Course course) {

		boolean bool = false;

		if (course.getName() != null) {
			if (this.course.editCourseName(course.getId(), course.getName()) == 0) {
				return -1;
			}
			bool = true;
		}
		if (course.getDescription() != null) {
			if (this.course.editCourseDescription(course.getId(), course.getDescription()) == 0) {
				return -1;
			}
			bool = true;
		}
		if (course.getDuration() != null) {
			if (this.course.editCourseDuration(course.getId(), course.getDuration()) == 0) {
				return -1;
			}
			bool = true;
		}
		if (course.getDurationUnitType() != null) {
			if (this.course.editCourseDurationUnitType(course.getId(), course.getDurationUnitType()) == 0) {
				return -1;
			}
			bool = true;
		}
		if (course.getFinished() != null) {
			if (this.course.editCourseFinished(course.getId(), course.getFinished()) == 0) {
				return -1;
			}
			bool = true;
		}

		return bool == true ? 1 : 0;
	}

	@Override
	public Integer deleteCourse(Integer id) {
		if (getCourse(id).getListOfLessons().size() == 0) {
			return course.deleteCourse(id);
		}
		return 0;
	}

}
