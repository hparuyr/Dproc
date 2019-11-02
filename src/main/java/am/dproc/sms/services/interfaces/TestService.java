package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Test;

public interface TestService {

    Integer addTest(Test test);

    Test getTest(Integer id);

    List<Test> getAllTests();

    Integer updateTest(Test test);

    Integer deleteTest(Integer id);
}