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

import am.dproc.sms.models.Lesson;
import am.dproc.sms.services.interfaces.LessonService;
import io.swagger.annotations.Api;

@RestController
@Path(value = "/lesson")
@Api(value = "LessonController")
public class LessonController {

	@Autowired
	LessonService lesson;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/{id}")
	public Lesson getLesson(@PathParam(value = "id") Integer id) {
		return lesson.getLesson(id);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Lesson> getLessons() {
		return lesson.getAllLesson();
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/{id}")
	public Response deleteLesson(@PathParam(value = "id") Integer id) {
		if (lesson.deleteLesson(id) == 1) {
			return Response.status(Status.OK).build();
		}
		Map<String, String> message = new HashMap<String, String>();
		message.put("Message", "First you must delete the topics of this lesson!");
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addCourse(Lesson lesson) {
		Integer id = this.lesson.addLesson(lesson);
		if (id == 0) {
			Map<String, String> message = new HashMap<String, String>();
			message.put("Message", "All fields must be filled!");
			return Response.status(Status.BAD_REQUEST).entity(message).build();
		}
		return Response.status(Status.CREATED).entity(id).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response editLesson(Lesson lesson) {
		if (this.lesson.editLesson(lesson) > 0) {
			return Response.status(Status.OK).build();
		}
		Map<String, String> message = new HashMap<String, String>();
		message.put("Message", "Nothing to update!");
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}

}
