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
public class StudentCommentController {

	@Autowired
	StudentCommentService comment;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/{id}")
	public StudentComment getComment(@PathParam(value = "id") Integer id) {
		return comment.getComment(id);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<StudentComment> getComments(@QueryParam(value = "topicID") Integer id) {
		return comment.getCommentsOfTopic(id);
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/{id}")
	public Response deleteComment(@PathParam(value = "id") Integer id) {
		if (comment.deleteComment(id) == 1) {
			return Response.status(Status.OK).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addComment(StudentComment comment) {
		Integer id = this.comment.addComment(comment);
		if (id == 0) {
			Map<String, String> message = new HashMap<String, String>();
			message.put("Message", "Comment cannot be empty!");
			return Response.status(Status.BAD_REQUEST).entity(message).build();
		}
		return Response.status(Status.CREATED).entity(id).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response editComment(StudentComment comment) {
		if (this.comment.editComment(comment) == 1) {
			return Response.status(Status.OK).build();
		}
		Map<String, String> message = new HashMap<String, String>();
		message.put("Message", "Nothing to update!");
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}

}
