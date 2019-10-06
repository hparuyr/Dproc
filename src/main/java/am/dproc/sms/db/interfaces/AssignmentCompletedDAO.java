package am.dproc.sms.db.interfaces;

import java.util.List;
import am.dproc.sms.models.AssignmentCompleted;
import am.dproc.sms.models.Student;

public interface AssignmentCompletedDAO {

	public AssignmentCompleted getAssignmentCompleted(Integer assignmentCompletedId);

	public List<AssignmentCompleted> getAllAssignmentsCompleted();

	public List<AssignmentCompleted> getAssignmentsCompletedByAssignmentId(Integer assignmentId);

	public Integer updateAssignmentCompletedContentURL(Integer assignmentCompletedId, String contentURL);

	List<Student> getStudentsDoneAssignmentWithId(Integer assignmentId);

	List<Student> getStudentsNotDoneAssignmentWithId(Integer assignmentId);

}
