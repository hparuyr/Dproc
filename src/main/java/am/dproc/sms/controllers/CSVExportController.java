package am.dproc.sms.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.services.interfaces.CSVExportService;
import io.swagger.annotations.Api;

@RestController
@Api(value = "CSVExportController")
public class CSVExportController {

	@Autowired
	CSVExportService csv;

	@RequestMapping(value="/export", method = RequestMethod.GET, produces = "text/csv")
	public FileSystemResource greeting(@RequestParam(value = "teacherId") Integer teacherId) {
		try {
			return new FileSystemResource(csv.getCSVFile(teacherId));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
