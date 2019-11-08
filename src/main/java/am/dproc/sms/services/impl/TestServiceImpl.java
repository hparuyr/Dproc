package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.TestDAO;
import am.dproc.sms.models.Test;
import am.dproc.sms.services.interfaces.QuestionService;
import am.dproc.sms.services.interfaces.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	TestDAO testDAO;
	
	@Autowired
	QuestionService questionService;

	@Override
	public Integer addTest(Test test) {
		if (test.getLessonId() == null || test.getTitle()  == null) {
			return -1;
		}

		Integer testId = testDAO.addTest(test);

		if (test.getQuestions() != null){
			for (int i = 0; i < test.getQuestions().size(); i++) {
				test.getQuestions().get(i).setTestId(testId);
				questionService.addQuestion(test.getQuestions().get(i));
			}
		}

		return testId;
	}

	@Override
	public Test getTest(Integer id) {
		Test test = testDAO.getTest(id);
		test.setQuestions(questionService.getQuestionsForTest(id));
		return test;
	}

	@Override
	public List<Test> getAllTests() {
		List<Test> tests = testDAO.getAllTests();
		tests.forEach(item -> item.setQuestions(questionService.getQuestionsForTest(item.getId())));
		return tests;
	}

	@Override
	public Integer updateTest(Test test) {
		boolean bool = false;

		if (test.getTitle() != null) {
			if (testDAO.updateTest(test.getId(), test.getTitle()) == 0) {
				return -1;
			}
			bool = true;
		}

		return bool ? 1 : 0;
	}

	@Override
	public Integer deleteTest(Integer id) {
		if (getTest(id).getQuestions().size() == 0) {
			return testDAO.deleteTest(id);
		}
		return 0;
	}
}
