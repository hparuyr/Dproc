package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Course;

public interface CourseService {

	public Integer addCourse(Course course);

	public Course getCourse(Integer id);

	public List<Course> getCourses();

	List<Course> getCoursesByGroupId(Integer groupId);

	public Integer editCourse(Course course);

	public Integer deleteCourse(Integer id);
}
