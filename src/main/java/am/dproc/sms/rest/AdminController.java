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

import am.dproc.sms.models.Admin;
import am.dproc.sms.services.interfaces.AdminService;
import io.swagger.annotations.Api;

@RestController
@Path("/admin")
@Api(value = "AdminController")
public class AdminController {

	@Autowired
	AdminService adminService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addAdmin(Admin admin) {
		int id = adminService.addAdmin(admin);
		if (id > 0) {
			return Response.status(Response.Status.CREATED).entity(id).build();
		} else if (id == 0) {
			Map<String, String> message = new HashMap<>();
			message.put("message", String.format("User with email %s already exists", admin.getEmail()));
			return Response.status(Response.Status.CONFLICT).entity(message).build();
		} else {
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("").build();
		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/{id}")
	public Response getAdmin(@PathParam(value = "id") Integer id) {
		Admin admin = adminService.getAdmin(id);
		if (admin != null) {
			return Response.status(Response.Status.OK).entity(admin).build();
		}
		Map<String, String> message = new HashMap<>();
		message.put("message", String.format("Admin with id %s not found", id));
		return Response.status(Response.Status.NOT_FOUND).entity(message).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAdmins() {
		List<Admin> admins = adminService.getAdmins();
		return Response.status(Response.Status.OK).entity(admins).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateAdmin(Admin admin) {
		int adminsUpdated = adminService.updateAdmin(admin);
		if (adminsUpdated > 0) {
			return Response.status(Response.Status.OK).entity(admin).build();
		} else if (adminsUpdated == 0) {
			Map<String, String> message = new HashMap<>();
			message.put("message", String.format("Admin with id %s not updated", admin.getId()));
			return Response.status(Response.Status.NOT_FOUND).entity(message).build();
		}
		return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("").build();
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/{id}")
	public Response deleteAdmin(@PathParam(value = "id") Integer id) {
		int adminsDeleted = adminService.deleteAdmin(id);
		Map<String, String> message = new HashMap<>();
		if (adminsDeleted > 0) {
			message.put("message",  String.format("Admin with id %s deleted", id));
			return Response.status(Response.Status.OK).entity(message).build();
		} else if(adminsDeleted == 0) {
			message.put("message", String.format("Admin with id %s not deleted", id));
			return Response.status(Response.Status.NOT_FOUND).entity(message).build();
		}
		return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("").build();
	}

}
