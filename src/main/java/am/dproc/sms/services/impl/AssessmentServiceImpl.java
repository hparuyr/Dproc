package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.impl.AssessmentDAODBImpl;
import am.dproc.sms.models.Assessment;
import am.dproc.sms.services.root.AssessmentService;

@Service
public class AssessmentServiceImpl implements AssessmentService {
	
	@Autowired
	AssessmentDAODBImpl asDao;

	@Override
	public Assessment getAssessment(Integer id) {
		// TODO Auto-generated method stub
		return asDao.getAssessment(id);
	}

	@Override
	public Assessment getAssessmentByTitle(String title) {
		// TODO Auto-generated method stub
		return asDao.getAssessmentByTitle(title);
	}

	@Override
	public List<Assessment> getAllAssessments() {
		// TODO Auto-generated method stub
		return asDao.getAllAssessments();
	}

	@Override
	public List<Assessment> getAllAssessmentsByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return asDao.getAllAssessmentsByUserId(userId);
	}

	@Override
	public List<Assessment> getAssessmentsByAssignmentId(Integer assignmentId) {
		// TODO Auto-generated method stub
		return asDao.getAssessmentsByAssignmentId(assignmentId);
	}

	@Override
	public Integer deleteAssessment(Integer id) {
		// TODO Auto-generated method stub
		return asDao.deleteAssessment(id);
	}

	@Override
	public Integer deleteAllAssessments() {
		// TODO Auto-generated method stub
		return asDao.deleteAllAssessments();
	}

	@Override
	public Integer addAssessment(Assessment asses) {
		// TODO Auto-generated method stub
		return asDao.addAssessment(asses);
	}

}
