package am.dproc.sms.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.Course;
import am.dproc.sms.services.interfaces.CourseService;

@RestController
@Path(value = "/course")
public class CourseController {

	@Autowired
	CourseService course;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/{id}")
	public Course getCourse(@PathParam(value = "id") Integer id) {
		return course.getCourse(id);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Course> getCourses() {
		return course.getCourses();
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/{id}")
	public ResponseEntity<Integer> deleteCourse(@PathParam(value = "id") Integer id) {
		if (course.deleteCourse(id) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Message", "First you must delete the lessons of this course!").body(0);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<Integer> addCourse(Course course) {
		Integer id = this.course.addCourse(course);
		if (id == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Message", "All fields must be filled!").body(0);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<Integer> editCourse(Course course) {
		if (this.course.editCourse(course) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
	}

}
