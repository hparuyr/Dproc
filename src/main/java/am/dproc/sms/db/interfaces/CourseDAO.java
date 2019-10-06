package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Course;

public interface CourseDAO {

	public Integer addCourse(Course course);

	public Course getCourse(Integer id);

	public List<Course> getCourses();

	public Integer deleteCourse(Integer id);

	public Integer editCourseName(Integer id, String name);

	public Integer editCourseDescription(Integer id, String description);

	public Integer editCourseDuration(Integer id, Integer duration);
	
	public Integer editCourseDurationUnitType(Integer id, String durationUnitType);

}
