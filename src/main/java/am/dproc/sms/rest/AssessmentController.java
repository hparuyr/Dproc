package am.dproc.sms.rest;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.Assessment;
import am.dproc.sms.services.interfaces.AssessmentService;

@RestController
@RequestMapping(path = "/assessment")
public class AssessmentController {

	@Autowired
	AssessmentService asService;

	@GetMapping(value = "/{id}")
	public Assessment getAssessment(Integer id) {
		return asService.getAssessment(id);
	}

	@GetMapping(value = "/{title}")
	public Assessment getAssessmentByTitle(String title) {
		return asService.getAssessmentByTitle(title);
	}

	@GetMapping(value = "/")
	public List<Assessment> getAllAssessments() {
		return asService.getAllAssessments();
	}

	@GetMapping(value = "/{userId}")
	public List<Assessment> getAllAssessmentsByUserId(Integer userId) {
		return asService.getAllAssessmentsByUserId(userId);
	}

	@GetMapping(value = "/{assignmentId}")
	public List<Assessment> getAssessmentsByAssignmentId(Integer assignmentId) {
		return asService.getAssessmentsByAssignmentId(assignmentId);
	}

	@DeleteMapping(value = "/{id}")
	public Integer deleteAssessment(Integer id) {
		return asService.deleteAssessment(id);
	}

	@DeleteMapping(value = "/")
	public Integer deleteAllAssessments() {
		return asService.deleteAllAssessments();
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public Integer addAssessment(Assessment asses) {
		return asService.addAssessment(asses);
	}

}
