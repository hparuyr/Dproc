package am.dproc.sms.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.Assessment;
import am.dproc.sms.services.impl.AssessmentServiceImpl;

@RestController
@RequestMapping(path = "/assessment")
public class AssessmentController {

	@Autowired
	AssessmentServiceImpl asService;

	@GetMapping(value = "/{id}")
	public Assessment getAssessment(Integer id) {
		// TODO Auto-generated method stub
		return asService.getAssessment(id);
	}

	@GetMapping(value = "/{title}")
	public Assessment getAssessmentByTitle(String title) {
		// TODO Auto-generated method stub
		return asService.getAssessmentByTitle(title);
	}

	@GetMapping(value = "/")
	public List<Assessment> getAllAssessments() {
		// TODO Auto-generated method stub
		return asService.getAllAssessments();
	}

	@GetMapping(value = "/{userId}")
	public List<Assessment> getAllAssessmentsByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return asService.getAllAssessmentsByUserId(userId);
	}

	@GetMapping(value = "/{assignmentId}")
	public List<Assessment> getAssessmentsByAssignmentId(Integer assignmentId) {
		// TODO Auto-generated method stub
		return asService.getAssessmentsByAssignmentId(assignmentId);
	}

	@DeleteMapping(value = "/{id}")
	public Integer deleteAssessment(Integer id) {
		// TODO Auto-generated method stub
		return asService.deleteAssessment(id);
	}

	@DeleteMapping(value = "/")
	public Integer deleteAllAssessments() {
		// TODO Auto-generated method stub
		return asService.deleteAllAssessments();
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer addAssessment(Assessment asses) {
		// TODO Auto-generated method stub
		return asService.addAssessment(asses);
	}

}
