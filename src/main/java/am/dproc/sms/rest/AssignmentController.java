package am.dproc.sms.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.Assignment;
import am.dproc.sms.services.impl.AssignmentServiceImpl;

@RestController
@RequestMapping(path = "/assignment")

public class AssignmentController {
	
	@Autowired
	AssignmentServiceImpl asiService;
	
	@GetMapping(value = "/{id}")
	public Assignment getAssignment(Integer id) {
		// TODO Auto-generated method stub
		return asiService.getAssignment(id);
	}

	@GetMapping(value = "/")
	public List<Assignment> getAllAssignments() {
		// TODO Auto-generated method stub
		return asiService.getAllAssignments();
	}

	@GetMapping(value = "/{title}")
	public List<Assignment> getAllAssignments(String title) {
		// TODO Auto-generated method stub
		return asiService.getAllAssignments(title);
	}

	@GetMapping(value = "/{teacherId}")
	public List<Assignment> getAssignmentsByTeacherId(Integer teacherId) {
		// TODO Auto-generated method stub
		return asiService.getAssignmentsByTeacherId(teacherId);
	}

	@DeleteMapping(value = "/{id}")
	public Integer deleteAssignment(Integer id) {
		// TODO Auto-generated method stub
		return asiService.deleteAssignment(id);
	}

	@DeleteMapping(value = "/")
	public Integer deleteAllAssignments() {
		// TODO Auto-generated method stub
		return asiService.deleteAllAssignments();
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer addAssignment(Assignment asi) {
		// TODO Auto-generated method stub
		return asiService.addAssignment(asi);
	}


}
