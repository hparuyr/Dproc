package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.AnswerDAO;
import am.dproc.sms.models.Answer;
import am.dproc.sms.services.interfaces.AnswerService;
@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	AnswerDAO answerDAO;

	@Override
	public Answer getAnswer(Integer id) {
		return answerDAO.getAnswer(id);
	}

	@Override
	public List<Answer> getAllAnswers() {
		return answerDAO.getAllAnswers();
	}
	
	@Override
	public List<Answer> getAnswersForQuestion(Integer questionId) {
		return answerDAO.getAnswersForQuestion(questionId);
	}

	@Override
	public int createAnswer(Answer answer) {
		return answerDAO.createAnswer(answer);
	}

	@Override
	public int updateAnswer(Answer answer) {
		return answerDAO.updateAnswer(answer);
	}

	@Override
	public int deleteAnswer(int id) {
		return answerDAO.deleteAnswer(id);
	}

}
