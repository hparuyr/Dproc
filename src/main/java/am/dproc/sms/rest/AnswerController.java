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

import am.dproc.sms.models.Answer;
import am.dproc.sms.services.root.AnswerService;

@RestController
@Path(value = "/answer")
public class AnswerController {

	@Autowired
	AnswerService answerService;

	@GET
	@Path(value = "/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Answer getAnswer(@PathParam(value = "id") Integer id) {
		return answerService.getAnswer(id);
	}	
	
	@GET
	@Path(value = "/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Answer> getAllAnswers() {
		return answerService.getAllAnswers();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Integer createAnswer(Answer answer) {
		return answerService.createAnswer(answer);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Integer updateAnswer(Answer answer) {
		return answerService.updateAnswer(answer);
	}

	@DELETE
	@Path(value = "/{id}")
	public Integer deleteAnswer(@PathParam(value = "id") Integer id) {
		return answerService.deleteAnswer(id);
	}

}
