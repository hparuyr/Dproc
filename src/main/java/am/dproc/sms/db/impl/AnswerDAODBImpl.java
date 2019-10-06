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

import am.dproc.sms.db.interfaces.AnswerDAO;
import am.dproc.sms.models.Answer;

@Repository
public class AnswerDAODBImpl implements AnswerDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String CREATE_ANSWER = "" +
            "INSERT " +
            "INTO mydb.ANSWER(QUESTION_ID, CONTENT, SCORE, REATION_DATE) " +
            "VALUES (?, ?, ?, ?)";
    private static final String GET_ANSWER_BY_ID = "" +
            "SELECT QUESTION_ID, CONTENT, SCORE " +
            "FROM mydb.ANSWER " +
            "WHERE ID = ?";
    private static final String GET_ANSWERS_FOR_QUESTION = "" +
            "SELECT QUESTION_ID, CONTENT, SCORE " +
            "FROM mydb.ANSWER " +
            "WHERE QUESTION_ID = ?";
    private static final String GET_ALL_ANSWERS = "" +
            "SELECT QUESTION_ID, CONTENT, SCORE " +
            "FROM mydb.ANSWER";
    private static final String UPDATE_ANSWER = "" +
            "UPDATE mydb.ANSWER " +
            "SET CONTENT = ?, SCORE = ?, QUESTION_ID = ?, CHANGE_DATE = ? " +
            "WHERE ID = ?";
    private static final String DELETE_ANSWER = "" +
            "DELETE " +
            "FROM mydb.ANSWER " +
            "WHERE ID = ?";

    @Override
    public Integer createAnswer(Answer answer) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE_ANSWER, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, answer.getQuestionId());
            ps.setString(2, answer.getContent());
            ps.setInt(3, answer.getScore());
            ps.setLong(4, System.currentTimeMillis());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public Answer getAnswer(Integer id) {
        return jdbcTemplate.queryForObject(GET_ANSWER_BY_ID, new AnswerMapper(), id);
    }

    @Override
    public List<Answer> getAnswersForQuestion(Integer questionId) {
        return jdbcTemplate.query(GET_ANSWERS_FOR_QUESTION, new AnswerMapper(), questionId);
    }

    @Override
    public List<Answer> getAllAnswers() {
        return jdbcTemplate.query(GET_ALL_ANSWERS, new AnswerMapper());
    }

    @Override
    public Integer updateAnswer(Answer answer) {
        return jdbcTemplate.update(UPDATE_ANSWER, answer.getContent(), answer.getScore(), answer.getQuestionId(),
                System.currentTimeMillis(), answer.getId());
    }

    @Override
    public Integer deleteAnswer(Integer id) {
        return jdbcTemplate.update(DELETE_ANSWER, id);
    }

    private static class AnswerMapper implements RowMapper<Answer> {
        @Override
        public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Answer ans = new Answer();

            ans.setId(rs.getInt("ID"));
            ans.setQuestionId(rs.getInt("QUESTION_ID"));
            ans.setContent(rs.getString("CONTENT"));
            ans.setScore(rs.getInt("SCORE"));

            return ans;
        }
    }
}
