package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Answer;

public interface AnswerDAO {

	Integer addAnswer(Answer answer);

	Answer getAnswer(Integer id);

	List<Answer> getAnswersForQuestion(Integer questionID);

	List<Answer> getAllAnswers();

	Integer updateContent(Integer id, String content);

	Integer updateScore(Integer id, Integer score);

	Integer deleteAnswer(Integer id);

}
