package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.root.AssignmentDAO;
import am.dproc.sms.models.Assignment;

@Repository
public class AssignmentDAODBImpl implements AssignmentDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String GET_ASSIGNMENT_BY_ID = "SELECT * FROM mydb.ASSIGNMENT WHERE ID = ?";
	private static final String GET_ALL_ASSIGNMENTS = "SELECT * FROM mydb.ASSIGNMENT";
	private static final String GET_ALL_ASSIGNMENTS_BY_TITLE = "SELECT * FROM mydb.ASSIGNMENT WHERE TITLE = ?";
	private static final String GET_ALL_ASSIGNMENTS_BY_TEACHER_ID = "SELECT * FROM mydb.ASSIGNMENT WHERE TEACHER_ID = ?";
	private static final String GET_ASSIGNMENT_FEEDBACK = "SELECT FD.COMMENT FROM mydb.ASSIGNMENT ASGN JOIN mydb.ASSIGNMENT_FEEDBACK FD ON ASGN.ID = FD.ASSIGNMENT_ID and ASGN.ID = ? ";
	private static final String DELETE_ASSIGNMENT_BY_ID = "DELETE FROM mydb.ASSIGNMENT WHERE ID = ?";
	private static final String DELETE_ALL_ASSIGMENTS = "DELETE FROM mydb.ASSIGNMENT";
	private static final String ADD_ASSIGNMENT = "INSERT INTO mydb.ASSIGNMENT (STARTED_DATE, DEADLINE, TITLE, DESCRIPTION, CREATION_DATE, TEACHER_ID) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String ADD_ASSIGNMENT_FEEDBACK = "INSERT INTO mydb.ASSIGNMENT_FEEDBACK (TEACHER_ID, ASSIGNMENT_ID, COMMENT, CREATION_DATE) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_ASSIGNMENT_FEEDBACK = "UPDATE mydb.ASSIGNMENT_FEEDBACK SET COMMENT = ?, CHANGE_DATE = ? WHERE ID = ?";
	private static final String DELETE_ASSIGNMENT_FEEDBACK = "DELETE FROM mydb.ASSIGNMENT_FEEDBACK WHERE ID = ?";

	@Override
	public Assignment getAssignment(Integer id) {
		// TODO Auto-generated method stub
		return jdbctemplate.queryForObject(GET_ASSIGNMENT_BY_ID, new AssignmentMapper(), id);
	}

	@Override
	public List<Assignment> getAllAssignments() {
		// TODO Auto-generated method stub
		return jdbctemplate.query(GET_ALL_ASSIGNMENTS, new AssignmentMapper());
	}

	@Override
	public List<Assignment> getAllAssignments(String title) {
		// TODO Auto-generated method stub
		return jdbctemplate.query(GET_ALL_ASSIGNMENTS_BY_TITLE, new AssignmentMapper(), title);
	}

	@Override
	public List<Assignment> getAssignmentsByTeacherId(Integer teacherId) {
		return jdbctemplate.query(GET_ALL_ASSIGNMENTS_BY_TEACHER_ID, new AssignmentMapper(), teacherId);
	}

	@Override
	public String getAssignmentFeedback(Integer id) {
		try {
			return (String) jdbctemplate.queryForObject(GET_ASSIGNMENT_FEEDBACK, new Object[] { id }, String.class);
		} catch (EmptyResultDataAccessException ex) {

			return "";
		}
	}

	@Override
	public Integer deleteAssignment(Integer id) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(DELETE_ASSIGNMENT_BY_ID, id);
	}

	@Override
	public Integer deleteAllAssignments() {
		// TODO Auto-generated method stub
		return jdbctemplate.update(DELETE_ALL_ASSIGMENTS);
	}

	@Override
	public Integer addAssignment(Assignment asi) {
		// TODO Auto-generated method stub
		return jdbctemplate.update(ADD_ASSIGNMENT, new Object[] { asi.getStartedDate(), asi.getDeadLine(),
				asi.getTeacherIdGivenAsi(), asi.getTitle(), asi.getDescription(), System.currentTimeMillis() });
	}

	@Override
	public Integer addAssignmentFeedback(Integer teacherId, Integer assignmentId, String comment) {
		return jdbctemplate.update(ADD_ASSIGNMENT_FEEDBACK, teacherId, assignmentId, comment,
				System.currentTimeMillis());
	}

	@Override
	public Integer updateAssignmentFeedback(Integer id, String comment) {
		return jdbctemplate.update(UPDATE_ASSIGNMENT_FEEDBACK, comment, System.currentTimeMillis(), id);
	}

	@Override
	public Integer deleteAssignmentFeedback(Integer id) {
		return jdbctemplate.update(DELETE_ASSIGNMENT_FEEDBACK, id);
	}

	private static class AssignmentMapper implements RowMapper<Assignment> {

		@Override
		public Assignment mapRow(ResultSet rs, int rowNum) throws SQLException {
			Assignment assignment = new Assignment();

			assignment.setId(rs.getInt("ID"));
			assignment.setStartedDate(rs.getLong("START_DATE"));
			assignment.setDeadLine(rs.getLong("DEADLINE"));
			assignment.setTitle(rs.getString("TITLE"));
			assignment.setDescription(rs.getString("DESCRIPTION"));
			// assignment.setCreationDate(rs.getInt("CREATION_DATE"));
			// assignment.setChangeDate(rs.getInt("CHANGE_DATE"));
			assignment.setTeacherIdGivenAsi(rs.getInt("TEACHER_ID"));

			return assignment;
		}
	}

}
