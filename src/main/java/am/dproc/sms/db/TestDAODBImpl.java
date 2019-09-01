package am.dproc.sms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.modules.AnswerBean;
import am.dproc.sms.modules.QuestionBean;
import am.dproc.sms.modules.TestBean;

@Repository
public class TestDAODBImpl implements TestDAO {
	@Autowired
	JdbcTemplate template;

	private static final String CREATE_TEST = "insert into mydb.TEST(TITLE, LESSON_ID, CREATION_DATE) values (?, ?, ?)";
	private static final String GET_TEST_BY_ID = "select * from mydb.TEST where ID = ? ";
	private static final String GET_ALL_TESTS = "select * from mydb.TEST";
	private static final String UPDATE_TEST = "update mydb.TEST set TITLE = ?, LESSON_ID = ?, CHANGE_DATE = ? where ID = ?";
	private static final String DELETE_TEST = "delete from mydb.TEST where ID = ?";

	@Override
	public Integer createTest(TestBean test) {
		return template.update(CREATE_TEST, test.getTitle(), test.getLessonId(), System.currentTimeMillis());
	}

	@Override
	public TestBean getTest(Integer id) {
		TestBean test = template.queryForObject(GET_TEST_BY_ID, new TestMapper(), id);
		return test;
	}

	@Override
	public List<TestBean> getAllTests() {
		return template.query(GET_ALL_TESTS, new TestMapper());
	}

	@Override
	public Integer updateTest(TestBean test) {
		return template.update(UPDATE_TEST, test.getTitle(), test.getLessonId(), System.currentTimeMillis(), test.getId());
	}

	@Override
	public Integer deleteTest(Integer id) {
		return template.update(DELETE_TEST, id);
	}

	private static class TestMapper implements RowMapper<TestBean> {
		@Autowired
		JdbcTemplate template;

		@Override
		public TestBean mapRow(ResultSet rs, int rownumber) throws SQLException {
			TestBean test = new TestBean();
			test.setId(rs.getInt("ID"));
			test.setTitle(rs.getString("TITLE"));
			test.setLessonId(rs.getInt("LESSON_ID"));

			//test.setQuestions(getQuestionsForTest(test.getId()));

			return test;
		}

		private List<QuestionBean> getQuestionsForTest(Integer id) {
			return template.query("select ID, CONTENT from QUESTION where TEST_ID = ? ", new Object[] { id },
					new RowMapper<QuestionBean>() {
						@Override
						public QuestionBean mapRow(ResultSet rs, int rownumber) throws SQLException {
							QuestionBean question = new QuestionBean();
							question.setId(rs.getInt("ID"));
							question.setContent(rs.getString("CONTENT"));
							question.setTestId(id);

							question.setAnswers(getAnswersForQuestion(question.getId()));

							return question;
						}
					});

		}

		private List<AnswerBean> getAnswersForQuestion(Integer id) {
			return template.query("select ID, CONTENT, SCORE from ANSWER where QUESTION_ID = :id ", new Object[] { id },
					new RowMapper<AnswerBean>() {
						@Override
						public AnswerBean mapRow(ResultSet rs, int rownumber) throws SQLException {
							AnswerBean answer = new AnswerBean();
							answer.setId(rs.getInt("ID"));
							answer.setContent(rs.getString("CONTENT"));
							answer.setScore(rs.getInt("SCORE"));
							answer.setQuestionId(id);

							return answer;
						}
					});
		}
	}
}