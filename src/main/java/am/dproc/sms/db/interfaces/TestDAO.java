package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Test;

public interface TestDAO {	

	Integer addTest(Test test);

	Test getTest(Integer id);
	
	List<Test> getAllTests();
	
	Integer updateTest(Integer id, String title);
	
	Integer deleteTest(Integer id);

}
