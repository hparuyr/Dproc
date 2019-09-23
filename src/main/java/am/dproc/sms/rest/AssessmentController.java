package am.dproc.sms.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	@Path(value = "/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Assessment getAssessment(@PathParam(value = "id") Integer id) {
		return asService.getAssessment(id);
	}

	@GET
	@Path(value = "title/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Assessment getAssessmentByTitle(@PathParam(value = "title") String title) {
		return asService.getAssessmentByTitle(title);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Assessment> getAllAssessments() {
		return asService.getAllAssessments();
	}

	@GET
	@Path(value = "user/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Assessment> getAllAssessmentsByUserId(@PathParam(value = "userId") Integer userId) {
		return asService.getAllAssessmentsByUserId(userId);
	}

	@GET
	@Path(value = "assignment/{assignmentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Assessment> getAssessmentsByAssignmentId(@PathParam(value = "assignmentId") Integer assignmentId) {
		return asService.getAssessmentsByAssignmentId(assignmentId);
	}

	@GET
	@Path(value = "avg/{studentId}/{courseId}")
	public Double getAverageScoreByStudentCourse(@PathParam(value = "studentId") Integer studentId, @PathParam(value="courseId") Integer courseId) {
		return asService.getAverageScoreByStudentCourse(studentId, courseId);
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
	public Integer addAssessment(Assessment asses) {
		return asService.addAssessment(asses);
	}

}
