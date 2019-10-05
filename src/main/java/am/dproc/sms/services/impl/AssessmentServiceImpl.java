package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.impl.AssessmentDAODBImpl;
import am.dproc.sms.models.Assessment;
import am.dproc.sms.services.interfaces.AssessmentService;

@Service
public class AssessmentServiceImpl implements AssessmentService {

	@Autowired
	AssessmentDAODBImpl asDao;

	@Override
	public Assessment getAssessment(Integer id) {
		return asDao.getAssessment(id);
	}

	@Override
	public Assessment getAssessmentByTitle(String title) {
		return asDao.getAssessmentByTitle(title);
	}

	@Override
	public List<Assessment> getAllAssessments() {
		return asDao.getAllAssessments();
	}

	@Override
	public List<Assessment> getAllAssessmentsByUserId(Integer userId) {
		return asDao.getAllAssessmentsByUserId(userId);
	}

	@Override
	public List<Assessment> getAssessmentsByAssignmentId(Integer assignmentId) {
		return asDao.getAssessmentsByAssignmentId(assignmentId);
	}
	
	@Override
	public Integer getAssessmentByStudentIDAndAssignmentID(Integer studentId, Integer assignmentId) {
		return asDao.getAssessmentByStudentIDAndAssignmentID(studentId, assignmentId);
	}

	@Override
	public Double getAverageScoreByStudentCourse(Integer studentId, Integer courseId) {
		return asDao.getAverageScoreByStudentCourse(studentId, courseId);
	}
	@Override
	public Integer deleteAssessment(Integer id) {
		return asDao.deleteAssessment(id);
	}

	@Override
	public Integer deleteAllAssessments() {
		return asDao.deleteAllAssessments();
	}

	@Override
	public Integer addAssessment(Assessment asses) {
		return asDao.addAssessment(asses);
	}

	@Override
	public Double getAverageScoreByStudent(Integer studentId) {
		return asDao.getAverageScoreByStudent(studentId);
	}

}
