package am.dproc.sms.db;

import java.util.List;

import am.dproc.sms.modules.QuestionBean;

public interface QuestionDAO {	

	Integer createQuestion(QuestionBean question);
	
	QuestionBean getQuestion(Integer id);

	List<QuestionBean> getAllQuestions();
	
	Integer updateQuestion(QuestionBean question);
	
	Integer deleteQuestion(Integer id);

	List<QuestionBean> getQuestionsForTest(Integer testId);	

}

