package am.dproc.sms.services.root;

import java.util.List;

import am.dproc.sms.models.Question;

public interface QuestionService {
	Question getQuestion(Integer id);

	List<Question> getAllQuestions();

	Integer createQuestion(Question question);

	Integer updateQuestion(Question question);

	Integer deleteQuestion(Integer id);

	List<Question> getQuestionsForTest(Integer testId);

}
