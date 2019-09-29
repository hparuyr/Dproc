package am.dproc.sms.controllers;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.services.interfaces.CSVExportService;
import io.swagger.annotations.Api;

@Api(value = "CSVExportController")
@RestController
@Path(value = "/csv")
public class CSVExportController {

	@Autowired
	CSVExportService csv;

	@GET
	@Path(value = "/export/{teacherId}")
	public File greeting(@PathParam(value = "teacherId") Integer teacherId) {
		System.out.println("inside csv export controller");
		try {
			return csv.getCSVFile(teacherId);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
