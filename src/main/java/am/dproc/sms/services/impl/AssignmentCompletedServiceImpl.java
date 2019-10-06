package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.AssignmentCompletedDAO;
import am.dproc.sms.models.AssignmentCompleted;
import am.dproc.sms.models.Student;
import am.dproc.sms.services.interfaces.AssignmentCompletedService;

@Service
public class AssignmentCompletedServiceImpl implements AssignmentCompletedService {

	@Autowired
	private AssignmentCompletedDAO assignmentCompletedDAO;

	@Override
	public AssignmentCompleted getAssignmentCompleted(Integer assignmentCompletedId) {
		return assignmentCompletedDAO.getAssignmentCompleted(assignmentCompletedId);
	}

	@Override
	public List<AssignmentCompleted> getAllAssignmentsCompleted() {
		return assignmentCompletedDAO.getAllAssignmentsCompleted();
	}

	@Override
	public List<AssignmentCompleted> getAssignmentsCompletedByAssignmentId(Integer assignmentId) {
		return assignmentCompletedDAO.getAssignmentsCompletedByAssignmentId(assignmentId);
	}

	@Override
	public List<Student> getStudentsDoneAssignmentWithId(Integer assignmentId) {
		return assignmentCompletedDAO.getStudentsDoneAssignmentWithId(assignmentId);
	}

	@Override
	public List<Student> getStudentsNotDoneAssignmentWithId(Integer assignmentId) {
		return assignmentCompletedDAO.getStudentsNotDoneAssignmentWithId(assignmentId);
	}

	@Override
	public Integer updateAssignmentCompleted(AssignmentCompleted assignmentCompleted) {
		boolean bool = false;

		if (assignmentCompleted.getContentURL() != null) {
			if (assignmentCompletedDAO.updateAssignmentCompletedContentURL(assignmentCompleted.getId(), assignmentCompleted.getContentURL()) == 0) {
				return -1;
			}
			bool = true;
		}
		return bool == true ? 1 : 0;
	}
}
