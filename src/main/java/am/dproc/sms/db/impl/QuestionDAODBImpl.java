package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.root.QuestionDAO;
import am.dproc.sms.models.Question;

@Repository
public class QuestionDAODBImpl implements QuestionDAO {

	@Autowired
	JdbcTemplate template;

	private static final String CREATE_QUESTION = "insert into mydb.QUESTION(CONTENT, TEST_ID, CREATION_DATE) values (?, ?, ?)";
	private static final String GET_QUESTION_BY_ID = "select * from mydb.QUESTION where ID = ?";
	private static final String GET_QUESTIONS_FOR_TEST = "select * from mydb.QUESTION where TEST_ID = ?";
	private static final String GET_ALL_QUESTIONS = "select * from mydb.QUESTION";
	private static final String UPDATE_QUESTION = "update mydb.QUESTION set CONTENT = ?, TEST_ID = ?, CHANGE_DATE = ? where ID = ?";
	private static final String DELETE_QUESTION = "delete from mydb.QUESTION where ID = ?";

	@Override
	public Integer createQuestion(Question question) {
		return template.update(CREATE_QUESTION, question.getContent(), question.getTestId(),
				System.currentTimeMillis());
	}

	@Override
	public Question getQuestion(Integer id) {
		return template.queryForObject(GET_QUESTION_BY_ID, new QuestionMapper(), id);
	}

	@Override
	public List<Question> getAllQuestions() {
		return template.query(GET_ALL_QUESTIONS, new QuestionMapper());
	}

	@Override
	public List<Question> getQuestionsForTest(Integer testId) {
		return template.query(GET_QUESTIONS_FOR_TEST, new QuestionMapper(), testId);
	}

	@Override
	public Integer updateQuestion(Question question) {
		return template.update(UPDATE_QUESTION, question.getContent(), question.getTestId(), System.currentTimeMillis(),
				question.getId());
	}

	@Override
	public Integer deleteQuestion(Integer id) {
		return template.update(DELETE_QUESTION, id);
	}

	private static class QuestionMapper implements RowMapper<Question> {

		@Override
		public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
			Question q = new Question();
			q.setId(rs.getInt("ID"));
			q.setContent(rs.getString("CONTENT"));
			q.setTestId(rs.getInt("TEST_ID"));

			return q;
		}

	}
}
