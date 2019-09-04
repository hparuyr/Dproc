package am.dproc.sms.db.root;

import java.util.List;

import org.springframework.http.ResponseEntity;

import am.dproc.sms.models.Course;

public interface CourseDAO {

	public Course getCourse(Integer id);
		
	public List<Course> getCourses();
	
	public Integer getCourseID(String name);
				
	public ResponseEntity<Integer> deleteCourse(Integer id);
			
	public ResponseEntity<Integer> addCourse(Course course);
	
	public ResponseEntity<Integer> editCourseName(Integer id, String name);
	
	public ResponseEntity<Integer> editCourseDuration(Integer id, String duration);
	
	public ResponseEntity<Integer> editCourseDescription(Integer id, String description);
	
	public ResponseEntity<Integer> editCourseLocation(Integer id, String location);
	
}
