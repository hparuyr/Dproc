package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Test;

public interface TestDAO {	

	Integer createTest(Test test);

	Test getTest(Integer id);
	
	List<Test> getAllTests();
	
	Integer updateTest(Test test);
	
	Integer deleteTest(Integer id);

}
