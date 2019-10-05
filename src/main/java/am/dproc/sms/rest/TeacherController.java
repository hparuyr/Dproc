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

import am.dproc.sms.models.Teacher;
import am.dproc.sms.services.interfaces.TeacherService;
import io.swagger.annotations.Api;

@RestController
@Path("/teacher")
@Api(value = "TeacherController")
public class TeacherController {
	
	@Autowired
	TeacherService teacher;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<Integer> addTeacher(Teacher teacher) {
		if (this.teacher.addTeacher(teacher) == 1) {
			return ResponseEntity.status(HttpStatus.CREATED).body(1);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/{id}")
	public Teacher getTeacher(@PathParam(value = "id") Integer id) {
		return teacher.gerTeacher(id);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Teacher> getTeachers() {
		return teacher.getTeachers();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<Integer> updateTeacher(Teacher teacher) {
		if (this.teacher.updateTeacher(teacher) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
		}
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/{id}")
	public ResponseEntity<Integer> deleteTeacher(@PathParam(value = "id") Integer id) {
		if (teacher.deleteTeacher(id) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
		}
	}

}
