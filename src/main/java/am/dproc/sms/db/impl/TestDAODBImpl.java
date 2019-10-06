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

import am.dproc.sms.db.interfaces.TestDAO;
import am.dproc.sms.models.Test;

@Repository
public class TestDAODBImpl implements TestDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String CREATE_TEST = "" +
            "INSERTT " +
            "INTO mydb.TEST(LESSON_ID, TITLE, CREATION_DATE) " +
            "VALUES (?, ?, ?)";
    private static final String GET_TEST_BY_ID = "" +
            "SELECT ID, TITLE, LESSON_ID " +
            "FROM mydb.TEST " +
            "WHERE ID = ? ";
    private static final String GET_ALL_TESTS = "" +
            "SELECT ID, TITLE, LESSON_ID " +
            "FROM mydb.TEST";
    private static final String UPDATE_TEST = "" +
            "UPDATE mydb.TEST " +
            "SET TITLE = ?, LESSON_ID = ?, CHANGE_DATE = ? " +
            "WHERE ID = ?";
    private static final String DELETE_TEST = "" +
            "DELETE " +
            "FROM mydb.TEST " +
            "WHERE ID = ?";

    @Override
    public Integer createTest(Test test) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE_TEST, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, test.getLessonId());
            ps.setString(2, test.getTitle());
            ps.setLong(4, System.currentTimeMillis());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public Test getTest(Integer id) {
        return jdbcTemplate.queryForObject(GET_TEST_BY_ID, new TestMapper(), id);
    }

    @Override
    public List<Test> getAllTests() {
        return jdbcTemplate.query(GET_ALL_TESTS, new TestMapper());
    }

    @Override
    public Integer updateTest(Test test) {
        return jdbcTemplate.update(UPDATE_TEST, test.getTitle(), test.getLessonId(), System.currentTimeMillis(),
                test.getId());
    }

    @Override
    public Integer deleteTest(Integer id) {
        return jdbcTemplate.update(DELETE_TEST, id);
    }

    private static class TestMapper implements RowMapper<Test> {
        @Override
        public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
            Test test = new Test();

            test.setId(rs.getInt("ID"));
            test.setLessonId(rs.getInt("LESSON_ID"));
            test.setTitle(rs.getString("TITLE"));

            return test;
        }
    }
}