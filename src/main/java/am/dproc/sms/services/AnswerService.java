package am.dproc.sms.services;

import java.util.List;

import am.dproc.sms.modules.AnswerBean;

public interface AnswerService {

	AnswerBean getAnswer(Integer id);

	List<AnswerBean> getAllAnswers();

	int createAnswer(AnswerBean answer);

	int updateAnswer(AnswerBean answer);

	int deleteAnswer(int id);

	List<AnswerBean> getAnswersForQuestion(Integer questionId);

}
