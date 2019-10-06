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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.School;
import am.dproc.sms.services.interfaces.SchoolService;
import io.swagger.annotations.Api;

@RestController
@Path("/schoolService")
@Api(value = "SchoolController")
public class SchoolController {

	@Autowired
	SchoolService schoolService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addSchool(School school) {
		Integer id = schoolService.addSchool(school);
		if (id == -1) {
			Map<String, String> message = new HashMap<String, String>();
			message.put("Message", "All fields must be filled!");
			return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
		} else if (id == 0) {
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}
		return Response.status(Response.Status.CREATED).entity(id).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/{id}")
	public School getSchool(@PathParam(value = "id") Integer id) {
		return schoolService.getSchool(id);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<School> getSchools() {
		return schoolService.getSchools();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<Integer> updateSchool(School school) {
		if (this.schoolService.updateSchool(school) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
		}
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path(value = "/{id}")
	public ResponseEntity<Integer> deleteSchool(@PathParam(value = "id") Integer id) {
		if (schoolService.deleteSchool(id) == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(1);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
		}
	}

}
