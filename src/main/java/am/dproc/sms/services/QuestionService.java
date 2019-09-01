package am.dproc.sms.services;

import java.util.List;

import am.dproc.sms.modules.QuestionBean;

public interface QuestionService {
	QuestionBean getQuestion(Integer id);

	List<QuestionBean> getAllQuestions();

	Integer createQuestion(QuestionBean question);

	Integer updateQuestion(QuestionBean question);

	Integer deleteQuestion(Integer id);

	List<QuestionBean> getQuestionsForTest(Integer testId);

}
