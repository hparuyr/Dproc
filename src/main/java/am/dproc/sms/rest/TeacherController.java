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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.Teacher;
import am.dproc.sms.services.interfaces.TeacherService;
import io.swagger.annotations.Api;

@RestController
@Path("/teacher")
@Api(value = "TeacherController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;

	@POST
	public Response addTeacher(Teacher teacher) {
		int id = teacherService.addTeacher(teacher);
		if (id > 0) {
			return Response.status(Response.Status.CREATED).entity(id).build();
		} 
		else if(id == 0) {
			Map<String,String> message = new HashMap<>();
			message.put("message","User with your email already exists");
			return Response.status(Response.Status.CONFLICT).entity(message).build();
		}
		else {
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("").build();
		}
	}

	@GET
	@Path(value = "/{id}")
	public Response getTeacher(@PathParam(value = "id") Integer id) {
		Teacher teacher = teacherService.getTeacher(id);
		if(teacher != null) {
			return Response.status(Response.Status.OK).entity(teacher).build();
		}
		Map<String, String> message = new HashMap<>();
		message.put("message", String.format("Teacher with id %s not found", id));
		return Response.status(Response.Status.NOT_FOUND).entity(message).build();
	}

	@GET
	public Response getTeachers() {
		List<Teacher> teachers = teacherService.getTeachers();
		return Response.status(Response.Status.OK).entity(teachers).build();
	}

	@PUT
	public ResponseEntity<Integer> updateTeacher(Teacher teacher) {
		if (teacherService.updateTeacher(teacher) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
		}
	}

	@DELETE
	@Path(value = "/{id}")
	public ResponseEntity<Integer> deleteTeacher(@PathParam(value = "id") Integer id) {
		if (teacherService.deleteTeacher(id) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
		}
	}

}
