package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Question;

public interface QuestionDAO {	

	Integer createQuestion(Question question);
	
	Question getQuestion(Integer id);

	List<Question> getAllQuestions();
	
	Integer updateQuestion(Question question);
	
	Integer deleteQuestion(Integer id);

	List<Question> getQuestionsForTest(Integer testId);	

}

