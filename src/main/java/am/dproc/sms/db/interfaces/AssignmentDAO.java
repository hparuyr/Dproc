package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Assignment;

public interface AssignmentDAO {

	Integer addAssignment(Assignment assignment);

	Assignment getAssignment(Integer id);

	List<Assignment> getAllAssignments();

	List<Assignment> getAssignmentsByTeacherId(Integer teacherId);

	Assignment getAssignmentByLessonIdAndTeacherId(Integer lessonId, Integer teacherId);

	Integer deleteAssignment(Integer id);

	Integer deleteAllAssignments();

	//public String getAssignmentComment(Integer assignmentID);

	//String getAssignmentFeedback(Integer id);

	//Integer addAssignmentFeedback(Integer teacherId, Integer assignmentId, String comment);

	//Integer updateAssignmentFeedback(Integer id, String comment);

	//Integer deleteAssignmentFeedback(Integer id);

}
