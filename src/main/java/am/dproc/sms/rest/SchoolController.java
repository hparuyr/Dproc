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

import am.dproc.sms.models.School;
import am.dproc.sms.services.root.SchoolService;

@RestController
@Path("/school")
public class SchoolController {
	@Autowired
	SchoolService school;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<Integer> addSchool(School school) {
		if(this.school.addSchool(school)==1) {
		return ResponseEntity.status(HttpStatus.OK)
		        .body(1);
	}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		            .body(0);
		}
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path(value = "/{id}")
	public School getSchool(@PathParam(value = "id") Integer id) {
		return school.getSchool(id);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<School> getSchools() {
		return school.getSchools();
	}
	

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<Integer> updateSchool(School school) {
		if( this.school.updateSchool(school)==1) {
			return ResponseEntity.status(HttpStatus.OK)
			        .body(1);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		            .body(0);
		}
	}
	
	@DELETE
	@Produces({MediaType.APPLICATION_JSON})
	@Path(value = "/{id}")
	public ResponseEntity<Integer> deleteSchool(@PathParam(value = "id") Integer id) {
		if( school.deleteSchool(id)==1) {
			return ResponseEntity.status(HttpStatus.OK)
			        .body(1);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		            .body(0);
		}
	}
	
	
	
}
