package am.dproc.sms.db;

import java.util.List;

import am.dproc.sms.modules.AnswerBean;

public interface AnswerDAO {	

	Integer createAnswer(AnswerBean answer);

	AnswerBean getAnswer(Integer id);
	
	List<AnswerBean> getAllAnswers();
	
	Integer updateAnswer(AnswerBean answer);
	
	Integer deleteAnswer(Integer id);

	List<AnswerBean> getAnswersForQuestion(Integer questionId);	

}
