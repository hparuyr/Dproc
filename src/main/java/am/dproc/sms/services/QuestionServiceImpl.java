package am.dproc.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.QuestionDAO;
import am.dproc.sms.modules.AnswerBean;
import am.dproc.sms.modules.QuestionBean;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionDAO questionDAO;
	
	@Autowired
	AnswerService answerService;

	@Override
	public QuestionBean getQuestion(Integer id) {
		QuestionBean question =  questionDAO.getQuestion(id);
		question.setAnswers(answerService.getAnswersForQuestion(id));
		return question;
	}

	@Override
	public List<QuestionBean> getAllQuestions() {
		List<QuestionBean> questions =  questionDAO.getAllQuestions();
		questions.forEach(item -> item.setAnswers(answerService.getAnswersForQuestion(item.getId())));
		return questions;
	}
	
	@Override
	public List<QuestionBean> getQuestionsForTest(Integer testId) {
		List<QuestionBean> questions =  questionDAO.getQuestionsForTest(testId);
		questions.forEach(item -> item.setAnswers(answerService.getAnswersForQuestion(item.getId())));
		return questions;		
	}

	@Override
	public Integer createQuestion(QuestionBean question) {
		return questionDAO.createQuestion(question);
	}

	@Override
	public Integer updateQuestion(QuestionBean question) {
		return questionDAO.updateQuestion(question);
	}

	@Override
	public Integer deleteQuestion(Integer id) {
		return questionDAO.deleteQuestion(id);
	}

}
