package am.dproc.sms.db;

import java.util.List;

import am.dproc.sms.modules.Course;

public interface CourseDAO {

	public Course getCourse(Integer id);
		
	public List<Course> getCourses();
	
	public Integer getCourseID(String name);
				
	public Integer deleteCourse(Integer id);
			
	public Integer addCourse(Course course);
	
}
