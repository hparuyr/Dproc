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
@Path("/groupService-courseService")
@Api(value = "GroupCourseController")
public class GroupCourseController {

	@Autowired
	GroupCourseService groupCourseService;

	@Path("/assign")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response assignCourseToGroup(GroupCourse groupCourse) {
		Integer id = groupCourseService.create(groupCourse);
		if (id == -1) {
			Map<String, String> message = new HashMap<String, String>();
			message.put("Message", "All fields must be filled!");
			return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
		} else if (id == 0) {
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}
		return Response.status(Response.Status.CREATED).entity(id).build();
	}

	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public GroupCourse getById(@PathParam("id") int id) {
		return groupCourseService.getById(id);
	}

	@Path("/{gid}/{cid}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public GroupCourse getByGroupAndCourse(@PathParam("gid") int groupId,@PathParam("cid") int courseId) {
		return groupCourseService.getByGroupAndCourse(groupId, courseId);
	}

	@Path("/courseId/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<GroupCourse> getByCourseId(@PathParam("id") int courseId) {
		return groupCourseService.getByCourseID(courseId);
	}
	
	@Path("/teacherId/{teacherId}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<GroupCourse> getByTeacherId(@PathParam("teacherId") int teacherId) {
		return groupCourseService.getByTeacherID(teacherId);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<GroupCourse> getAll() {
		return groupCourseService.getAll();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public int updateGroupId(GroupCourse groupCourse) {
		return groupCourseService.update(groupCourse);
	}
	
	@Path("/groupId/{id}")
	@DELETE
	public int deleteByGroupId(@PathParam("id") int groupId) {
		return groupCourseService.deleteByGroupID(groupId);
	}

	@Path("/courseId/{id}")
	@DELETE
	public int deleteByCourseId(@PathParam("id") int courseId) {
		return groupCourseService.deleteByCourseID(courseId);
	}

	@DELETE
	public int deleteAll() {
		return groupCourseService.deleteAll();
	}
}
