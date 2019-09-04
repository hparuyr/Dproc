package am.dproc.sms.db.root;

import java.util.List;

import am.dproc.sms.models.AnswerBean;

public interface AnswerDAO {	

	Integer createAnswer(AnswerBean answer);

	AnswerBean getAnswer(Integer id);
	
	List<AnswerBean> getAllAnswers();
	
	Integer updateAnswer(AnswerBean answer);
	
	Integer deleteAnswer(Integer id);

	List<AnswerBean> getAnswersForQuestion(Integer questionId);	

}
