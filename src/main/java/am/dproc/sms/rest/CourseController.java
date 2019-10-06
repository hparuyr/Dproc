package am.dproc.sms.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.Course;
import am.dproc.sms.services.interfaces.CourseService;
import io.swagger.annotations.Api;

@RestController
@Path(value = "/courseService")
@Api(value = "CourseController")
public class CourseController {

	@Autowired
	CourseService courseService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addCourse(Course course) {
		Integer id = this.courseService.addCourse(course);
		if (id == -1) {
			Map<String, String> message = new HashMap<String, String>();
			message.put("Message", "All fields must be filled!");
			return Response.status(Status.BAD_REQUEST).entity(message).build();
		} else if (id == 0) {
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		}
		return Response.status(Status.CREATED).entity(id).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/{id}")
	public Course getCourse(@PathParam(value = "id") Integer id) {
		return courseService.getCourse(id);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Course> getCourses() {
		return courseService.getCourses();
	}

	@GET
	@Path(value="/groupService/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Course> getCoursesByGroupId(@PathParam(value="id") Integer id) {
		return courseService.getCoursesByGroupId(id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response editCourse(Course course) {
		Integer status = this.courseService.editCourse(course);
		if (status == 1) {
			return Response.status(Status.OK).build();
		} else if (status == -1) {
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		}
		Map<String, String> message = new HashMap<String, String>();
		message.put("Message", "Nothing to update!");
		return Response.status(Status.NO_CONTENT).entity(message).build();
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/{id}")
	public Response deleteCourse(@PathParam(value = "id") Integer id) {
		if (courseService.deleteCourse(id) == 1) {
			return Response.status(Status.OK).build();
		}
		Map<String, String> message = new HashMap<String, String>();
		message.put("Message", "First you must delete the lessons of this courseService!");
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}

}
