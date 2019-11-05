package am.dproc.sms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.TestResultDAO;
import am.dproc.sms.services.interfaces.TestResultService;

@Service
public class TestResultServiceImpl implements TestResultService {

    @Autowired
    TestResultDAO testResultDAO;

    @Override
    public Integer addTestResult(Integer testId, Integer studentId, Double score) {
        if (testId == null || studentId == null || score == null) {
            return -1;
        }
        return testResultDAO.addTestResult(testId, studentId, score);
    }

    @Override
    public Double getLastTestResultForStudent(Integer testId, Integer studentId) {
        return testResultDAO.getLastTestResultForStudent(testId, studentId);
    }

    @Override
    public Double getTestResultById(Integer id) {
        return testResultDAO.getTestResultById(id);
    }

    @Override
    public Double getAverageTestResultForStudent(Integer studentId) {
        return testResultDAO.getAverageTestResultForStudent(studentId);
    }

    @Override
    public Double getAverageTestResultForStudentCourse(Integer studentId, Integer courseId) {
        return testResultDAO.getAverageTestResultForStudentCourse(studentId, courseId);
    }

    @Override
    public Integer updateTestResultForStudent(Integer testId, Integer studentId, Double score) {
        return testResultDAO.updateTestResultForStudent(testId, studentId, score);
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
    public Integer deleteTestResultForStudent(Integer testId, Integer studentId) {
        return testResultDAO.deleteTestResultForStudent(testId, studentId);
    }

}
