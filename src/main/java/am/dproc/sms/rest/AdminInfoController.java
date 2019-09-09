package am.dproc.sms.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import am.dproc.sms.models.AdminInfo;
import am.dproc.sms.services.root.AdminInfoService;

@RestController
@Path("/adminInfo")
public class AdminInfoController {
	
	@Autowired
	AdminInfoService adminInfo;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<Integer> addAdminInfo(AdminInfo adminInfo) {
		if(this.adminInfo.addAdminInfo(adminInfo)==1) {
		return ResponseEntity.status(HttpStatus.CREATED)
		        .body(1);
	}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		            .body(0);
		}
	}
	

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<Integer> updateAdminInfo(AdminInfo adminInfo) {
		if( this.adminInfo.updateAdminInfo(adminInfo)==1) {
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
	public ResponseEntity<Integer> deleteAdminInfo(@PathParam(value = "id") Integer adminId) {
		if( adminInfo.deleteAdminInfo(adminId)==1) {
			return ResponseEntity.status(HttpStatus.OK)
			        .body(1);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		            .body(0);
		}
	}

}
