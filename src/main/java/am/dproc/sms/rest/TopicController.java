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

import am.dproc.sms.models.Topic;
import am.dproc.sms.services.root.TopicService;

@RestController
@Path(value = "/topic")
public class TopicController {

	@Autowired
	TopicService topic;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Topic> getAllTopics() {
		return topic.getAllTopics();
	}

	@GET
	@Path(value = "/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Topic getTopic(@PathParam(value = "id") Integer id) {
		return topic.getTopic(id);
	}

	@DELETE
	@Path(value = "/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<Integer> deleteTopic(@PathParam(value = "id") Integer id) {
		return topic.deleteTopic(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<Integer> addTopic(Topic topic) {
		return this.topic.addTopic(topic);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<Integer> editTopic(Topic topic) {
		return this.topic.editTopic(topic);
	}

}
