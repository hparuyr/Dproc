package am.dproc.sms.rest;

import java.io.File;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import am.dproc.sms.services.interfaces.StudentReportService;
import io.swagger.annotations.Api;

@RestController
@Path(value = "studentReport")
@Api(value = "StudentReportController")
@Produces("application/pdf")
public class StudentReportController {

	@Autowired
	StudentReportService repService;

	@GET
	@Path(value = "/{studentId}")
	public Response getStudentReport(@PathParam(value = "studentId") Integer studentId) {
		File file = repService.getStudentReport(studentId);
		return Response.ok(file).header("Content-Disposition", "attachment;").build();
	}

}
