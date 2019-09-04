package am.dproc.sms.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.Course;
import am.dproc.sms.services.root.CourseService;

@RestController
@Path(value = "/course")
public class CourseController {

	@Autowired
	CourseService course;

	// Works
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path(value = "/{id}")
	public Course getCourse(@PathParam(value = "id") Integer id) {
		return course.getCourse(id);
	}

	// Works
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Course> getCourses() {
		return course.getCourses();
	}

	// Works
	@DELETE
	@Path(value = "/{id}")
	public Integer deleteCourse(@PathParam(value = "id") Integer id) {
		return course.deleteCourse(id);
	}

	// Works
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Integer addCourse(Course course) {
		return this.course.addCourse(course);
	}

}
