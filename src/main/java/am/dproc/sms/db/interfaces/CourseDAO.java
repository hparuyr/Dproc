package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Course;

public interface CourseDAO {

	public Course getCourse(Integer id);
		
	public List<Course> getCourses();
	
	public Integer getCourseID(String name, Long currentTimeMillis);
				
	public Integer deleteCourse(Integer id);
			
	public Integer addCourse(Course course);
	
	public Integer editCourseName(Integer id, String name);
	
	public Integer editCourseDuration(Integer id, String duration);
	
	public Integer editCourseDescription(Integer id, String description);
	
	public Integer editCourseLocation(Integer id, String location);
	
}
