package am.dproc.sms.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.QuestionDAO;
import am.dproc.sms.models.Question;

@Repository
public class QuestionDAODBImpl implements QuestionDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String ADD_QUESTION = "" +
            "INSERT " +
            "INTO mydb.QUESTION(TEST_ID, CONTENT, CREATION_DATE) " +
            "VALUES (?, ?, ?)";
    private static final String GET_QUESTION_BY_ID = "" +
            "SELECT ID, TEST_ID, CONTENT " +
            "FROM mydb.QUESTION " +
            "WHERE ID = ?";
    private static final String GET_QUESTIONS_FOR_TEST = "" +
            "SELECT ID, TEST_ID, CONTENT " +
            "FROM mydb.QUESTION " +
            "WHERE TEST_ID = ?";
    private static final String GET_ALL_QUESTIONS = "" +
            "SELECT ID, TEST_ID, CONTENT " +
            "FROM mydb.QUESTION";
    private static final String UPDATE_QUESTION_CONTENT = "" +
            "UPDATE mydb.QUESTION " +
            "SET CONTENT = ?, CHANGE_DATE = ? " +
            "WHERE ID = ?";
    private static final String DELETE_QUESTION = "" +
            "DELETE " +
            "FROM mydb.QUESTION " +
            "WHERE ID = ?";

    @Override
    public Integer addQuestion(Question question) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(ADD_QUESTION, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, question.getTestId());
            ps.setString(2, question.getContent());
            ps.setLong(3, System.currentTimeMillis());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public Question getQuestion(Integer id) {
        return jdbcTemplate.queryForObject(GET_QUESTION_BY_ID, new QuestionMapper(), id);
    }

    @Override
    public List<Question> getAllQuestions() {
        return jdbcTemplate.query(GET_ALL_QUESTIONS, new QuestionMapper());
    }

    @Override
    public List<Question> getQuestionsForTest(Integer testId) {
        return jdbcTemplate.query(GET_QUESTIONS_FOR_TEST, new QuestionMapper(), testId);
    }

    @Override
    public Integer updateQuestionContent(Integer id, String content) {
        return jdbcTemplate.update(UPDATE_QUESTION_CONTENT, content, System.currentTimeMillis(), id);
    }

    @Override
    public Integer deleteQuestion(Integer id) {
        return jdbcTemplate.update(DELETE_QUESTION, id);
    }

    private static class QuestionMapper implements RowMapper<Question> {

        @Override
        public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
            Question question = new Question();

            question.setId(rs.getInt("ID"));
            question.setTestId(rs.getInt("TEST_ID"));
            question.setContent(rs.getString("CONTENT"));

            return question;
        }

    }
}
