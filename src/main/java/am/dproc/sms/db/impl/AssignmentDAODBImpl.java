package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	private static final String GET_ALL_ASSIGNMENTS_BY_TEACHER_ID = "SELECT * FROM mydb.ASSIGNMENT WHERE TEACHER_ID_GIVEN_ASI = ?";
	private static final String DELETE_ASSIGNMENT_BY_ID = "DELETE FROM mydb.ASSIGNMENT WHERE ID = ?";
	private static final String DELETE_ALL_ASSIGMENTS = "DELETE FROM mydb.ASSIGNMENT";
	private static final String ADD_ASSIGNMENT = "INSERT INTO mydb.ASSIGNMENT (STARTED_DATE, DEADLINE, TITLE, DESCRIPTION, CREATION_DATE, CHANGE_DATE,TEACHER_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";

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
		// TODO Auto-generated method stub
		return jdbctemplate.query(GET_ALL_ASSIGNMENTS_BY_TEACHER_ID, new AssignmentMapper(), teacherId);
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
		return jdbctemplate.update(ADD_ASSIGNMENT,
				new Object[] { asi.getStartedDate(), asi.getDeadLine(), asi.getTeacherIdGivenAsi(), asi.getTitle(),
						asi.getDescription(), asi.getCreationDate(), asi.getChangeDate() });
	}

	private static class AssignmentMapper implements RowMapper<Assignment> {

		@Override
		public Assignment mapRow(ResultSet rs, int rowNum) throws SQLException {
			Assignment assignment = new Assignment();

			assignment.setId(rs.getInt("ID"));
			assignment.setStartedDate(rs.getInt("STARTED_DATE"));
			assignment.setDeadLine(rs.getInt("DEADLINE"));
			assignment.setTitle(rs.getString("TITLE"));
			assignment.setDescription(rs.getString("DESCRIPTION"));
			assignment.setCreationDate(rs.getInt("CREATION_DATE"));
			assignment.setChangeDate(rs.getInt("CHANGE_DATE"));
			assignment.setTeacherIdGivenAsi(rs.getInt("TEACHER_ID"));

			return assignment;
		}

	}

}
