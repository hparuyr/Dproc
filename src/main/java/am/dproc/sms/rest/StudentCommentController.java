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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Integer> deleteComment(@PathParam(value = "id") Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(comment.deleteComment(id));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<Integer> addComment(StudentComment comment) {
		Integer id = this.comment.addComment(comment);
		if (id == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Message", "Comment cannot be empty!").body(0);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<Integer> editComment(StudentComment comment) {
		if (this.comment.editComment(comment) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
	}

}
