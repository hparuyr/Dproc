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

import am.dproc.sms.models.Student;
import am.dproc.sms.services.interfaces.StudentService;
import io.swagger.annotations.Api;

@RestController
@Path("/student")
@Api(value = "StudentController")
public class StudentController {
	@Autowired
	StudentService studentService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addStudent(Student student) {
		int id = this.studentService.addStudent(student);
		if (id > 0) {
			return Response.status(Response.Status.CREATED).entity(id).build();
		} else if (id == 0) {
			Map<String, String> message = new HashMap<>();
			message.put("message", String.format("User with email %s already exists", student.getEmail()));
			return Response.status(Response.Status.CONFLICT).entity(message).build();
		} else {
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("").build();
		}
	}

	@Path("/all")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStudents(List<Student> student) {
		int[] ids = this.studentService.addStudents(student);
		if (ids.length > 0) {
			return Response.status(Response.Status.CREATED).entity(ids).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/{id}")
	public Response getStudent(@PathParam(value = "id") Integer id) {
		Student student = studentService.getStudent(id);
		if (student != null) {
			return Response.status(Response.Status.OK).entity(student).build();
		}
		Map<String, String> message = new HashMap<>();
		message.put("message", String.format("Student with id %s not found", id));
		return Response.status(Response.Status.NOT_FOUND).entity(message).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Student> getStudent() {
		return studentService.getStudents();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<Integer> updateStudent(Student student) {
		if (studentService.updateStudent(student) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
		}
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/{id}")
	public ResponseEntity<Integer> deleteStudent(@PathParam(value = "id") Integer id) {
		if (studentService.deleteStudent(id) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
		}
	}

}
