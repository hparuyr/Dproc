package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Answer;

public interface AnswerService {

	Answer getAnswer(Integer id);

	List<Answer> getAllAnswers();

	int createAnswer(Answer answer);

	int updateAnswer(Answer answer);

	int deleteAnswer(int id);

	List<Answer> getAnswersForQuestion(Integer questionId);

}
