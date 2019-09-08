package am.dproc.sms.services.root;

public interface TestResultService {

	Integer createTestResult(Integer testId, Integer studentId, Double score);

	Double getLastTestResultForUser(Integer testId, Integer studentId);

	Double getTestResultById(Integer id);

	Double getAverageTestResultForUser(Integer studentId);

	Integer updateTestResultForUser(Integer testId, Integer studentId, Double score);

	Integer updateTestResultById(Integer id, Double score);

	Integer deleteTestResultById(Integer id);

	Integer deleteTestResultForUser(Integer testId, Integer studentId);

}
