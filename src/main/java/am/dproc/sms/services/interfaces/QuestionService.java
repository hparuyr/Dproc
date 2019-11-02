package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Question;

public interface QuestionService {

    Integer addQuestion(Question question);

    Question getQuestion(Integer id);

    List<Question> getQuestionsForTest(Integer testId);

    List<Question> getAllQuestions();

    Integer updateQuestion(Question question);

    Integer deleteQuestion(Integer id);

}
