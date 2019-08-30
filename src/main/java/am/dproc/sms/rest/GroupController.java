package am.dproc.sms.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.modules.GroupBean;
import am.dproc.sms.services.GroupService;

@RestController
@Path("/group")
public class GroupController {
	@Autowired
	GroupService groupService;
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.APPLICATION_JSON)
	public int create(@FormParam("name") String name, @FormParam("schoolId") int schoolId) {
		return groupService.create(name,schoolId);
	}

	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public GroupBean get(@PathParam("id") int id) {
		return groupService.get(id);
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<GroupBean> get() {
		return groupService.getAll();
	}
	
	
}
