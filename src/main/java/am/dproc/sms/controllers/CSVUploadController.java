package am.dproc.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CSVUploadController {
	
	@GetMapping("/upload")
	public String uploadPage() {
		return "csvupload";
	}
	
	@PostMapping("/csvupload")
	public String handleUpload(@RequestParam("file") MultipartFile file) {
		
//		new CSVReader(new FileReader(file));
		
		return "csvupload";
	}
	
}
