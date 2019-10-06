package am.dproc.sms.db.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.TestResultDAO;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class TestResultDAODBImpl implements TestResultDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String CREATE_TEST_RESULT = "" +
            "INSERT " +
            "INTO mydb.TEST_RESULT(TEST_ID, STUDENT_ID, SCORE, CREATION_DATE) " +
            "VALUES (?, ?, ?, ?)";
    private static final String GET_LAST_TEST_RESULT_FOR_STUDENT = "" +
            "SELECT SCORE " +
            "FROM mydb.TEST_RESULT " +
            "WHERE TEST_ID = ? " +
            "AND STUDENT_ID = ? " +
            "ORDER BY CREATION_DATE " +
            "DESC LIMIT 1";
    private static final String GET_TEST_RESULT_BY_ID = "" +
            "SELECT SCORE " +
            "FROM mydb.TEST_RESULT " +
            "WHERE ID = ?";
    private static final String GET_AVG_TEST_RESULT_FOR_STUDENT = "" +
            "SELECT avg(SCORE) " +
            "AS avgScore " +
            "FROM mydb.TEST_RESULT " +
            "WHERE STUDENT_ID = ?";
    private static final String GET_AVG_TEST_RESULT_FOR_STUDENT_COURSE = "" +
            "SELECT avg(TR.SCORE) " +
            "AS avgScore \r\n" +
            "FROM mydb.TEST_RESULT TR " +
            "JOIN TEST T " +
            "ON T.ID = TR.TEST_ID " +
            "JOIN LESSON L " +
            "ON L.ID = T.LESSON_ID " +
            "JOIN COURSE C " +
            "ON C.ID = L.COURSE_ID " +
            "WHERE TR.STUDENT_ID = ? " +
            "AND C.ID = ?";
    private static final String UPDATE_TEST_RESULT_FOR_STUDENT = "" +
            "UPDATE mydb.TEST_RESULT " +
            "SET SCORE = ?, CHANGE_DATE = ? " +
            "WHERE TEST_ID = ? " +
            "AND STUDENT_ID = ?";
    private static final String UPDATE_TEST_RESULT_BY_ID = "" +
            "UPDATE mydb.TEST_RESULT " +
            "SET SCORE = ?, CHANGE_DATE = ? " +
            "WHERE ID = ?";
    private static final String DELETE_TEST_RESULT_FOR_STUDENT = "" +
            "DELETE " +
            "FROM mydb.TEST_RESULT " +
            "WHERE TEST_ID = ? " +
            "AND STUDENT_ID = ?";
    private static final String DELETE_TEST_RESULT_BY_ID = "" +
            "DELETE " +
            "FROM mydb.TEST_RESULT " +
            "WHERE ID = ?";


    @Override
    public Integer createTestResult(Integer testId, Integer studentId, Double score) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE_TEST_RESULT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, testId);
            ps.setInt(2, studentId);
            ps.setDouble(3, score);
            ps.setLong(4, System.currentTimeMillis());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public Double getTestResultById(Integer id) {
        return jdbcTemplate.queryForObject(GET_TEST_RESULT_BY_ID, Double.class, id);
    }

    @Override
    public Double getLastTestResultForStudent(Integer testId, Integer studentId) {
        return jdbcTemplate.queryForObject(GET_LAST_TEST_RESULT_FOR_STUDENT, Double.class, testId, studentId);
    }

    @Override
    public Double getAverageTestResultForStudent(Integer studentId) {
        return jdbcTemplate.queryForObject(GET_AVG_TEST_RESULT_FOR_STUDENT, Double.class, studentId);
    }

    @Override
    public Double getAverageTestResultForStudentCourse(Integer studentId, Integer courseId) {
        return jdbcTemplate.queryForObject(GET_AVG_TEST_RESULT_FOR_STUDENT_COURSE, Double.class, studentId, courseId);
    }

    @Override
    public Integer updateTestResultForStudent(Integer testId, Integer studentId, Double score) {
        return jdbcTemplate.update(UPDATE_TEST_RESULT_FOR_STUDENT, score, System.currentTimeMillis(), testId, studentId);
    }

    @Override
    public Integer updateTestResultById(Integer id, Double score) {
        return jdbcTemplate.update(UPDATE_TEST_RESULT_BY_ID, score, System.currentTimeMillis(), id);
    }

    @Override
    public Integer deleteTestResultById(Integer id) {
        return jdbcTemplate.update(DELETE_TEST_RESULT_BY_ID, id);
    }

    @Override
    public Integer deleteTestResultForStudent(Integer testId, Integer studentId) {
        return jdbcTemplate.update(DELETE_TEST_RESULT_FOR_STUDENT, testId, studentId);
    }

}
