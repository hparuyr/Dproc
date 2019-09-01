package am.dproc.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.TestDAO;
import am.dproc.sms.modules.TestBean;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	TestDAO testDAO;
	
	@Autowired
	QuestionService questionService;

	@Override
	public TestBean getTest(Integer id) {
		TestBean test = testDAO.getTest(id);
		test.setQuestions(questionService.getQuestionsForTest(id));
		return test;
	}

	@Override
	public List<TestBean> getAllTests() {
		List<TestBean> tests = testDAO.getAllTests();
		tests.forEach(item -> item.setQuestions(questionService.getQuestionsForTest(item.getId())));
		return tests;
	}

	@Override
	public Integer createTest(TestBean test) {
		return testDAO.createTest(test);
	}

	@Override
	public Integer updateTest(TestBean test) {
		return testDAO.updateTest(test);
	}

	@Override
	public Integer deleteTest(Integer id) {
		return testDAO.deleteTest(id);
	}
}
