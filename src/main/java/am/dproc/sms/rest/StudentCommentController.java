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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.StudentComment;
import am.dproc.sms.services.interfaces.StudentCommentService;
import io.swagger.annotations.Api;

@RestController
@Path(value = "/comments")
@Api(value = "StudentCommentController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
public class StudentCommentController {

	@Autowired
	StudentCommentService studentCommentService;

	@POST
	public Response addComment(StudentComment comment) {
		Integer id = studentCommentService.addComment(comment);
		if (id == -1) {
			Map<String, String> message = new HashMap<>();
			message.put("Message", "All fields must be filled!");
			return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
		} else if (id == 0) {
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}
		return Response.status(Response.Status.CREATED).entity(id).build();
	}

	@GET
	@Path(value = "/{id}")
	public StudentComment getComment(@PathParam(value = "id") Integer id) {
		return studentCommentService.getComment(id);
	}

	@GET
	public List<StudentComment> getComments(@QueryParam(value = "topicID") Integer id) {
		return studentCommentService.getCommentsOfTopic(id);
	}

	@PUT
	public Response updateComment(StudentComment comment) {
		Integer status = this.studentCommentService.updateComment(comment);
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
	public Response deleteComment(@PathParam(value = "id") Integer id) {
		if (studentCommentService.deleteComment(id) == 1) {
			return Response.status(Status.OK).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

}