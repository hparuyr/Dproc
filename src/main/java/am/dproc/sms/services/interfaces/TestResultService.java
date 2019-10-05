package am.dproc.sms.services.interfaces;

public interface TestResultService {

	Integer createTestResult(Integer testId, Integer studentId, Double score);

	Double getLastTestResultForStudent(Integer testId, Integer studentId);

	Double getTestResultById(Integer id);

	Double getAverageTestResultForStudent(Integer studentId);

	Integer updateTestResultForStudent(Integer testId, Integer studentId, Double score);

	Integer updateTestResultById(Integer id, Double score);

	Integer deleteTestResultById(Integer id);

	Integer deleteTestResultForStudent(Integer testId, Integer studentId);

	Double getAverageTestResultForStudentCourse(Integer studentId, Integer courseId);

}
