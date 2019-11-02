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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.Admin;
import am.dproc.sms.services.interfaces.AdminService;
import io.swagger.annotations.Api;

@RestController
@Path("/admin")
@Api(value = "AdminController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
public class AdminController {
	
	@Autowired
	AdminService admin;

	@POST
	public ResponseEntity<Integer> addAdmin(Admin admin) {
		if (this.admin.addAdmin(admin) == 1) {
			return ResponseEntity.status(HttpStatus.CREATED).body(1);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
		}
	}

	@GET
	@Path(value = "/{id}")
	public Admin getAdmin(@PathParam(value = "id") Integer id) {
		return admin.getAdmin(id);
	}

	@GET
	public List<Admin> getAdmins() {
		return admin.getAdmins();
	}

	@PUT
	public ResponseEntity<Integer> updateAdmin(Admin admin) {
		if (this.admin.updateAdmin(admin) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
		}
	}

	@DELETE
	@Path(value = "/{id}")
	public ResponseEntity<Integer> deleteAdmin(@PathParam(value = "id") Integer id) {
		if (admin.deleteAdmin(id) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
		}
	}

}
