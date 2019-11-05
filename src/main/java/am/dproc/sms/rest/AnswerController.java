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
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.Answer;
import am.dproc.sms.services.interfaces.AnswerService;
import io.swagger.annotations.Api;

@RestController
@Path(value = "/answer")
@Api(value = "AnswerController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @POST
    public Response addAnswer(Answer answer) {
        Integer id = answerService.addAnswer(answer);
        if (id == -1) {
            Map<String, String> message = new HashMap<>();
            message.put("Message", "All fields must be filled!");
            return Response.status(Status.BAD_REQUEST).entity(message).build();
        } else if (id == 0) {
            return Response.status(Status.SERVICE_UNAVAILABLE).build();
        }
        return Response.status(Status.CREATED).entity(id).build();
    }

    @GET
    @Path(value = "/{id}")
    public Answer getAnswer(@PathParam(value = "id") Integer id) {
        return answerService.getAnswer(id);
    }

    @GET
    @Path(value = "/all")
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @PUT
    public Response updateAnswer(Answer answer) {
        Integer status = this.answerService.updateAnswer(answer);
        if (status == 1) {
            return Response.status(Status.OK).build();
        } else if (status == -1) {
            return Response.status(Status.SERVICE_UNAVAILABLE).build();
        }
        Map<String, String> message = new HashMap<>();
        message.put("Message", "Nothing to update!");
        return Response.status(Status.BAD_REQUEST).entity(message).build();
    }

    @DELETE
    @Path(value = "/{id}")
    public Integer deleteAnswer(@PathParam(value = "id") Integer id) {
        return answerService.deleteAnswer(id);
    }

}