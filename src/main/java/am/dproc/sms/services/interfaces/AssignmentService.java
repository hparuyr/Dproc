package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Assignment;

public interface AssignmentService {

	public Assignment getAssignment(Integer id);

	public List<Assignment> getAllAssignments();

	public List<Assignment> getAllAssignments(String title);

	public List<Assignment> getAssignmentsByTeacherId(Integer teacherId);
	
	public Assignment getAssignmentByLessonID(Integer lessonid, Integer teacherID);

	public Integer deleteAssignment(Integer id);

	public String getAssignmentComment(Integer assignmentID);
	
	public Integer deleteAllAssignments();

	public Integer addAssignment(Assignment asi);

	String getAssignmentFeedback(Integer id);

	Integer addAssignmentFeedback(Integer teacherId, Integer assignmentId, String comment);

	Integer updateAssignmentFeedback(Integer id, String comment);

	Integer deleteAssignmentFeedback(Integer id);
}
