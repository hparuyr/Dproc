package am.dproc.sms.services;

import java.util.List;

import am.dproc.sms.modules.TestBean;

public interface TestService {
	TestBean getTest(Integer id);

	List<TestBean> getAllTests();

	Integer createTest(TestBean test);

	Integer updateTest(TestBean test);

	Integer deleteTest(Integer id);
}