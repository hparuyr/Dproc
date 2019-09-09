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

import am.dproc.sms.models.TeacherInfo;
import am.dproc.sms.services.interfaces.TeacherInfoService;


@RestController
@Path("/teacherInfo")
public class TeacherInfoControlller {
	
	@Autowired
	TeacherInfoService teacherInfo;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseEntity<Integer> addTeacherInfo(TeacherInfo teacherInfo) {
		if(this.teacherInfo.addTeacherInfo(teacherInfo)==1) {
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
	public ResponseEntity<Integer> updateTeacherInfo(TeacherInfo teacherInfo) {
		if( this.teacherInfo.updateTeacherInfo(teacherInfo)==1) {
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
	public ResponseEntity<Integer> deleteTeacherInfo(@PathParam(value = "id") Integer teacherId) {
		if( teacherInfo.deleteTeacherInfo(teacherId)==1) {
			return ResponseEntity.status(HttpStatus.OK)
			        .body(1);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		            .body(0);
		}
	}

}
