package am.dproc.sms.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.Assessment;
import am.dproc.sms.services.interfaces.AssessmentService;
import io.swagger.annotations.Api;

@RestController
@Path(value = "/assessment")
@Api(value = "AssessmentController")
public class AssessmentController {

	@Autowired
	AssessmentService asService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/{id}")
	public Assessment getAssessment(@PathParam(value = "id") Integer id) {
		return asService.getAssessment(id);
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Assessment> getAllAssessments() {
		return asService.getAllAssessments();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/userID")
	public List<Assessment> getAllAssessmentsByUserId(@QueryParam(value = "userID") Integer userId) {
		return asService.getAllAssessmentsByUserId(userId);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/assignment")
	public List<Assessment> getAssessmentsByAssignmentId(@QueryParam(value = "assignmentID") Integer assignmentId) {
		return asService.getAssessmentsByAssignmentId(assignmentId);
	}

	@DELETE
	@Path(value = "/{id}")
	public Integer deleteAssessment(@PathParam(value = "id") Integer id) {
		return asService.deleteAssessment(id);
	}

	@DELETE
	public Integer deleteAllAssessments() {
		return asService.deleteAllAssessments();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Integer addAssessment(Assessment asses) {
		return asService.addAssessment(asses);
	}

}
