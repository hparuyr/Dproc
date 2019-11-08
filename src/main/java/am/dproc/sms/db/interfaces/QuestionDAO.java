package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Question;

public interface QuestionDAO {	

	Integer addQuestion(Question question);
	
	Question getQuestion(Integer id);

	List<Question> getAllQuestions();
	
	Integer updateQuestionContent(Integer id, String content);
	
	Integer deleteQuestion(Integer id);

	List<Question> getQuestionsForTest(Integer testId);	

}

