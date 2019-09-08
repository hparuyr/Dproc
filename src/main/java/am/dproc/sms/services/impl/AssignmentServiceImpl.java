package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.impl.AssignmentDAODBImpl;
import am.dproc.sms.models.Assignment;
import am.dproc.sms.services.root.AssignmentService;

@Service
public class AssignmentServiceImpl implements AssignmentService {

	@Autowired
	AssignmentDAODBImpl asiDao;

	@Override
	public Assignment getAssignment(Integer id) {
		// TODO Auto-generated method stub
		return asiDao.getAssignment(id);
	}

	@Override
	public List<Assignment> getAllAssignments() {
		// TODO Auto-generated method stub
		return asiDao.getAllAssignments();
	}

	@Override
	public List<Assignment> getAllAssignments(String title) {
		// TODO Auto-generated method stub
		return asiDao.getAllAssignments(title);
	}

	@Override
	public List<Assignment> getAssignmentsByTeacherId(Integer teacherId) {
		// TODO Auto-generated method stub
		return asiDao.getAssignmentsByTeacherId(teacherId);
	}

	@Override
	public Integer deleteAssignment(Integer id) {
		// TODO Auto-generated method stub
		return asiDao.deleteAssignment(id);
	}

	@Override
	public Integer deleteAllAssignments() {
		// TODO Auto-generated method stub
		return asiDao.deleteAllAssignments();
	}

	@Override
	public Integer addAssignment(Assignment asi) {
		// TODO Auto-generated method stub
		return asiDao.addAssignment(asi);
	}

	@Override
	public String getAssignmentFeedback(Integer id) {
		return asiDao.getAssignmentFeedback(id);
	}

	@Override
	public Integer addAssignmentFeedback(Integer teacherId, Integer assignmentId, String comment) {
		return asiDao.addAssignmentFeedback(teacherId, assignmentId, comment);
	}
	
	@Override
	public Integer updateAssignmentFeedback(Integer id, String comment) {
		return asiDao.updateAssignmentFeedback(id, comment);
	}

	@Override
	public Integer deleteAssignmentFeedback(Integer id) {
		return asiDao.deleteAssignmentFeedback(id);
	}

}
