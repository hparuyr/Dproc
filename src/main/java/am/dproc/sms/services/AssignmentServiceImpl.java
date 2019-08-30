package am.dproc.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.AssignmentDAODBImpl;
import am.dproc.sms.modules.Assignment;

@Service
public class AssignmentServiceImpl implements AssignmentService{
	
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

}
