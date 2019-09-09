package am.dproc.sms.services.root;

import java.util.List;

import am.dproc.sms.models.Test;

public interface TestService {
	Test getTest(Integer id);

	List<Test> getAllTests();

	Integer createTest(Test test);

	Integer updateTest(Test test);

	Integer deleteTest(Integer id);
}