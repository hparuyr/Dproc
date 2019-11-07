package am.dproc.sms.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.AuthenticationRequest;
import am.dproc.sms.models.AuthenticationResponse;
import am.dproc.sms.services.impl.JwtUtil;
import am.dproc.sms.services.interfaces.StudentService;
import io.swagger.annotations.Api;

@RestController
@Path("/authenticate")
@Api(value = "AuthController")
public class AuthController {

	@Autowired
	StudentService studentService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired 
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtTokenUtil;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception {
			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			} catch (BadCredentialsException e) {
				throw new Exception("Incorrect username or password",e);
			}
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			final String jwt = jwtTokenUtil.generateToken(userDetails);
			return Response.status(Response.Status.OK).entity(new AuthenticationResponse(jwt)).build();
	}
}
