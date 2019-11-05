package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Answer;

public interface AnswerService {

	Integer addAnswer(Answer answer);

	Answer getAnswer(Integer id);

	List<Answer> getAnswersForQuestion(Integer questionId);

	List<Answer> getAllAnswers();

	Integer updateAnswer(Answer answer);

	Integer deleteAnswer(Integer id);

}
