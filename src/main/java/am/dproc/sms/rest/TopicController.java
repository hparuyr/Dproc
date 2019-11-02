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

import am.dproc.sms.models.Topic;
import am.dproc.sms.services.interfaces.TopicService;
import io.swagger.annotations.Api;

@RestController
@Path(value = "/topic")
@Api(value = "TopicController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
public class TopicController {

	@Autowired
	TopicService topicService;

	@POST
	public Response addTopic(Topic topic) {
		Integer id = topicService.addTopic(topic);
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
	public List<Topic> getAllTopics() {
		return topicService.getAllTopics();
	}

	@GET
	@Path(value = "/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Topic getTopic(@PathParam(value = "id") Integer id) {
		return topicService.getTopic(id);
	}

	@PUT
	public Response updateTopic(Topic topic) {
		Integer status = this.topicService.updateTopic(topic);
		if (status == 1) {
			return Response.status(Status.OK).build();
		} else if (status == -1) {
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		}
		Map<String, String> message;
		message = new HashMap<>();
		message.put("Message", "Nothing to update!");
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}

	@DELETE
	@Path(value = "/{id}")
	public Response deleteTopic(@PathParam(value = "id") Integer id) {
		if (topicService.deleteTopic(id) == 1) {
			return Response.status(Status.OK).build();
		}
		Map<String, String> message = new HashMap<>();
		message.put("Message", "First you must delete the comments of this topicService!");
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}

}
