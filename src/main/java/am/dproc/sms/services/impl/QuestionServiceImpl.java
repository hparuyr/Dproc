package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.QuestionDAO;
import am.dproc.sms.models.Question;
import am.dproc.sms.services.interfaces.AnswerService;
import am.dproc.sms.services.interfaces.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionDAO questionDAO;

	@Autowired
	AnswerService answerService;

	@Override
	public Question getQuestion(Integer id) {
		Question question = questionDAO.getQuestion(id);
		question.setAnswers(answerService.getAnswersForQuestion(id));
		return question;
	}

	@Override
	public List<Question> getAllQuestions() {
		List<Question> questions = questionDAO.getAllQuestions();
		questions.forEach(item -> item.setAnswers(answerService.getAnswersForQuestion(item.getId())));
		return questions;
	}

	@Override
	public List<Question> getQuestionsForTest(Integer testId) {
		List<Question> questions = questionDAO.getQuestionsForTest(testId);
		questions.forEach(item -> item.setAnswers(answerService.getAnswersForQuestion(item.getId())));
		return questions;
	}

	@Override
	public Integer createQuestion(Question question) {
		return questionDAO.createQuestion(question);
	}

	@Override
	public Integer updateQuestion(Question question) {
		return questionDAO.updateQuestion(question);
	}

	@Override
	public Integer deleteQuestion(Integer id) {
		return questionDAO.deleteQuestion(id);
	}

}
