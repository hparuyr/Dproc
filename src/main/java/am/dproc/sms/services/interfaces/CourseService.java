package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Course;

public interface CourseService {

	Integer addCourse(Course course);

	Course getCourse(Integer id);

	List<Course> getCourses();

	List<Course> getCoursesByGroupId(Integer groupId);

	List<Course> getCoursesByStudentId(Integer studentId);

	Integer updateCourse(Course course);

	Integer deleteCourse(Integer id);
}
