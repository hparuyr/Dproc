package am.dproc.sms.services.root;

import java.util.List;

import am.dproc.sms.models.Assignment;

public interface AssignmentService {

	public Assignment getAssignment(Integer id);

	public List<Assignment> getAllAssignments();

	public List<Assignment> getAllAssignments(String title);

	public List<Assignment> getAssignmentsByTeacherId(Integer teacherId);

	public Integer deleteAssignment(Integer id);

	public Integer deleteAllAssignments();

	public Integer addAssignment(Assignment asi);
}