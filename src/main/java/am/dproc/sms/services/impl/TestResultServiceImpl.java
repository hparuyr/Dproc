package am.dproc.sms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.root.TestResultDAO;
import am.dproc.sms.services.root.TestResultService;

@Service
public class TestResultServiceImpl implements TestResultService {
	@Autowired
	TestResultDAO testResultDAO;
	

	@Override
	public Integer createTestResult(Integer testId, Integer studentId, Double score) {
		return testResultDAO.createTestResult(testId, studentId, score);
	}

	@Override
	public Double getLastTestResultForUser(Integer testId, Integer studentId) {
		return testResultDAO.getLastTestResultForUser(testId, studentId);
	}

	@Override
	public Double getTestResultById(Integer id) {
		return testResultDAO.getTestResultById(id);
	}

	@Override
	public Double getAverageTestResultForUser(Integer studentId) {
		return testResultDAO.getAverageTestResultForUser(studentId);
	}

	@Override
	public Integer updateTestResultForUser(Integer testId, Integer studentId, Double score) {
		return testResultDAO.updateTestResultForUser(testId, studentId, score);
	}

	@Override
	public Integer updateTestResultById(Integer id, Double score) {
		return testResultDAO.updateTestResultById(id, score);
	}

	@Override
	public Integer deleteTestResultById(Integer id) {
		return testResultDAO.deleteTestResultById(id);
	}

	@Override
	public Integer deleteTestResultForUser(Integer testId, Integer studentId) {
		return testResultDAO.deleteTestResultForUser(testId, studentId);
	}
}
