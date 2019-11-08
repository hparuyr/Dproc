package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Course;

public interface CourseDAO {

	Integer addCourse(Course course);

	Course getCourse(Integer id);

	List<Course> getCourses();

	List<Course> getCoursesByGroupId(Integer groupId);

	List<Course> getCoursesByStudentId(Integer studentId);

	Integer updateCourseName(Integer id, String name);

	Integer updateCourseDescription(Integer id, String description);

	Integer updateCourseDuration(Integer id, Integer duration);

	Integer updateCourseDurationUnitType(Integer id, String durationUnitType);

	Integer deleteCourse(Integer id);
}
