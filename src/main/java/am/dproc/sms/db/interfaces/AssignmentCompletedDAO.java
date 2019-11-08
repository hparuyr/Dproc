package am.dproc.sms.db.interfaces;

import java.util.List;
import am.dproc.sms.models.AssignmentCompleted;
import am.dproc.sms.models.Student;

public interface AssignmentCompletedDAO {

	AssignmentCompleted getAssignmentCompleted(Integer assignmentCompletedId);

	List<AssignmentCompleted> getAllAssignmentsCompleted();

	List<AssignmentCompleted> getAssignmentsCompletedByAssignmentId(Integer assignmentId);

	Integer updateAssignmentCompletedContentURL(Integer assignmentCompletedId, String contentURL);

	List<Student> getStudentsDoneAssignmentWithId(Integer assignmentId);

	List<Student> getStudentsNotDoneAssignmentWithId(Integer assignmentId);

}
