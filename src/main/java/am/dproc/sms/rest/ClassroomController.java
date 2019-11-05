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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.Classroom;
import am.dproc.sms.services.interfaces.ClassroomService;
import io.swagger.annotations.Api;

@RestController
@Path(value = "/classroom")
@Api(value = "ClassroomController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
public class ClassroomController {

	@Autowired
	ClassroomService classroomService;

	@POST
	public Response addCourse(Classroom classroom) {
		Integer id = this.classroomService.addClassroom(classroom);
		if (id == -1) {
			Map<String, String> message = new HashMap<>();
			message.put("Message", "All fields must be filled!");
			return Response.status(Status.BAD_REQUEST).entity(message).build();
		} else if (id == 0) {
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		}
		return Response.status(Status.CREATED).entity(id).build();
	}

	@GET
	@Path(value = "/{id}")
	public Classroom getClassroom(@PathParam(value = "id") Integer id) {
		return classroomService.getClassroomById(id);
	}

	@GET
	public List<Classroom> getAllClassrooms() {
		return classroomService.getAllClassrooms();
	}

	@GET
	@Path(value = "/capacity")
	public List<Classroom> getClassroomsByCapacity(@QueryParam(value = "min") Integer min,
			@QueryParam(value = "max") Integer max) {
		return classroomService.getClassrooms(min, max);
	}

	@PUT
	public Response updateCourse(Classroom classroom) {
		Integer status = this.classroomService.updateClassroom(classroom);
		if (status == 1) {
			return Response.status(Status.OK).build();
		} else if (status == -1) {
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		}
		Map<String, String> message = new HashMap<>();
		message.put("Message", "Nothing to update!");
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}

	@DELETE
	@Path(value = "/{id}")
	public Response deleteCourse(@PathParam(value = "id") Integer id) {
		if (classroomService.deleteClassRoom(id) == 1) {
			return Response.status(Status.OK).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

}