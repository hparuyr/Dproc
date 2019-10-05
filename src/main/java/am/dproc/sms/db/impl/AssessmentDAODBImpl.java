package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.AssessmentDAO;
import am.dproc.sms.models.Assessment;

@Repository
public class AssessmentDAODBImpl implements AssessmentDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String GET_ASSESSMENT_BY_ID = "SELECT * FROM mydb.ASSESSMENT WHERE ID = ?";
	private static final String GET_ALL_ASSESSMENTS = "SELECT * FROM mydb.ASSESSMENT";
	private static final String GET_ALL_ASSESSMENT_BY_TITLE = "SELECT * FROM mydb.ASSESSMENT WHERE TITLE = ?";
	private static final String GET_AVG_SCORE_BY_STUDENT_COURSE = "SELECT AVG(ASM.SCORE) as avgScore \r\n" +
			                                                      "FROM ASSESSMENT ASM \r\n" +
                                                      			  "  JOIN ASSIGNMENT ASGN ON ASM.ASSIGNMENT_ID = ASGN.ID\r\n" +
			                                                      "  JOIN LESSON L ON L.ID = ASGN.LESSON_ID\r\n" +
			                                                      "  JOIN COURSE C ON C.ID = L.COURSE_ID\r\n" +
			                                                      "WHERE ASM.STUDENT_ID = ? AND C.ID = ?\r\n";
	private static final String GET_AVG_SCORE_BY_STUDENT = "SELECT AVG(SCORE) as avgScore FROM ASSESSMENT WHERE STUDENT_ID = ?";
	private static final String GET_ALL_ASSESSMENT_BY_STUDENT_ID = "SELECT * FROM mydb.ASSESSMENT WHERE STUDENT_ID = ?";
	private static final String GET_ALL_ASSESSMENT_BY_ASSIGNMENT_ID = "SELECT * FROM mydb.ASSESSMENT WHERE ASSIGNMENT_ID = ?";
	private static final String GET_ASSESSMENT_BY_STUDENT_ID_AND_ASSIGNMENT_ID = "SELECT \r\n" +
			"IFNULL(\r\n" +
			"    ( SELECT ID FROM mydb.ASSESSMENT\r\n" +
			"        WHERE STUDENT_ID = ? AND ASSIGNMENT_ID = ?\r\n" +
			"    ),\r\n" +
			"    '0'\r\n" +
			")\r\n" +
			"";
	private static final String DELETE_ASSESSMENT_BY_ID = "DELETE FROM mydb.ASSESSMENT WHERE ID = ?";
	private static final String DELETE_ALL_ASSESSMENT = "DELETE FROM mydb.ASSESSMENT";
	private static final String ADD_ASSESSMENT = "INSERT INTO mydb.ASSESSMENT (TITLE, SCORE, CREATION_DATE, CHANGE_DATE,USER_ID, ASSIGNMENT_ID,) VALUES (?, ?, ?, ?, ?, ?)";

	@Override
	public Assessment getAssessment(Integer id) {
		return jdbctemplate.queryForObject(GET_ASSESSMENT_BY_ID, new AssessmentMapper(), id);
	}

	@Override
	public Assessment getAssessmentByTitle(String title) {
		return jdbctemplate.queryForObject(GET_ALL_ASSESSMENT_BY_TITLE, new AssessmentMapper(), title);
	}

	@Override
	public List<Assessment> getAllAssessments() {
		return jdbctemplate.query(GET_ALL_ASSESSMENTS, new AssessmentMapper());
	}

	@Override
	public List<Assessment> getAllAssessmentsByUserId(Integer userId) {
		return jdbctemplate.query(GET_ALL_ASSESSMENT_BY_STUDENT_ID, new AssessmentMapper(), userId);
	}

	@Override
	public List<Assessment> getAssessmentsByAssignmentId(Integer assignmentId) {
		return jdbctemplate.query(GET_ALL_ASSESSMENT_BY_ASSIGNMENT_ID, new AssessmentMapper(), assignmentId);
	}

	@Override
	public Integer getAssessmentByStudentIDAndAssignmentID(Integer studentId, Integer assignmentId) {
		return jdbctemplate.queryForObject(GET_ASSESSMENT_BY_STUDENT_ID_AND_ASSIGNMENT_ID, Integer.class, studentId, assignmentId);
	}

	@Override
	public Double getAverageScoreByStudentCourse(Integer studentId, Integer courseId) {
		return jdbctemplate.queryForObject(GET_AVG_SCORE_BY_STUDENT_COURSE, Double.class, studentId, courseId);
	}

	@Override
	public Double getAverageScoreByStudent(Integer studentId) {
		return jdbctemplate.queryForObject(GET_AVG_SCORE_BY_STUDENT, Double.class, studentId);
	}

	@Override
	public Integer deleteAssessment(Integer id) {
		return jdbctemplate.update(DELETE_ASSESSMENT_BY_ID, id);
	}

	@Override
	public Integer deleteAllAssessments() {
		return jdbctemplate.update(DELETE_ALL_ASSESSMENT);
	}

	@Override
	public Integer addAssessment(Assessment asses) {
		return jdbctemplate.update(ADD_ASSESSMENT,
				new Object[] { asses.getId(), asses.getScore(), asses.getUserId(), asses.getAssignmentId() });
	}

	private static class AssessmentMapper implements RowMapper<Assessment> {

		@Override
		public Assessment mapRow(ResultSet rs, int rowNum) throws SQLException {
			Assessment assessment = new Assessment();

			assessment.setId(rs.getInt("ID"));
			assessment.setScore(rs.getInt("SCORE"));
			assessment.setCreationDate(rs.getLong("CREATION_DATE"));
			assessment.setChangeDate(rs.getLong("CHANGE_DATE"));
			assessment.setUserId(rs.getInt("STUDENT_ID"));
			assessment.setAssignmentId(rs.getInt("ASSIGNMENT_ID"));

			return assessment;
		}
	}
}
