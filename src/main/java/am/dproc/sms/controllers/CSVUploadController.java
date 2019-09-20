package am.dproc.sms.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

import am.dproc.sms.models.Group;
import am.dproc.sms.models.School;
import am.dproc.sms.models.Student;
import am.dproc.sms.models.StudentInfo;
import am.dproc.sms.services.interfaces.SchoolService;

@Controller
public class CSVUploadController {
	
	@Autowired
	SchoolService schoolService;
	
	@GetMapping("/upload")
	public String uploadPage() {
		return "csvupload";
	}
	
	@PostMapping("/upload/school")
	public String uploadSchool(@RequestParam("schools") MultipartFile file) throws IOException {
		
		InputStream is = file.getInputStream();
		String[] line;
		CSVReader reader = new CSVReader(new InputStreamReader(is, "UTF-8"));
		while((line = reader.readNext()) != null) {
			System.out.println(line[0] + " " + line[1]);
			School school = new School();
			school.setName(line[0]);
			school.setAddress(line[1]);
			schoolService.addSchool(school);
		}
		
//		String line;
//		BufferedReader breader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//		while((line = breader.readLine()) != null) {
//			System.out.println(line);
//			String[] credentials = line.split(",");
//			String row = String.format("name: %s   address: %s", credentials[0], credentials[1]);
//			System.out.println(row);
//		}
//		
//		
//		ByteArrayOutputStream result = new ByteArrayOutputStream();
//		byte[] buffer = new byte[1024];
//		int length;
//		while ((length = is.read(buffer)) != -1) {
//			System.out.println("length: "+length);
//		    result.write(buffer, 0, length);
//		}
//		// StandardCharsets.UTF_8.name() > JDK 7
//		String res = result.toString("UTF-8");
//		 System.out.println(res);
		
		return "csvupload";
	}
	
	@PostMapping("/upload/student")
	public String uploadStudent(@RequestParam("students") MultipartFile file) throws IOException {
		InputStream is = file.getInputStream();
		String[] line;
		CSVReader reader = new CSVReader(new InputStreamReader(is, "UTF-8"));
		while((line = reader.readNext()) != null) {
			Arrays.deepToString(line);
			System.out.println(line[0] + " " + line[1]);
			Student student = new Student();
			student.setName(line[0]);
			student.setSurname(line[1]);
			student.setEmail(line[2]);
			
			Group group = new Group();
			group.setName(line[3]);
			
			StudentInfo info = new StudentInfo();
			info.setPassportId(line[4]);
			info.setSocialCardId(line[5]);
			info.setBirthDate(Long.parseLong(line[6]));
			info.setImageUrl(line[7]);
		}
		return "csvupload";
	}
	
}
