package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.SurveyQuestionDAO;
import am.dproc.sms.models.SurveyQuestion;
import am.dproc.sms.services.interfaces.SurveyQuestionService;

@Service
public class SurveyQuestionServiceImpl implements SurveyQuestionService {

	@Autowired
	SurveyQuestionDAO surveyQuestionDao;

	@Override
	public int create(String content, String inputName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] createBatch(List<SurveyQuestion> surveyQuestions) {
		return surveyQuestionDao.createBatch(surveyQuestions);
	}

	@Override
	public List<SurveyQuestion> getAll() {
		return surveyQuestionDao.getAll();
	}
	

}
