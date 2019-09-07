package am.dproc.sms.services.root;

public interface TestResultService {

	Integer createTestResult(Integer testId, Integer userId, Double score);

	Double getLastTestResultForUser(Integer testId, Integer userId);

	Double getTestResultById(Integer id);

	Double getAverageTestResultForUser(Integer userId);

	Integer updateTestResultForUser(Integer testId, Integer userId, Double score);

	Integer updateTestResultById(Integer id, Double score);

	Integer deleteTestResultById(Integer id);

	Integer deleteTestResultForUser(Integer testId, Integer userId);

}
