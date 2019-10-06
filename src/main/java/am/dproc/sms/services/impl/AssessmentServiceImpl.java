package am.dproc.sms.services.impl;

import java.util.List;

import am.dproc.sms.db.interfaces.AssessmentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.models.Assessment;
import am.dproc.sms.services.interfaces.AssessmentService;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    @Autowired
    AssessmentDAO assessmentDAO;

    @Override
    public Integer addAssessment(Assessment assessment) {
        if (assessment.getAssignmentCompletedId() == null || assessment.getComment() == null || assessment.getScore() == null || assessment.getUserId() == null) {
            return -1;
        }
        return assessmentDAO.addAssessment(assessment);
    }

    @Override
    public Assessment getAssessment(Integer id) {
        return assessmentDAO.getAssessment(id);
    }

    @Override
    public Double getAverageScoreByStudent(Integer studentId) {
        return assessmentDAO.getAverageScoreByStudent(studentId);
    }

    @Override
    public List<Assessment> getAllAssessmentsByUserId(Integer userId) {
        return assessmentDAO.getAllAssessmentsByUserId(userId);
    }

    @Override
    public List<Assessment> getAssessmentsByAssignmentId(Integer assignmentId) {
        return assessmentDAO.getAssessmentsByAssignmentId(assignmentId);
    }

    @Override
    public Integer getAssessmentByStudentIDAndAssignmentID(Integer studentId, Integer assignmentId) {
        return assessmentDAO.getAssessmentByStudentIDAndAssignmentID(studentId, assignmentId);
    }

    @Override
    public Double getAverageScoreByStudentCourse(Integer studentId, Integer courseId) {
        return assessmentDAO.getAverageScoreByStudentCourse(studentId, courseId);
    }

    @Override
    public List<Assessment> getAllAssessments() {
        return assessmentDAO.getAllAssessments();
    }

    @Override
    public Integer deleteAssessment(Integer id) {
        return assessmentDAO.deleteAssessment(id);
    }

    @Override
    public Integer deleteAllAssessments() {
        return assessmentDAO.deleteAllAssessments();
    }

}
