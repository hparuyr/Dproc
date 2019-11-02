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

import am.dproc.sms.db.interfaces.AssignmentDAO;
import am.dproc.sms.models.Assignment;

@Repository
public class AssignmentDAODBImpl implements AssignmentDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String ADD_ASSIGNMENT = "" +
            "INSERT " +
            "INTO mydb.ASSIGNMENT (TEACHER_ID, LESSON_ID, START_DATE, END_DATE, TITLE, DESCRIPTION, CREATION_DATE) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ASSIGNMENT_BY_ID = "" +
            "SELECT ID, TEACHER_ID, LESSON_ID, TITLE, DESCRIPTION, START_DATE, END_DATE " +
            "FROM mydb.ASSIGNMENT " +
            "WHERE ID = ?";
    private static final String GET_ALL_ASSIGNMENTS = "" +
            "SELECT ID, TEACHER_ID, LESSON_ID, TITLE, DESCRIPTION, START_DATE, END_DATE " +
            "FROM mydb.ASSIGNMENT";
    private static final String GET_ALL_ASSIGNMENTS_BY_TEACHER_ID = "" +
            "SELECT ID, TEACHER_ID, LESSON_ID, TITLE, DESCRIPTION, START_DATE, END_DATE " +
            "FROM mydb.ASSIGNMENT " +
            "WHERE TEACHER_ID = ?";
    private static final String GET_ALL_ASSIGNMENTS_BY_LESSON_ID_AND_TEACHER_ID = "" +
            "SELECT ID, TEACHER_ID, LESSON_ID, TITLE, DESCRIPTION, START_DATE, END_DATE " +
            "FROM mydb.ASSIGNMENT " +
            "WHERE LESSON_ID = ? " +
            "AND TEACHER_ID = ?";
    private static final String DELETE_ASSIGNMENT_BY_ID = "" +
            "DELETE " +
            "FROM mydb.ASSIGNMENT " +
            "WHERE ID = ?";
    private static final String DELETE_ALL_ASSIGNMENTS = "" +
            "DELETE " +
            "FROM mydb.ASSIGNMENT";
    //private static final String ADD_ASSIGNMENT_FEEDBACK = "INSERT INTO mydb.ASSIGNMENT_FEEDBACK (TEACHER_ID, ASSIGNMENT_ID, COMMENT, CREATION_DATE) VALUES (?, ?, ?, ?)";
    //private static final String UPDATE_ASSIGNMENT_FEEDBACK = "UPDATE mydb.ASSIGNMENT_FEEDBACK SET COMMENT = ?, CHANGE_DATE = ? WHERE ID = ?";
    //private static final String DELETE_ASSIGNMENT_FEEDBACK = "DELETE FROM mydb.ASSIGNMENT_FEEDBACK WHERE ID = ?";
    //private static final String GET_ASSIGNMENT_FEEDBACK = "SELECT FD.COMMENT FROM mydb.ASSIGNMENT ASGN JOIN mydb.ASSIGNMENT_FEEDBACK FD ON ASGN.ID = FD.ASSIGNMENT_ID and ASGN.ID = ? ";
    /*
     * private static final String GET_ASSIGNMENT_COMMENT = "SELECT \r\n" +
     * "IFNULL(\r\n" + "    ( SELECT COMMENT FROM mydb.ASSIGNMENT_FEEDBACK \r\n" +
     * "        WHERE ASSIGNMENT_ID = ?\r\n" + "    ),\r\n" + "    ''\r\n" + ")";
     */

    @Override
    public Integer addAssignment(Assignment assignment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(ADD_ASSIGNMENT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, assignment.getTeacherId());
            ps.setInt(2, assignment.getLessonId());
            ps.setLong(3, assignment.getStartDate());
            ps.setLong(4, assignment.getEndDate());
            ps.setString(5, assignment.getTitle());
            ps.setString(6, assignment.getDescription());
            ps.setLong(7, System.currentTimeMillis());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public Assignment getAssignment(Integer id) {
        return jdbcTemplate.queryForObject(GET_ASSIGNMENT_BY_ID, new AssignmentMapper(), id);
    }

    @Override
    public List<Assignment> getAllAssignments() {
        return jdbcTemplate.query(GET_ALL_ASSIGNMENTS, new AssignmentMapper());
    }

    @Override
    public List<Assignment> getAssignmentsByTeacherId(Integer teacherId) {
        return jdbcTemplate.query(GET_ALL_ASSIGNMENTS_BY_TEACHER_ID, new AssignmentMapper(), teacherId);
    }

    @Override
    public Assignment getAssignmentByLessonIdAndTeacherId(Integer lessonId, Integer teacherId) {
        return jdbcTemplate.queryForObject(GET_ALL_ASSIGNMENTS_BY_LESSON_ID_AND_TEACHER_ID, new AssignmentMapper(), lessonId, teacherId);
    }

    @Override
    public Integer deleteAssignment(Integer id) {
        return jdbcTemplate.update(DELETE_ASSIGNMENT_BY_ID, id);
    }

    @Override
    public Integer deleteAllAssignments() {
        return jdbcTemplate.update(DELETE_ALL_ASSIGNMENTS);
    }

    /*
     * @Override public Integer addAssignmentFeedback(Integer teacherId, Integer
     * assignmentId, String comment) { return
     * jdbcTemplate.update(ADD_ASSIGNMENT_FEEDBACK, teacherId, assignmentId,
     * comment, System.currentTimeMillis()); }
     *
     * @Override public Integer updateAssignmentFeedback(Integer id, String comment)
     * { return jdbcTemplate.update(UPDATE_ASSIGNMENT_FEEDBACK, comment,
     * System.currentTimeMillis(), id); }
     *
     * @Override public Integer deleteAssignmentFeedback(Integer id) { return
     * jdbcTemplate.update(DELETE_ASSIGNMENT_FEEDBACK, id); }
     */

    /*
     * @Override public String getAssignmentFeedback(Integer id) { try { return
     * (String) jdbcTemplate.queryForObject(GET_ASSIGNMENT_FEEDBACK, new Object[] {
     * id }, String.class); } catch (EmptyResultDataAccessException ex) {
     *
     * return ""; } }
     *
     * @Override public String getAssignmentComment(Integer assignmentID) {
     * System.out.println("comment " +
     * jdbcTemplate.queryForObject(GET_ASSIGNMENT_COMMENT, String.class,
     * assignmentID )); String comment =
     * jdbcTemplate.queryForObject(GET_ASSIGNMENT_COMMENT, String.class,
     * assignmentID ); if (comment == null || comment == "") { return null; } return
     * comment; }
     */

    private static class AssignmentMapper implements RowMapper<Assignment> {

        @Override
        public Assignment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Assignment assignment = new Assignment();

            assignment.setId(rs.getInt("ID"));
            assignment.setTeacherId(rs.getInt("TEACHER_ID"));
            assignment.setLessonId(rs.getInt("LESSON_ID"));
            assignment.setStartDate(rs.getLong("START_DATE"));
            assignment.setEndDate(rs.getLong("END_DATE"));
            assignment.setTitle(rs.getString("TITLE"));
            assignment.setDescription(rs.getString("DESCRIPTION"));

            return assignment;
        }
    }

}
