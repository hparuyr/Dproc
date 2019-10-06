package am.dproc.sms.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.Assignment;
import am.dproc.sms.services.interfaces.AssignmentService;
import io.swagger.annotations.Api;

@RestController
@Path(value = "/assignment")
@Api(value = "AssignmentController")
public class AssignmentController {

	@Autowired
	AssignmentService assignmentService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAssignment(Assignment assignment) {
		Integer id = assignmentService.addAssignment(assignment);
		if (id == -1) {
			Map<String, String> message = new HashMap<String, String>();
			message.put("Message", "All fields must be filled!");
			return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
		} else if (id == 0) {
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}
		return Response.status(Response.Status.CREATED).entity(id).build();
	}

	@GET
	@Path(value = "/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Assignment getAssignment(@PathParam(value = "id") Integer id) {
		return assignmentService.getAssignment(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Assignment> getAllAssignments() {
		return assignmentService.getAllAssignments();
	}

	@GET
	@Path(value = "teacher/{teacherId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Assignment> getAssignmentsByTeacherId(@PathParam(value = "teacherId") Integer teacherId) {
		return assignmentService.getAssignmentsByTeacherId(teacherId);
	}

	/*@GET
	@Path(value = "/feedback/{id}")
	public String getAssignmentFeedback(@PathParam(value = "id") Integer id) {
		return assignmentService.getAssignmentFeedback(id);
	}*/

	@DELETE
	@Path(value = "/{id}")
	public Integer deleteAssignment(@PathParam(value = "id") Integer id) {
		return assignmentService.deleteAssignment(id);
	}

	/*@DELETE
	@Path(value = "/feedback/{id}")
	public Integer deleteAssignmentFeedback(@PathParam(value = "id") Integer id) {
		return assignmentService.deleteAssignmentFeedback(id);
	}

	@POST
	@Path(value = "/feedback/{teacherId}/{assignmentId}/{studentCommentService}")
	public Integer addAssignmentFeedback(@PathParam(value = "teacherId") Integer teacherId,
			@PathParam(value = "assignmentId") Integer assignmentId, @PathParam(value = "studentCommentService") String studentCommentService) {
		return assignmentService.addAssignmentFeedback(teacherId, assignmentId, studentCommentService);
	}

	@PUT
	@Path(value = "/feedback/{id}/{studentCommentService}")
	public Integer updateAssignmentFeedback(@PathParam(value = "id") Integer id,
			@PathParam(value = "studentCommentService") String studentCommentService) {
		return assignmentService.updateAssignmentFeedback(id, studentCommentService);
	}*/

	@DELETE
	public Integer deleteAllAssignments() {
		return assignmentService.deleteAllAssignments();
	}

}
