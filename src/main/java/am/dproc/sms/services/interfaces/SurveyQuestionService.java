package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.SurveyQuestion;

public interface SurveyQuestionService {

	int create(String content, String inputName);

	int[] createBatch(List<SurveyQuestion> surveyQuestions);

	//	public SurveyQuestion get(int id);

	List<SurveyQuestion> getAll();

}
