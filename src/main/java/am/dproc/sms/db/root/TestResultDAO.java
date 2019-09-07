package am.dproc.sms.db.root;

public interface TestResultDAO {

	Integer createTestResult(Integer testId, Integer userId, Double score);

	Double getLastTestResultForUser(Integer testId, Integer userId);

	Double getTestResultById(Integer id);

	Double getAverageTestResultForUser(Integer userId);

	Integer updateTestResultForUser(Integer testId, Integer userId, Double score);

	Integer updateTestResultById(Integer id, Double score);

	Integer deleteTestResultById(Integer id);

	Integer deleteTestResultForUser(Integer testId, Integer userId);

}
