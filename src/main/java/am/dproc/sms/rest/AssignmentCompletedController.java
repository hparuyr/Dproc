package am.dproc.sms.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import am.dproc.sms.models.AssignmentCompleted;
import am.dproc.sms.models.Student;
import am.dproc.sms.services.interfaces.AssignmentCompletedService;
import io.swagger.annotations.Api;

@RestController
@Path(value = "/assignmentCompl")
@Api(value = "AssignmentCompletedController")
public class AssignmentCompletedController {
	@Autowired
	private AssignmentCompletedService assignmentCompletedService;

	@GET
	@Path(value = "/{assignmentCompletedId}")
	@Produces(MediaType.APPLICATION_JSON)
	public AssignmentCompleted getAssignmentCompleted(@PathParam(value = "assignmentCompletedId") Integer assignmentCompletedId) {
		return assignmentCompletedService.getAssignmentCompleted(assignmentCompletedId);
	}

	@GET
	@Path(value = "/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AssignmentCompleted> getAllAssignmentsCompleted() {
		return assignmentCompletedService.getAllAssignmentsCompleted();
	}

	@GET
	@Path(value = "/assignment/{assignmentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AssignmentCompleted> getAssignmentsCompletedByAssignmentId(@PathParam(value = "assignmentId") Integer assignmentId) {
		return assignmentCompletedService.getAssignmentsCompletedByAssignmentId(assignmentId);
	}

	@GET
	@Path(value = "/studentsDone/{assignmentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentsDoneAssignmentWithId(@PathParam(value = "assignmentId") Integer assignmentId) {
		return assignmentCompletedService.getStudentsDoneAssignmentWithId(assignmentId);
	}

	@GET
	@Path(value = "/studentsNotDone/{assignmentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentsNotDoneAssignmentWithId(@PathParam(value = "assignmentId") Integer assignmentId) {
		return assignmentCompletedService.getStudentsNotDoneAssignmentWithId(assignmentId);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAssignmentCompleted(AssignmentCompleted assignmentCompleted) {
		Integer status = assignmentCompletedService.updateAssignmentCompleted(assignmentCompleted);
		if (status == 1) {
			return Response.status(Status.OK).build();
		} else if (status == -1) {
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		}
		Map<String, String> message = new HashMap<>();
		message.put("Message", "Nothing to update!");
		return Response.status(Status.NO_CONTENT).entity(message).build();
	}
}
