package am.dproc.sms.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.SurveyQuestionDAO;
import am.dproc.sms.models.SurveyQuestion;

@Repository
public class SurveyQuestionDAOImpl implements SurveyQuestionDAO{
	@Autowired
	JdbcTemplate template;
	
	public static final String BATCH_CREATE = "INSERT INTO SURVEY_QUESTION (CONTENT, INPUT_NAME, CREATION_DATE, CHANGE_DATE) VALUES (?, ?, ?, ?)";
	public static final String GET_ALL = "SELECT * FROM SURVEY_QUESTION";
	
	@Override
	public int create(String content, String inputName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] createBatch(List<SurveyQuestion> surveyQuestions) {
		long currentTimeStamp = System.currentTimeMillis();
		
		return template.batchUpdate(BATCH_CREATE, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, surveyQuestions.get(i).getContent());
				ps.setString(2, surveyQuestions.get(i).getInputName());
				ps.setLong(3, currentTimeStamp);
				ps.setLong(4, currentTimeStamp);
			}
			
			@Override
			public int getBatchSize() {
				return surveyQuestions.size();
			}
		});
	}

	@Override
	public List<SurveyQuestion> getAll() {
		return template.query(GET_ALL, new SurveyQuestionMapper());
	}

	private static class SurveyQuestionMapper implements RowMapper<SurveyQuestion>{

		@Override
		public SurveyQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
			SurveyQuestion surveyQuestion = new SurveyQuestion();
			surveyQuestion.setId(rs.getInt("ID"));
			surveyQuestion.setContent(rs.getString("CONTENT"));
			surveyQuestion.setInputName(rs.getString("INPUT_NAME"));
			return surveyQuestion;
		}
	}
}
