package am.dproc.sms.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.services.interfaces.TestResultService;
import io.swagger.annotations.Api;

import java.util.HashMap;
import java.util.Map;

@RestController
@Path(value = "/result")
@Api(value = "TestResultController")
public class TestResultController {

	@Autowired
	TestResultService testResultService;

	@POST
	@Path(value = "/{testId}/{studentId}/{score}")
	public Response createTestResult(@PathParam(value = "testId") Integer testId,
			@PathParam(value = "studentId") Integer studentId, @PathParam(value = "score") Double score) {
		Integer id = testResultService.createTestResult(testId, studentId, score);
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
	@Path(value = "/last/{testId}/{studentId}")
	public Double getLastTestResultForStudent(@PathParam(value = "testId") Integer testId,
			@PathParam(value = "studentId") Integer studentId) {
		return testResultService.getLastTestResultForStudent(testId, studentId);
	}

	@GET
	@Path(value = "/{id}")
	public Double getTestResultById(@PathParam(value = "id") Integer id) {
		return testResultService.getTestResultById(id);
	}

	@GET
	@Path(value = "/avg/{studentId}")
	public Double getAverageTestResultForStudent(@PathParam(value = "studentId") Integer studentId) {
		return testResultService.getAverageTestResultForStudent(studentId);
	}

	@GET
	@Path(value = "/avg/{studentId}/{courseId}")
	public Double getAverageTestResultForStudentCourse(@PathParam(value = "studentId") Integer studentId, @PathParam(value = "courseId") Integer courseId) {
		return testResultService.getAverageTestResultForStudentCourse(studentId, courseId);
	}

	@PUT
	@Path(value = "/{testId}/{studentId}/{score}")
	public Integer updateTestResultForStudent(@PathParam(value = "testId") Integer testId,
			@PathParam(value = "studentId") Integer studentId, @PathParam(value = "score") Double score) {
		return testResultService.updateTestResultForStudent(testId, studentId, score);
	}

	@PUT
	@Path(value = "/{id}/{score}")
	public Integer updateTestResultById(@PathParam(value = "id") Integer id, @PathParam(value = "score") Double score) {
		return testResultService.updateTestResultById(id, score);
	}

	@DELETE
	@Path(value = "/{id}")
	public Integer deleteTestResultById(@PathParam(value = "id") Integer id) {
		return testResultService.deleteTestResultById(id);
	}

	@DELETE
	@Path(value = "/{testId}/{studentId}")
	public Integer deleteTestResultForStudent(@PathParam(value = "testId") Integer testId, @PathParam(value = "studentId") Integer studentId) {
		return testResultService.deleteTestResultForStudent(testId, studentId);
	}

}
