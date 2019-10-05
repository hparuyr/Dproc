package am.dproc.sms.db.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.TestResultDAO;

@Repository
public class TestResultDAODBImpl implements TestResultDAO {
	private static final String CREATE_TEST_RESULT = "insert into mydb.TEST_RESULT(TEST_ID, STUDENT_ID, SCORE, CREATION_DATE) values (?,?,?,?)";
	private static final String GET_lAST_TEST_RESULT_FOR_STUDENT = "select SCORE from mydb.TEST_RESULT where TEST_ID = ? and STUDENT_ID = ? order by CREATION_DATE desc limit 1";
	private static final String GET_TEST_RESULT_BY_ID = "select SCORE from mydb.TEST_RESULT where ID = ?";
	private static final String GET_AVG_TEST_RESULT_FOR_STUDENT = "select avg(SCORE) as avgScore from mydb.TEST_RESULT where STUDENT_ID = ?";
	private static final String GET_AVG_TEST_RESULT_FOR_STUDENT_COURSE = "SELECT avg(TR.SCORE) AS avgScore \r\n" +
			                                                             "FROM mydb.TEST_RESULT TR \r\n" +
			                                                             "  JOIN TEST T ON T.ID = TR.TEST_ID\r\n" +
			                                                             "  JOIN LESSON L ON L.ID = T.LESSON_ID\r\n" +
			                                                             "  JOIN COURSE C ON C.ID = L.COURSE_ID\r\n" +
			                                                             "WHERE TR.STUDENT_ID = ? AND C.ID = ?";
	private static final String UPDATE_TEST_RESULT_FOR_STUDENT = "update mydb.TEST_RESULT set SCORE = ?, CHANGE_DATE = ? where TEST_ID = ? and STUDENT_ID = ?";
	private static final String UPDATE_TEST_RESULT_BY_ID = "update mydb.TEST_RESULT set SCORE = ?, CHANGE_DATE = ? where ID = ?";
	private static final String DELETE_TEST_RESULT_FOR_STUDENT = "delete from mydb.TEST_RESULT where TEST_ID = ? and STUDENT_ID = ?";
	private static final String DELETE_TEST_RESULT_BY_ID = "delete from mydb.TEST_RESULT where ID = ?";

	@Autowired
	JdbcTemplate template;

	@Override
	public Integer createTestResult(Integer testId, Integer studentId, Double score) {
		return template.update(CREATE_TEST_RESULT, testId, studentId, score, System.currentTimeMillis());
	}

	@Override
	public Double getTestResultById(Integer id) {
		return template.queryForObject(GET_TEST_RESULT_BY_ID, Double.class, id);
	}

	@Override
	public Double getLastTestResultForStudent(Integer testId, Integer studentId) {
		return template.queryForObject(GET_lAST_TEST_RESULT_FOR_STUDENT, Double.class, testId, studentId);
	}

	@Override
	public Double getAverageTestResultForStudent(Integer studentId) {
		return template.queryForObject(GET_AVG_TEST_RESULT_FOR_STUDENT, Double.class, studentId);
	}

	@Override
	public Double getAverageTestResultForStudentCourse(Integer studentId, Integer courseId) {
		return template.queryForObject(GET_AVG_TEST_RESULT_FOR_STUDENT_COURSE, Double.class, studentId, courseId);
	}

	@Override
	public Integer updateTestResultForStudent(Integer testId, Integer studentId, Double score) {
		return template.update(UPDATE_TEST_RESULT_FOR_STUDENT, score, System.currentTimeMillis(), testId, studentId);
	}

	@Override
	public Integer updateTestResultById(Integer id, Double score) {
		return template.update(UPDATE_TEST_RESULT_BY_ID, score, System.currentTimeMillis(), id);
	}

	@Override
	public Integer deleteTestResultById(Integer id) {
		return template.update(DELETE_TEST_RESULT_BY_ID, id);
	}

	@Override
	public Integer deleteTestResultForStudent(Integer testId, Integer studentId) {
		return template.update(DELETE_TEST_RESULT_FOR_STUDENT, testId, studentId);
	}
}
