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

import am.dproc.sms.modules.CourseBean;
import am.dproc.sms.modules.GroupBean;
import am.dproc.sms.services.CourseService;
import am.dproc.sms.services.GroupService;

@RestController
@Path("/course")
public class CourseController {
	@Autowired
	CourseService courseService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public int create(CourseBean course) {
		String name = course.getName();
		int duration = course.getDuration();
		String description = course.getDescription();
		String location = course.getLocation();
		return courseService.create(name, duration, description, location);
	}

	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public CourseBean getById(@PathParam("id") int id) {
		return courseService.getById(id);
	}
	
//	@GET
//	@Path("/{name}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<CourseBean> getByName(@PathParam("name") String name) {
//		return courseService.getByName(name);
//	}
	
	
	
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CourseBean> get() {
		return courseService.getAll();
	}
	
	
}
