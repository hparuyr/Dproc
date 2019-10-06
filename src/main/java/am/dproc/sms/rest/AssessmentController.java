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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    AssessmentService assessmentService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAssessment(Assessment assessment) {
        Integer id = assessmentService.addAssessment(assessment);
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
    public Assessment getAssessment(@PathParam(value = "id") Integer id) {
        return assessmentService.getAssessment(id);
    }

    @GET
    @Path(value = "user/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Assessment> getAllAssessmentsByUserId(@PathParam(value = "userId") Integer userId) {
        return assessmentService.getAllAssessmentsByUserId(userId);
    }

    // TODO
    @GET
    @Path(value = "assignment/{assignmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Assessment> getAssessmentsByAssignmentId(@PathParam(value = "assignmentId") Integer assignmentId) {
        return assessmentService.getAssessmentsByAssignmentId(assignmentId);
    }

    // TODO
    @GET
    @Path(value = "avg/{studentId}/{courseId}")
    public Double getAverageScoreByStudentCourse(@PathParam(value = "studentId") Integer studentId, @PathParam(value = "courseId") Integer courseId) {
        return assessmentService.getAverageScoreByStudentCourse(studentId, courseId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Assessment> getAllAssessments() {
        return assessmentService.getAllAssessments();
    }

    @DELETE
    @Path(value = "/{id}")
    public Integer deleteAssessment(@PathParam(value = "id") Integer id) {
        return assessmentService.deleteAssessment(id);
    }

    @DELETE
    public Integer deleteAllAssessments() {
        return assessmentService.deleteAllAssessments();
    }

}
