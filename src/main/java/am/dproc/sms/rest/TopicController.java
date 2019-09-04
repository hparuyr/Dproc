package am.dproc.sms.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.Topic;
import am.dproc.sms.services.root.TopicService;

@RestController
@Path(value = "/topic")
public class TopicController {

	@Autowired
	TopicService url;

	// Works
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path(value = "/{lessonID}")
	public List<Topic> getTopic(@PathParam(value = "lessonID") Integer lessonID) {
		return url.getLessonTopics(lessonID);
	}

	// Works
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Topic> getTopic() {
		return url.getAllTopics();
	}

	// Works
	@DELETE
	@Path(value = "/{lessonID}")
	public Integer deleteTopic(@PathParam(value = "lessonID") Integer lessonID) {
		return url.deleteTopic(lessonID);
	}

	// Works
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Integer addTopic(Topic topic) {
		return this.url.addTopic(topic);
	}

}
