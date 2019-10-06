package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Assignment;

public interface AssignmentDAO {

	public Assignment getAssignment(Integer id);

	public List<Assignment> getAllAssignments();

	public List<Assignment> getAllAssignments(String title);

	public List<Assignment> getAssignmentsByTeacherId(Integer teacherId);

	public Assignment getAssignmentByLessonIDAndTeacherID(Integer lessonID, Integer teacherID);

	//public String getAssignmentComment(Integer assignmentID);

	public Integer deleteAssignment(Integer id);

	public Integer deleteAllAssignments();

	public Integer addAssignment(Assignment assignment);

	//String getAssignmentFeedback(Integer id);

	//Integer addAssignmentFeedback(Integer teacherId, Integer assignmentId, String comment);

	//Integer updateAssignmentFeedback(Integer id, String comment);

	//Integer deleteAssignmentFeedback(Integer id);

}
