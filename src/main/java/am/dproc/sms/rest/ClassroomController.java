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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.Classroom;
import am.dproc.sms.services.interfaces.ClassroomService;

@RestController
@Path(value = "/classroom")
public class ClassroomController {

	@Autowired
	ClassroomService classroom;
	
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
	public List<Classroom> getClassroomsByCapacity(@QueryParam(value = "min") Integer min, @QueryParam(value = "max") Integer max) {
		return classroom.getClassrooms(min, max);
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/{id}")
	public ResponseEntity<Integer> deleteCourse(@PathParam(value = "id") Integer id) {
		if (classroom.deleteClassRoom(id) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Message", "First you must delete the lessons of this course!").body(0);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<Integer> addCourse(Classroom classroom) {
		Integer id = this.classroom.addClassroom(classroom);
		if (id == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Message", "All fields must be filled!").body(0);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<Integer> editCourse(Classroom classroom) {
		if (this.classroom.editClassroom(classroom) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
	}

}
