package am.dproc.sms.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.web.bind.annotation.RestController;

@RestController
@Path("/")
public class SecurityTestController {

	@GET
	public String home() {
		return ("<h1>Welcome</h1>");
	}
	
	@GET
	@Path("/user")
	public String user() {
		return ("<h1>Welcome User</h1>");
	}
	
	@GET
	@Path("/admin")
	public String admin() {
		return ("<h1>Welcome Admin</h1>");
	}
		
}
