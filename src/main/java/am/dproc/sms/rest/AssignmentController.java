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

import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.Assignment;
import am.dproc.sms.services.interfaces.AssignmentService;

@RestController
@Path(value = "/assignment")
public class AssignmentController {

	@Autowired
	AssignmentService asiService;

	@GET
	@Path(value = "/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Assignment getAssignment(@PathParam(value = "id") Integer id) {
		return asiService.getAssignment(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Assignment> getAllAssignments() {
		return asiService.getAllAssignments();
	}

	@GET
	@Path(value = "title/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Assignment> getAllAssignments(@PathParam(value = "title") String title) {
		return asiService.getAllAssignments(title);
	}

	@GET
	@Path(value = "teacher/{teacherId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Assignment> getAssignmentsByTeacherId(@PathParam(value = "teacherId") Integer teacherId) {
		return asiService.getAssignmentsByTeacherId(teacherId);
	}

	@GET
	@Path(value = "/feedback/{id}")
	public String getAssignmentFeedback(@PathParam(value = "id") Integer id) {
		return asiService.getAssignmentFeedback(id);
	}

	@DELETE
	@Path(value = "/{id}")
	public Integer deleteAssignment(@PathParam(value = "id") Integer id) {
		return asiService.deleteAssignment(id);
	}

	@DELETE
	@Path(value = "/feedback/{id}")
	public Integer deleteAssignmentFeedback(@PathParam(value = "id") Integer id) {
		return asiService.deleteAssignmentFeedback(id);
	}

	@POST
	@Path(value = "/feedback/{teacherId}/{assignmentId}/{comment}")
	public Integer addAssignmentFeedback(@PathParam(value = "teacherId") Integer teacherId,
			@PathParam(value = "assignmentId") Integer assignmentId, @PathParam(value = "comment") String comment) {
		return asiService.addAssignmentFeedback(teacherId, assignmentId, comment);
	}

	@PUT
	@Path(value = "/feedback/{id}/{comment}")
	public Integer updateAssignmentFeedback(@PathParam(value = "id") Integer id,
			@PathParam(value = "comment") String comment) {
		return asiService.updateAssignmentFeedback(id, comment);
	}

	@DELETE
	public Integer deleteAllAssignments() {
		return asiService.deleteAllAssignments();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Integer addAssignment(Assignment asi) {
		return asiService.addAssignment(asi);
	}
}
