package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.SurveyQuestion;

public interface SurveyQuestionDAO {
	
	public int create(String content, String inputName);

	public int[] createBatch(List<SurveyQuestion> surveyQuestions);

//	public SurveyQuestion get(int id);
	public List<SurveyQuestion> getAll();
	
}
