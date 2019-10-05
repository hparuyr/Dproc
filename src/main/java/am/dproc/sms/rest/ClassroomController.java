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
public class ClassroomController {

	@Autowired
	ClassroomService classroom;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addCourse(Classroom classroom) {
		Integer id = this.classroom.addClassroom(classroom);
		if (id == 0) {
			Map<String, String> message = new HashMap<String, String>();
			message.put("Message", "All fields must be filled!");
			return Response.status(Status.BAD_REQUEST).entity(message).build();
		}
		return Response.status(Status.CREATED).entity(id).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/{id}")
	public Classroom getClassroom(@PathParam(value = "id") Integer id) {
		return classroom.getClassroomByID(id);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Classroom> getAllClassrooms() {
		return classroom.getAllClassrooms();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/capacity")
	public List<Classroom> getClassroomsByCapacity(@QueryParam(value = "min") Integer min,
			@QueryParam(value = "max") Integer max) {
		return classroom.getClassrooms(min, max);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response editCourse(Classroom classroom) {
		Integer status = this.classroom.editClassroom(classroom);
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
		if (classroom.deleteClassRoom(id) == 1) {
			return Response.status(Status.OK).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

}