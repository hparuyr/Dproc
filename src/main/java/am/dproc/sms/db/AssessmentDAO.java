package am.dproc.sms.db;

import java.util.List;

import am.dproc.sms.modules.Assessment;





public interface AssessmentDAO {
	
	public Assessment getAssessment(Integer id);
	
	public Assessment getAssessmentByTitle(String title);

	public List<Assessment> getAllAssessments();

	public List<Assessment> getAllAssessmentsByUserId(Integer userId);

	public List<Assessment> getAssessmentsByAssignmentId(Integer assignmentId);

	public Integer deleteAssessment(Integer id);

	public Integer deleteAllAssessments();

	public Integer addAssessment(Assessment asi);

}
