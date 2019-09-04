package am.dproc.sms.db.root;

import java.util.List;

import am.dproc.sms.models.TestBean;

public interface TestDAO {	

	Integer createTest(TestBean test);

	TestBean getTest(Integer id);
	
	List<TestBean> getAllTests();
	
	Integer updateTest(TestBean test);
	
	Integer deleteTest(Integer id);

}
