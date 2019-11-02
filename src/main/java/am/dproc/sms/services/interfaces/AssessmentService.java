package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Assessment;

public interface AssessmentService {

    Integer addAssessment(Assessment assessment);

    Assessment getAssessment(Integer id);

    List<Assessment> getAllAssessmentsByUserId(Integer userID);

    List<Assessment> getAssessmentsByAssignmentId(Integer assignmentID);

    Integer getAssessmentByStudentIDAndAssignmentID(Integer studentID, Integer assignmentID);

    Assessment getAssessmentObjByStudentIDAndAssignmentID(Integer studentID, Integer assignmentID);

    Double getAverageScoreByStudentCourse(Integer studentID, Integer courseID);

    Double getAverageScoreByStudent(Integer studentID);

    List<Assessment> getAllAssessments();

    Integer deleteAssessment(Integer id);

    Integer deleteAllAssessments();

}
