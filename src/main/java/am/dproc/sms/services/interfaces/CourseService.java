package am.dproc.sms.services.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import am.dproc.sms.models.Course;

public interface CourseService {

	public Course getCourse(Integer id);
	
	public List<Course> getCourses();
	
	public ResponseEntity<Integer> deleteCourse(Integer id);
		
	public ResponseEntity<Integer> addCourse(Course course);
	
	public ResponseEntity<Integer> editCourse(Course course);
	
}
