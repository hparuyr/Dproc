package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Course;

public interface CourseService {

	public Course getCourse(Integer id);
	
	public List<Course> getCourses();
		
	public Integer deleteCourse(Integer id);
		
	public Integer addCourse(Course course);
	
	public Integer editCourse(Course course);
	
}
