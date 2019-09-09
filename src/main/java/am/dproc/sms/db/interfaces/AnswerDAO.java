package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Answer;

public interface AnswerDAO {

	Integer createAnswer(Answer answer);

	Answer getAnswer(Integer id);

	List<Answer> getAllAnswers();

	Integer updateAnswer(Answer answer);

	Integer deleteAnswer(Integer id);

	List<Answer> getAnswersForQuestion(Integer questionId);

}
