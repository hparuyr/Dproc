package am.dproc.sms.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
import am.dproc.sms.services.interfaces.GroupService;
import am.dproc.sms.services.interfaces.SchoolService;
import am.dproc.sms.services.interfaces.StudentInfoService;
import am.dproc.sms.services.interfaces.StudentService;

@Controller
public class CSVUploadController {

	@Autowired
	SchoolService schoolService;

	@Autowired
	GroupService groupService;

	@Autowired
	StudentService studentService;

	@Autowired
	StudentInfoService studentInfoService;

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
		reader.close();
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
		List<Student> students = new ArrayList<>();
		List<Group> groups = new ArrayList<>();
		List<StudentInfo> studentInfos = new ArrayList<>();
		Map<String, List<Integer>> groupMap= new LinkedHashMap<>();


		int curInd = 0;
		reader.readNext();
		while((line = reader.readNext()) != null) {
			Arrays.toString(line);
			System.out.println(line[0] + " " + line[1]);
			Student student = new Student();
			student.setFistname(line[0]);
			student.setLastname(line[1]);
			student.setEmail(line[2]);
			int rand1 = (int) ((Math.random() * 1000000) + 1);
			int rand2 = (int) ((Math.random() * 1000000) + 1);
			String pass = "rand_"+String.valueOf(rand1)+String.valueOf(rand1)+"_pass";
			student.setPassword(pass);

			Group group = new Group();
			group.setName(line[3]);
			int schoolId = (int) ((Math.random() * 3) + 1);
			group.setSchoolId(schoolId);

			StudentInfo info = new StudentInfo();
			info.setPassportId(line[4]);
			info.setSocialCardId(line[5]);

			DateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
			Date date;
			try {
				date = formatter.parse(line[6]);
				long birthDate = date.getTime();
				info.setBirthDate(birthDate);
			} catch (ParseException e) {
				System.out.println("Can't parse this: "+line[6]);
				e.printStackTrace();
			}
//			long birthDate = date.getTime();
//			info.setBirthDate(birthDate);
			info.setImageUrl(line[7]);

			if(groupMap.containsKey(group.getName())) {
				groupMap.get(group.getName()).add(curInd);
			} else {
				List<Integer> studentIndList = new ArrayList<Integer>();
				studentIndList.add(curInd);
				groupMap.put(group.getName(), studentIndList);
				groups.add(group);
			}
			students.add(student);
			studentInfos.add(info);
			curInd++;
		}
		List<Integer> groupIds = groupService.addGroups(groups);
//		for (int i = 0; i < students.size(); i++) {
//			students.get(i).setGroupId(groupIds.get(i));
//		}
		Set<Entry<String, List<Integer>>> entrySet =  groupMap.entrySet();
		Iterator<Entry<String,List<Integer>>> mapIterator = entrySet.iterator();
		int groupIndex = 0;
		while(mapIterator.hasNext()) {
			Map.Entry<String, List<Integer>> curEntry = mapIterator.next();
			List<Integer> studentIndices = curEntry.getValue();
			for(int i = 0; i < studentIndices.size(); i++) {
				int studentIndex = studentIndices.get(i);
				//todo
//				students.get(studentIndex).setGroupId(groupIds.get(groupIndex));
			}
			groupIndex++;
		}
		int[] studentsIds = studentService.addStudents(students);
		System.out.println(Arrays.toString(studentsIds));

		for (int i = 0; i < studentInfos.size(); i++) {
			studentInfos.get(i).setStudentId(studentsIds[i]);
		}
		int[] studentInfoIds = studentInfoService.addStudentInfos(studentInfos);
		System.out.println(Arrays.toString(studentInfoIds));
		reader.close();
		return "csvupload";
	}
}
