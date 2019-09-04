package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.root.AnswerDAO;
import am.dproc.sms.models.Answer;

@Repository
public class AnswerDAODBImpl implements AnswerDAO {

	@Autowired
	JdbcTemplate template;

	private static final String CREATE_ANSWER = "insert into mydb.ANSWER(CONTENT, SCORE, QUESTION_ID, CREATION_DATE) values (?, ?, ?, ?)";
	private static final String GET_ANSWER_BY_ID = "select * from mydb.ANSWER where ID = ?";
	private static final String GET_ANSWERS_FOR_QUESTION = "select * from mydb.ANSWER where QUESTION_ID = ?";
	private static final String GET_ALL_ANSWERS = "select * from mydb.ANSWER";
	private static final String UPDATE_ANSWER = "update mydb.ANSWER set CONTENT = ?, SCORE = ?, QUESTION_ID = ?, CHANGE_DATE = ? where ID = ?";
	private static final String DELETE_ANSWER = "delete from mydb.ANSWER where ID = ?";

	@Override
	public Integer createAnswer(Answer answer) {
		return template.update(CREATE_ANSWER, answer.getContent(), answer.getScore(), answer.getQuestionId(),
				System.currentTimeMillis());
	}

	@Override
	public Answer getAnswer(Integer id) {
		return template.queryForObject(GET_ANSWER_BY_ID, new AnswerMapper(), id);
	}

	@Override
	public List<Answer> getAllAnswers() {
		return template.query(GET_ALL_ANSWERS, new AnswerMapper());
	}

	@Override
	public List<Answer> getAnswersForQuestion(Integer questionId) {
		return template.query(GET_ANSWERS_FOR_QUESTION, new AnswerMapper(), questionId);
	}

	@Override
	public Integer updateAnswer(Answer answer) {
		return template.update(UPDATE_ANSWER, answer.getContent(), answer.getScore(), answer.getQuestionId(),
				System.currentTimeMillis(), answer.getId());
	}

	@Override
	public Integer deleteAnswer(Integer id) {
		return template.update(DELETE_ANSWER, id);
	}

	private static class AnswerMapper implements RowMapper<Answer> {
		@Override
		public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Answer ans = new Answer();
			ans.setId(rs.getInt("ID"));
			ans.setContent(rs.getString("CONTENT"));
			ans.setScore(rs.getInt("SCORE"));
			ans.setQuestionId(rs.getInt("QUESTION_ID"));

			return ans;
		}
	}
}
