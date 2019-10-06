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
public class TopicController {

	@Autowired
	TopicService topic;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addTopic(Topic topic) {
		Integer id = this.topic.addTopic(topic);
		if (id == 0) {
			Map<String, String> message = new HashMap<String, String>();
			message.put("Message", "All fields must be filled!");
			return Response.status(Status.BAD_REQUEST).entity(message).build();
		}
		return Response.status(Status.CREATED).entity(id).build();
	}

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

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response editTopic(Topic topic) {
		Integer status = this.topic.editTopic(topic);
		if (status == 1) {
			return Response.status(Status.OK).build();
		} else if (status == -1) {
			return Response.status(Status.SERVICE_UNAVAILABLE).build();
		}
		Map<String, String> message = new HashMap<String, String>();
		message.put("Message", "Nothing to update!");
		return Response.status(Status.NO_CONTENT).entity(message).build();
	}

	@DELETE
	@Path(value = "/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteTopic(@PathParam(value = "id") Integer id) {
		if (topic.deleteTopic(id) == 1) {
			return Response.status(Status.OK).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

}
