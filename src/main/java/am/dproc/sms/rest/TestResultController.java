package am.dproc.sms.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.services.root.TestResultService;



@RestController
@Path(value = "/result")
public class TestResultController {
	@Autowired
	TestResultService testResultService;

	@POST
	@Path(value = "/{testId}/{userId}/{score}")
	public Integer createTestResult(@PathParam(value = "testId") Integer testId,
			@PathParam(value = "userId") Integer userId, @PathParam(value = "score") Double score) {
		return testResultService.createTestResult(testId, userId, score);
	}

	@GET
	@Path(value = "/last/{testId}/{userId}")
	public Double getLastTestResultForUser(@PathParam(value = "testId") Integer testId,
			@PathParam(value = "userId") Integer userId) {
		return testResultService.getLastTestResultForUser(testId, userId);
	}

	@GET
	@Path(value = "/{id}")
	public Double getTestResultById(@PathParam(value = "id") Integer id) {
		return testResultService.getTestResultById(id);
	}

	@GET
	@Path(value = "/avg/{userId}")
	public Double getAverageTestResultForUser(@PathParam(value = "userId") Integer userId) {
		return testResultService.getAverageTestResultForUser(userId);
	}

	@PUT
	@Path(value = "/{testId}/{userId}/{score}")
	public Integer updateTestResultForUser(@PathParam(value = "testId") Integer testId,
			@PathParam(value = "userId") Integer userId, @PathParam(value = "score") Double score) {
		return testResultService.updateTestResultForUser(testId, userId, score);
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
	@Path(value = "/{testId}/{userId}")
	public Integer deleteTestResultForUser(@PathParam(value = "testId") Integer testId, @PathParam(value = "userId") Integer userId) {
		return testResultService.deleteTestResultForUser(testId, userId);
	}

}
