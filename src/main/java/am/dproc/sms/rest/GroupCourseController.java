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
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.GroupCourse;
import am.dproc.sms.services.interfaces.GroupCourseService;
import io.swagger.annotations.Api;

@RestController
@Path("/group-course")
@Api(value = "GroupCourseController")
public class GroupCourseController {

	@Autowired
	GroupCourseService groupCourseService;

	// works
	@Path("/assign")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response assignCourseToGroup(GroupCourse groupCourse) {
		int id = groupCourseService.create(groupCourse);
		if (id > 0) {
			return Response.status(Response.Status.CREATED).entity(id).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity("Record was not created").build();
		}
	}

	@Path("/{id}")
	@GET
	@Produces("application/json")
	public GroupCourse getById(@PathParam("id") int id) {
		return groupCourseService.getById(id);
	}

	@Path("/{gid}/{cid}")
	@GET
	@Produces("application/json")
	public GroupCourse getByGroupAndCourse(@PathParam("gid") int groupId,@PathParam("cid") int courseId) {
		return groupCourseService.getByGroupAndCourse(groupId, courseId);
	}

	// works
	@Path("/courseId/{id}")
	@GET
	@Produces("application/json")
	public List<GroupCourse> getByCourseId(@PathParam("id") int courseId) {
		return groupCourseService.getByCourseID(courseId);
	}

	// works
	@GET
	@Produces("application/json")
	public List<GroupCourse> getAll() {
		return groupCourseService.getAll();
	}

	// works
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public int updateGroupId(GroupCourse groupCourse) {
		return groupCourseService.update(groupCourse);
	}
	
	// works
	@Path("/groupId/{id}")
	@DELETE
	public int deleteByGroupId(@PathParam("id") int groupId) {
		return groupCourseService.deleteByGroupID(groupId);
	}

	// works
	@Path("/courseId/{id}")
	@DELETE
	public int deleteByCourseId(@PathParam("id") int courseId) {
		return groupCourseService.deleteByCourseID(courseId);
	}

	// works
	@DELETE
	public int deleteAll() {
		return groupCourseService.deleteAll();
	}
}
