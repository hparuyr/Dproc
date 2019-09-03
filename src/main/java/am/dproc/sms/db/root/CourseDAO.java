package am.dproc.sms.db.root;

import java.util.List;

import am.dproc.sms.models.Course;

public interface CourseDAO {

	public Course getCourse(Integer id);
		
	public List<Course> getCourses();
	
	public Integer getCourseID(String name);
				
	public Integer deleteCourse(Integer id);
			
	public Integer addCourse(Course course);
	
}
