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
    public Integer addAnswer(Answer answer) {
        if (answer.getQuestionId() == null || answer.getContent() == null || answer.getScore() == null) {
            return -1;
        }
        return answerDAO.addAnswer(answer);
    }

    @Override
    public Answer getAnswer(Integer id) {
        return answerDAO.getAnswer(id);
    }

    @Override
    public List<Answer> getAnswersForQuestion(Integer questionId) {
        return answerDAO.getAnswersForQuestion(questionId);
    }

    @Override
    public List<Answer> getAllAnswers() {
        return answerDAO.getAllAnswers();
    }

    @Override
    public Integer updateAnswer(Answer answer) {

        boolean bool = false;

        if (answer.getContent() != null) {
            if (answerDAO.updateContent(answer.getId(), answer.getContent()) == 0) {
                return -1;
            }
            bool = true;
        }
        if (answer.getScore() != null) {
            if (answerDAO.updateScore(answer.getId(), answer.getScore()) == 0) {
                return -1;
            }
            bool = true;
        }

        System.out.println(bool);
        return bool ? 1 : 0;

    }

    @Override
    public Integer deleteAnswer(Integer id) {
        return answerDAO.deleteAnswer(id);
    }

}
