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

import am.dproc.sms.models.Answer;
import am.dproc.sms.services.interfaces.AnswerService;
import io.swagger.annotations.Api;

@RestController
@Path(value = "/answer")
@Api(value = "AnswerController")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAnswer(Answer answer) {
        Integer id = answerService.createAnswer(answer);
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
    public Answer getAnswer(@PathParam(value = "id") Integer id) {
        return answerService.getAnswer(id);
    }

    @GET
    @Path(value = "/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
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