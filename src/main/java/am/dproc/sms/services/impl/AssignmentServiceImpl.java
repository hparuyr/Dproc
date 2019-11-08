package am.dproc.sms.services.impl;

import java.util.List;

import am.dproc.sms.db.interfaces.AssignmentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.models.Assignment;
import am.dproc.sms.services.interfaces.AssignmentService;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    AssignmentDAO assignmentDAO;

    @Override
    public Integer addAssignment(Assignment assignment) {
        if (assignment.getLessonId() == null || assignment.getTeacherId() == null ||
                assignment.getEndDate() == null || assignment.getStartDate() == null ||
                assignment.getTitle() == null || assignment.getDescription() == null) {
            return -1;
        }
        return assignmentDAO.addAssignment(assignment);
    }

    @Override
    public Assignment getAssignment(Integer id) {
        return assignmentDAO.getAssignment(id);
    }

    @Override
    public List<Assignment> getAllAssignments() {
        return assignmentDAO.getAllAssignments();
    }

    @Override
    public List<Assignment> getAssignmentsByTeacherId(Integer teacherId) {
        return assignmentDAO.getAssignmentsByTeacherId(teacherId);
    }

    @Override
    public Assignment getAssignmentByLessonIdAndTeacherId(Integer lessonId, Integer teacherID) {
        return assignmentDAO.getAssignmentByLessonIdAndTeacherId(lessonId, teacherID);
    }

    @Override
    public Integer deleteAssignment(Integer id) {
        return assignmentDAO.deleteAssignment(id);
    }

    @Override
    public Integer deleteAllAssignments() {
        return assignmentDAO.deleteAllAssignments();
    }

	/*@Override
	public String getAssignmentComment(Integer assignmentID) {
		return assignmentDAO.getAssignmentComment(assignmentID);
	}*/

	/*@Override
	public String getAssignmentFeedback(Integer id) {
		return assignmentDAO.getAssignmentFeedback(id);
	}

	@Override
	public Integer addAssignmentFeedback(Integer teacherId, Integer assignmentId, String studentCommentDAO) {
		return assignmentDAO.addAssignmentFeedback(teacherId, assignmentId, studentCommentDAO);
	}

	@Override
	public Integer updateAssignmentFeedback(Integer id, String studentCommentDAO) {
		return assignmentDAO.updateAssignmentFeedback(id, studentCommentDAO);
	}

	@Override
	public Integer deleteAssignmentFeedback(Integer id) {
		return assignmentDAO.deleteAssignmentFeedback(id);
	}*/

}
