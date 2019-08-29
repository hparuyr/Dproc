package am.dproc.sms.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.db.GroupCourseDAO;
import am.dproc.sms.modules.UserBean;
import am.dproc.sms.services.GroupCourseService;

@RestController
@Path("/")
public class GroupCourseController {

	@Autowired
	GroupCourseService groupCourseService;
	
	@Path("/enroll")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public String hello(@FormParam("firstname") String firstname, @FormParam("lastname") String lastname) {
		return "Hello " + firstname + " " + lastname;
	}

	@Path("/user")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public UserBean json(UserBean user) {
//		return "Name: " +  user.getFirstname() +"\nLastname: " + user.getLastname();
		return user;
	}
	
	@Path("/group/{gid}/course/{cid}")
	@POST
	public void assign() {
		
	}
	
	@Path("/test")
	public int test() {
		return groupCourseService.create(10, 11);
	}
}
