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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.Lesson;
import am.dproc.sms.services.root.LessonService;

@RestController
@Path(value = "/lesson")
public class LessonController {

	@Autowired
	LessonService lesson;

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path(value = "/{id}")
	public Lesson getLesson(@PathParam(value = "id") Integer id) {
		return lesson.getLesson(id);
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Lesson> getLessons() {
		return lesson.getAllLesson();
	}

	@DELETE
	@Produces({MediaType.APPLICATION_JSON})
	@Path(value = "/{id}")
	public ResponseEntity<Integer> deleteLesson(@PathParam(value = "id")  Integer id) {
		return lesson.deleteLesson(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<Integer> addCourse(Lesson lesson) {
		return this.lesson.addLesson(lesson);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<Integer> editLesson(Lesson lesson) {
		return this.lesson.editLesson(lesson);
	}
	
}
