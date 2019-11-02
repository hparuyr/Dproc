package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Assessment;

public interface AssessmentDAO {

	Integer addAssessment(Assessment assessment);

	Assessment getAssessment(Integer id);

	List<Assessment> getAllAssessments();

	List<Assessment> getAllAssessmentsByUserId(Integer userId);

	List<Assessment> getAssessmentsByAssignmentId(Integer assignmentId);
	
	Integer getAssessmentByStudentIDAndAssignmentID(Integer studentId, Integer assignmentId);

	Assessment getAssessmentObjByStudentIDAndAssignmentID(Integer studentID, Integer assignmentID);

	Double getAverageScoreByStudentCourse(Integer studentId, Integer courseId);

	Double getAverageScoreByStudent(Integer studentId);

	Integer deleteAssessment(Integer id);

	Integer deleteAllAssessments();

}
