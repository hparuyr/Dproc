package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.SurveyResult;

public interface SurveyResultDAO {
	
	public int create(SurveyResult surveyResult);

	public SurveyResult getByStudentId(int studentId);

	public List<SurveyResult> getAll();

	public int update(SurveyResult surveyResult);

	public int delete(int studentId);

	public int deleteAll();
	
}
