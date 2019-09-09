package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.root.SurveyResultDAO;
import am.dproc.sms.models.SurveyResult;
import am.dproc.sms.services.root.SurveyResultService;

@Service
public class SurveyResultServiceImpl implements SurveyResultService {

	@Autowired
	SurveyResultDAO surveyResultDao;

	@Override
	public int create(SurveyResult surveyResult) {
		return surveyResultDao.create(surveyResult);
	}

	@Override
	public SurveyResult getByStudentId(int studentId) {
		return surveyResultDao.getByStudentId(studentId);
	}

	@Override
	public List<SurveyResult> getAll() {
		return surveyResultDao.getAll();
	}


	@Override
	public int update(SurveyResult surveyResult) {
		return surveyResultDao.update(surveyResult);
	}

	@Override
	public int delete(int studentId) {
		return surveyResultDao.delete(studentId);
	}

	@Override
	public int deleteAll() {
		return surveyResultDao.deleteAll();
	}

	

}
