package am.dproc.sms.db;

import java.util.List;

import am.dproc.sms.modules.TestBean;

public interface TestDAO {	

	Integer createTest(TestBean test);

	TestBean getTest(Integer id);
	
	List<TestBean> getAllTests();
	
	Integer updateTest(TestBean test);
	
	Integer deleteTest(Integer id);

}
