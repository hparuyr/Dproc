package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Assessment;

public interface AssessmentService {

	public Assessment getAssessment(Integer id);

	public Assessment getAssessmentByTitle(String title);

	public List<Assessment> getAllAssessments();

	public List<Assessment> getAllAssessmentsByUserId(Integer userId);

	public List<Assessment> getAssessmentsByAssignmentId(Integer assignmentId);
	
	public Integer getAssessmentByStudentIDAndAssignmentID(Integer studentId, Integer assignmentId);

	public Integer deleteAssessment(Integer id);

	public Integer deleteAllAssessments();

	public Integer addAssessment(Assessment asi);

	Double getAverageScoreByStudentCourse(Integer studentId, Integer courseId);

	public Double getAverageScoreByStudent(Integer studentId);
}
