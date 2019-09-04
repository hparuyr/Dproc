package am.dproc.sms.services.root;

import java.util.List;

import am.dproc.sms.models.TestBean;

public interface TestService {
	TestBean getTest(Integer id);

	List<TestBean> getAllTests();

	Integer createTest(TestBean test);

	Integer updateTest(TestBean test);

	Integer deleteTest(Integer id);
}