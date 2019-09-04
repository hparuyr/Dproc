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

import am.dproc.sms.models.Question;
import am.dproc.sms.services.root.QuestionService;

@RestController
@Path(value = "/question")
public class QuestionController {

	@Autowired
	QuestionService questionService;

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

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Integer createQuestion(Question question) {
		return questionService.createQuestion(question);
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
