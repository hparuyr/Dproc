package am.dproc.sms.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import am.dproc.sms.db.interfaces.AssessmentDAO;
import am.dproc.sms.models.Assessment;

@Repository
public class AssessmentDAODBImpl implements AssessmentDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String ADD_ASSESSMENT = "" +
            "INSERT " +
            "INTO mydb.ASSESSMENT(STUDENT_ID, ASSIGNMENT_COMPLETED_ID, SCORE, COMMENT, CREATION_DATE) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String GET_ASSESSMENT_BY_ID = "" +
            "SELECT ID, STUDENT_ID, ASSIGNMENT_COMPLETED_ID, SCORE, COMMENT " +
            "FROM mydb.ASSESSMENT " +
            "WHERE ID = ?";
    private static final String GET_AVG_SCORE_BY_STUDENT_COURSE = "" +
            "SELECT AVG(ASM.SCORE) as avgScore " +
            "FROM ASSESSMENT ASM " +
            "JOIN ASSIGNMENT_COMPLETED AC ON ASM.ASSIGNMENT_COMPLETED_ID = AC.ID " +
            "JOIN ASSIGNMENT ASGN ON AC.ASSIGNMENT_ID = ASGN.ID " +
            "JOIN LESSON L ON L.ID = ASGN.LESSON_ID " +
            "JOIN COURSE C ON C.ID = L.COURSE_ID " +
            "WHERE ASM.STUDENT_ID = ? AND C.ID = ? ";
    private static final String GET_AVG_SCORE_BY_STUDENT = "" +
            "SELECT AVG(SCORE) " +
            "AS avgScore " +
            "FROM ASSESSMENT " +
            "WHERE STUDENT_ID = ?";
    private static final String GET_ALL_ASSESSMENT_BY_STUDENT_ID = "" +
            "SELECT ID, STUDENT_ID, ASSIGNMENT_COMPLETED_ID, SCORE, COMMENT " +
            "FROM mydb.ASSESSMENT " +
            "WHERE STUDENT_ID = ?";
    private static final String GET_ALL_ASSESSMENT_BY_ASSIGNMENT_ID = "" +
    		"SELECT ASM.ID, ASM.STUDENT_ID, ASM.ASSIGNMENT_COMPLETED_ID, ASM.SCORE, ASM.COMMENT " +
    		"FROM mydb.ASSIGNMENT ASGN " +
    		"JOIN mydb.ASSIGNMENT_COMPLETED AC ON AC.ASSIGNMENT_ID = ASGN.ID " +
    		"JOIN mydb.ASSESSMENT ASM ON ASM.ASSIGNMENT_COMPLETED_ID = AC.ID " +
    		"WHERE ASGN.ID = ? ";
    private static final String GET_ASSESSMENT_BY_STUDENT_ID_AND_ASSIGNMENT_ID = "" +
            "SELECT SCORE " +
            "FROM mydb.ASSESSMENT " +
            "WHERE STUDENT_ID = ? " +
            "AND ASSIGNMENT_ID = ?";
    private static final String GET_ASSESSMENT_OBJ_BY_STUDENT_ID_AND_ASSIGNMENT_ID = "" +
            "SELECT A.ID, A.SCORE, A.COMMENT " +
            "FROM mydb.ASSESSMENT A " +
            "JOIN ASSIGNMENT_COMPLETED AC " +
            "ON AC.ID = A.ASSIGNMENT_COMPLETED_ID " +
            "WHERE AC.STUDENT_ID = ? " +
            "AND AC.ASSIGNMENT_ID = ?";
    private static final String GET_ALL_ASSESSMENTS = "" +
            "SELECT ID, STUDENT_ID, ASSIGNMENT_COMPLETED_ID, SCORE, COMMENT " +
            "FROM mydb.ASSESSMENT";
    private static final String DELETE_ASSESSMENT_BY_ID = "" +
            "DELETE " +
            "FROM mydb.ASSESSMENT " +
            "WHERE ID = ?";
    private static final String DELETE_ALL_ASSESSMENT = "" +
            "DELETE " +
            "FROM mydb.ASSESSMENT";

    @Override
    public Integer addAssessment(Assessment assessment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(ADD_ASSESSMENT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, assessment.getUserId());
            ps.setInt(2, assessment.getAssignmentCompletedId());
            ps.setInt(3, assessment.getScore());
            ps.setString(4, assessment.getComment());
            ps.setLong(5, System.currentTimeMillis());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public Assessment getAssessment(Integer id) {
        return jdbcTemplate.queryForObject(GET_ASSESSMENT_BY_ID, new AssessmentMapper(), id);
    }

    @Override
    public List<Assessment> getAllAssessments() {
        return jdbcTemplate.query(GET_ALL_ASSESSMENTS, new AssessmentMapper());
    }

    @Override
    public List<Assessment> getAllAssessmentsByUserId(Integer userId) {
        return jdbcTemplate.query(GET_ALL_ASSESSMENT_BY_STUDENT_ID, new AssessmentMapper(), userId);
    }

    @Override
    public List<Assessment> getAssessmentsByAssignmentId(Integer assignmentId) {
        return jdbcTemplate.query(GET_ALL_ASSESSMENT_BY_ASSIGNMENT_ID, new AssessmentMapper(), assignmentId);
    }

    @Override
    public Integer getAssessmentByStudentIdAndAssignmentId(Integer studentId, Integer assignmentId) {
        return jdbcTemplate.queryForObject(GET_ASSESSMENT_BY_STUDENT_ID_AND_ASSIGNMENT_ID, Integer.class, studentId, assignmentId);
    }

    @Override
    public Assessment getAssessmentObjByStudentIdAndAssignmentId(Integer studentID, Integer assignmentID) {

        try {
            return jdbcTemplate.queryForObject(GET_ASSESSMENT_OBJ_BY_STUDENT_ID_AND_ASSIGNMENT_ID, (rs, rowNum) -> {
                Assessment assessment = new Assessment();

                assessment.setId(rs.getInt("ID"));
                assessment.setScore(rs.getInt("SCORE"));
                assessment.setComment(rs.getString("COMMENT"));

                return assessment;
            }, studentID, assignmentID);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Double getAverageScoreByStudentCourse(Integer studentId, Integer courseId) {
        return jdbcTemplate.queryForObject(GET_AVG_SCORE_BY_STUDENT_COURSE, Double.class, studentId, courseId);
    }

    @Override
    public Double getAverageScoreByStudent(Integer studentId) {
        return jdbcTemplate.queryForObject(GET_AVG_SCORE_BY_STUDENT, Double.class, studentId);
    }

    @Override
    public Integer deleteAssessment(Integer id) {
        return jdbcTemplate.update(DELETE_ASSESSMENT_BY_ID, id);
    }

    @Override
    public Integer deleteAllAssessments() {
        return jdbcTemplate.update(DELETE_ALL_ASSESSMENT);
    }

    private static class AssessmentMapper implements RowMapper<Assessment> {

        @Override
        public Assessment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Assessment assessment = new Assessment();

            assessment.setId(rs.getInt("ID"));
            assessment.setUserId(rs.getInt("STUDENT_ID"));
            assessment.setAssignmentCompletedId(rs.getInt("ASSIGNMENT_COMPLETED_ID"));
            assessment.setScore(rs.getInt("SCORE"));
            assessment.setComment(rs.getString("COMMENT"));

            return assessment;
        }
    }
}
