package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.SurveyResult;

public interface SurveyResultDAO {

	int create(SurveyResult surveyResult);

	SurveyResult getByStudentId(int studentId);

	List<SurveyResult> getAll();

	int update(SurveyResult surveyResult);

	int delete(int studentId);

	int deleteAll();

}
