package am.dproc.sms.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
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

import am.dproc.sms.modules.GroupCourseBean;
import am.dproc.sms.modules.UserBean;
import am.dproc.sms.services.GroupCourseService;

@RestController
@Path("/group-course")
public class GroupCourseController {

	@Autowired
	GroupCourseService groupCourseService;

	// works
	@Path("/assign")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response assignCourseToGroup(GroupCourseBean groupCourseBean) {
		int success = groupCourseService.create(groupCourseBean.getGroupId(), groupCourseBean.getCourseId(),
				groupCourseBean.getTeacherId(), groupCourseBean.getStartDate(), groupCourseBean.isFinished());
		if (success == 1) {
			return Response.status(Response.Status.CREATED).entity("Record is created").build();
		} else {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Record was not created").build();
		}
	}

	@Path("/{id}")
	@GET
	@Produces("application/json")
	public GroupCourseBean getById(@PathParam("id") int id) {
		return groupCourseService.getById(id);
	}

	@Path("/{gid}/{cid}")
	@GET
	@Produces("application/json")
	public GroupCourseBean getByGroupAndCourse(@PathParam("gid") int groupId,@PathParam("cid") int courseId) {
		return groupCourseService.getByGroupAndCourse(groupId, courseId);
	}

	// works
	@Path("/courseId/{id}")
	@GET
	@Produces("application/json")
	public List<GroupCourseBean> getByCourseId(@PathParam("id") int courseId) {
		return groupCourseService.getByCourseID(courseId);
	}

	// works
	@GET
	@Produces("application/json")
	public List<GroupCourseBean> getAll() {
		return groupCourseService.getAll();
	}

	// works
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public int updateGroupId(GroupCourseBean groupCourseBean) {
		return groupCourseService.update(groupCourseBean);
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
