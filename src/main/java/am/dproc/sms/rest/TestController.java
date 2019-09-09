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
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.Test;
import am.dproc.sms.services.interfaces.TestService;

@RestController
@Path(value = "/test")
public class TestController {
	@Autowired
	TestService testService;

	@GET
	@Path(value = "/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Test getTest(@PathParam(value = "id") Integer id) {
		return testService.getTest(id);
	}

	@GET
	@Path(value = "/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Test> getAllTests() {
		return testService.getAllTests();
	}

	@GET
	@Path(value = "/hi")
	public String hi() {
		return "HI";
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Integer createTest(Test test) {
		return testService.createTest(test);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Integer updateTest(Test test) {
		return testService.updateTest(test);
	}

	@DELETE
	@Path(value = "/{id}")
	public Integer deleteTest(@PathParam(value = "id") Integer id) {
		return testService.deleteTest(id);
	}
}
