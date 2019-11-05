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
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
public class LessonController {

	@Autowired
	LessonService lessonService;
	
	@POST
	public Response addCourse(Lesson lesson) {
		Integer id = this.lessonService.addLesson(lesson);
		if (id == -1) {
			Map<String, String> message = new HashMap<>();
			message.put("Message", "All fields must be filled!");
			return Response.status(Status.BAD_REQUEST).entity(message).build();
		} else if (id == 0) {
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}
		return Response.status(Status.CREATED).entity(id).build();
	}

	@GET
	@Path(value = "/{id}")
	public Lesson getLesson(@PathParam(value = "id") Integer id) {
		return lessonService.getLesson(id);
	}

	@GET
	public List<Lesson> getLessons() {
		return lessonService.getAllLesson();
	}

	@PUT
	public Response updateLesson(Lesson lesson) {
		Integer status = this.lessonService.updateLesson(lesson);
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
	public Response deleteLesson(@PathParam(value = "id") Integer id) {
		if (lessonService.deleteLesson(id) == 1) {
			return Response.status(Status.OK).build();
		}
		Map<String, String> message = new HashMap<>();
		message.put("Message", "First you must delete the topics of this lessonService!");
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}

}
