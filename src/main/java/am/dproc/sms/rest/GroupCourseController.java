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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.GroupCourse;
import am.dproc.sms.services.interfaces.GroupCourseService;
import io.swagger.annotations.Api;

@RestController
@Path("/group-course")
@Api(value = "GroupCourseController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
public class GroupCourseController {

	@Autowired
	GroupCourseService groupCourseService;

	@POST
	public Response assignCourseToGroup(GroupCourse groupCourse) {
		Integer id = groupCourseService.add(groupCourse);
		if (id == -1) {
			Map<String, String> message = new HashMap<>();
			message.put("Message", "All fields must be filled!");
			return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
		} else if (id == 0) {
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}
		return Response.status(Response.Status.CREATED).entity(id).build();
	}

	@Path("/{id}")
	@GET
	public GroupCourse getById(@PathParam("id") Integer id) {
		return groupCourseService.getById(id);
	}

	@Path("/{groupId}/{courseId}")
	@GET
	public GroupCourse getByGroupAndCourse(
			@PathParam("groupId") Integer groupId,
			@PathParam("courseId") Integer courseId) {
		return groupCourseService.getByGroupAndCourse(groupId, courseId);
	}

	@Path("/courseID/{id}")
	@GET
	public List<GroupCourse> getByCourseId(@PathParam("id") Integer courseId) {
		return groupCourseService.getByCourseID(courseId);
	}
	
	@Path("/teacherId/{teacherId}")
	@GET
	public List<GroupCourse> getByTeacherId(@PathParam("teacherId") Integer teacherId) {
		return groupCourseService.getByTeacherId(teacherId);
	}

	@GET
	public List<GroupCourse> getAll() {
		return groupCourseService.getAll();
	}

	@PUT
	public Integer updateGroupId(GroupCourse groupCourse) {
		return groupCourseService.update(groupCourse);
	}
	
	@Path("/groupId/{id}")
	@DELETE
	public Integer deleteByGroupId(@PathParam("id") Integer groupId) {
		return groupCourseService.deleteByGroupId(groupId);
	}

	@Path("/courseId/{id}")
	@DELETE
	public Integer deleteByCourseId(@PathParam("id") Integer courseId) {
		return groupCourseService.deleteByCourseId(courseId);
	}

	@DELETE
	public Integer deleteAll() {
		return groupCourseService.deleteAll();
	}
}
