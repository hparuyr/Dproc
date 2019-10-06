package am.dproc.sms.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.Question;
import am.dproc.sms.services.interfaces.QuestionService;
import io.swagger.annotations.Api;

@RestController
@Path(value = "/question")
@Api(value = "QuestionController")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createQuestion(Question question) {
		Integer id = questionService.createQuestion(question);
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
	@Produces({MediaType.APPLICATION_JSON})
	@Path(value = "/{id}")
	public Question getQuestion(@PathParam(value = "id") Integer id) {
		return questionService.getQuestion(id);
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path(value = "/all")
	public List<Question> getAllQuestions() {
		return questionService.getAllQuestions();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Integer updateQuestion(Question question) {
		return questionService.updateQuestion(question);
	}

	@DELETE
	@Path(value = "/{id}")
	public Integer deleteQuestion(@PathParam(value = "id") Integer id) {
		return questionService.deleteQuestion(id);
	}
}
