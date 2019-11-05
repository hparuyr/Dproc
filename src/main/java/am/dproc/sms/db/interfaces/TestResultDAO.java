package am.dproc.sms.db.interfaces;

public interface TestResultDAO {

	Integer addTestResult(Integer testId, Integer studentId, Double score);

	Double getLastTestResultForStudent(Integer testId, Integer studentId);

	Double getTestResultById(Integer id);

	Double getAverageTestResultForStudent(Integer studentId);

	Double getAverageTestResultForStudentCourse(Integer studentId, Integer courseId);

	Integer updateTestResultForStudent(Integer testId, Integer studentId, Double score);

	Integer updateTestResultById(Integer id, Double score);

	Integer deleteTestResultById(Integer id);

	Integer deleteTestResultForStudent(Integer testId, Integer studentId);

}
