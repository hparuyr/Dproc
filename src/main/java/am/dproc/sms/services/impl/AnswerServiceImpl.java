package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.root.AnswerDAO;
import am.dproc.sms.models.AnswerBean;
import am.dproc.sms.services.root.AnswerService;
@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	AnswerDAO answerDAO;

	@Override
	public AnswerBean getAnswer(Integer id) {
		return answerDAO.getAnswer(id);
	}

	@Override
	public List<AnswerBean> getAllAnswers() {
		return answerDAO.getAllAnswers();
	}
	
	@Override
	public List<AnswerBean> getAnswersForQuestion(Integer questionId) {
		return answerDAO.getAnswersForQuestion(questionId);
	}

	@Override
	public int createAnswer(AnswerBean answer) {
		return answerDAO.createAnswer(answer);
	}

	@Override
	public int updateAnswer(AnswerBean answer) {
		return answerDAO.updateAnswer(answer);
	}

	@Override
	public int deleteAnswer(int id) {
		return answerDAO.deleteAnswer(id);
	}

}
