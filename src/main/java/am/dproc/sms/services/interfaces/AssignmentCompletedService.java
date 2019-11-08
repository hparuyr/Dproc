package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.AssignmentCompleted;
import am.dproc.sms.models.Student;

public interface AssignmentCompletedService {

	public AssignmentCompleted getAssignmentCompleted(Integer assignmentCompletedId);

	public List<AssignmentCompleted> getAllAssignmentsCompleted();

	public List<AssignmentCompleted> getAssignmentsCompletedByAssignmentId(Integer assignmentId);

	public List<Student> getStudentsDoneAssignmentWithId(Integer assignmentId);

	public List<Student> getStudentsNotDoneAssignmentWithId(Integer assignmentId);

	public Integer updateAssignmentCompleted(AssignmentCompleted assignmentCompleted);

}
