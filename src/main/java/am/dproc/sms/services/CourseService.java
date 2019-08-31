package am.dproc.sms.services;

import java.util.List;

import am.dproc.sms.modules.Course;

public interface CourseService {

	public Course getCourse(Integer id);
	
	public List<Course> getCourses();
	
	public Integer deleteCourse(Integer id);
		
	public Integer addCourse(Course course);
	
	
}
