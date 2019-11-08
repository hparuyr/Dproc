package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.AssignmentCompletedDAO;
import am.dproc.sms.models.AssignmentCompleted;
import am.dproc.sms.models.Student;

@Repository
public class AssignmentCompletedDAOImpl implements AssignmentCompletedDAO {

	@Autowired
	private JdbcTemplate jdbctemplate;

	private static final String GET_ASSIGNMENT_COMPL_BY_ID = "SELECT ID, STUDENT_ID, ASSIGNMENT_ID, CONTENT_URL"
			+ " FROM mydb.ASSIGNMENT_COMPLETED"
			+ " WHERE ID = ?";
	private static final String GET_ALL_ASSIGNMENT_COMPL = "SELECT ID, STUDENT_ID, ASSIGNMENT_ID, CONTENT_URL"
			+ " FROM mydb.ASSIGNMENT_COMPLETED";
	private static final String GET_ASSIGNMENT_COMPL_BY_ASSIGNMENT_ID = "SELECT ID, STUDENT_ID, ASSIGNMENT_ID, CONTENT_URL"
			+ " FROM mydb.ASSIGNMENT_COMPLETED"
			+ " WHERE ASSIGNMENT_ID = ?";
	private static final String GET_STUDENTS_DONE_ASSIGNMENT_WITH_ID = "SELECT U.ID, U.FIRSTNAME, U.LASTNAME, U.EMAIL, U.STATUS, U.TYPE\r\n" +
			" FROM ASSIGNMENT_COMPLETED AC\r\n" +
			"    JOIN `USER` U ON AC.STUDENT_ID = U.ID\r\n" +
			" WHERE U.TYPE = 1\r\n" +
			"   AND ASSIGNMENT_ID = ?\r\n" +
			"   AND CONTENT_URL IS NOT NULL";
	private static final String GET_STUDENTS_NOT_DONE_ASSIGNMENT_WITH_ID = "SELECT U.ID, U.FIRSTNAME, U.LASTNAME, U.EMAIL, U.STATUS, U.TYPE\r\n" +
			" FROM ASSIGNMENT_COMPLETED AC\r\n" +
			"    JOIN `USER` U ON AC.STUDENT_ID = U.ID\r\n" +
			" WHERE U.TYPE = 1\r\n" +
			"   AND ASSIGNMENT_ID = ?\r\n" +
			"   AND CONTENT_URL IS NULL";
	private static final String UPDATE_ASSIGNMENT_COMPL_CONTENT_URL = "UPDATE mydb.ASSIGNMENT_COMPLETED"
			+ " SET CONTENT_URL = ?, CHANGE_DATE = ?"
			+ " WHERE ID = ?";

	@Override
	public AssignmentCompleted getAssignmentCompleted(Integer assignmentCompletedId) {
		return jdbctemplate.queryForObject(GET_ASSIGNMENT_COMPL_BY_ID, new AssignmentCompletedMapper(), assignmentCompletedId);
	}

	@Override
	public List<AssignmentCompleted> getAllAssignmentsCompleted() {
		return jdbctemplate.query(GET_ALL_ASSIGNMENT_COMPL, new AssignmentCompletedMapper());
	}

	@Override
	public List<AssignmentCompleted> getAssignmentsCompletedByAssignmentId(Integer assignmentId) {
		return jdbctemplate.query(GET_ASSIGNMENT_COMPL_BY_ASSIGNMENT_ID, new AssignmentCompletedMapper(), assignmentId);
	}

	@Override
	public List<Student> getStudentsDoneAssignmentWithId(Integer assignmentId) {
		return jdbctemplate.query(GET_STUDENTS_DONE_ASSIGNMENT_WITH_ID, new StudentMapper(), assignmentId);
	}

	@Override
	public List<Student> getStudentsNotDoneAssignmentWithId(Integer assignmentId) {
		return jdbctemplate.query(GET_STUDENTS_NOT_DONE_ASSIGNMENT_WITH_ID, new StudentMapper(), assignmentId);
	}

	@Override
	public Integer updateAssignmentCompletedContentURL(Integer assignmentCompletedId, String contentURL) {
		return jdbctemplate.update(UPDATE_ASSIGNMENT_COMPL_CONTENT_URL, contentURL, System.currentTimeMillis(), assignmentCompletedId);
	}

	private static class AssignmentCompletedMapper implements RowMapper<AssignmentCompleted> {

		@Override
		public AssignmentCompleted mapRow(ResultSet rs, int rowNum) throws SQLException {
			AssignmentCompleted assignmentCompleted = new AssignmentCompleted();
			assignmentCompleted.setId(rs.getInt("ID"));
			assignmentCompleted.setStudentId(rs.getInt("STUDENT_ID"));
			assignmentCompleted.setAssignmentId(rs.getInt("ASSIGNMENT_ID"));
			assignmentCompleted.setContentURL(rs.getString("CONTENT_URL"));
			return assignmentCompleted;
		}
	}

	private static class StudentMapper implements RowMapper<Student> {
		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student student = new Student();
			student.setId(rs.getInt("ID"));
			student.setFirstName(rs.getString("FIRSTNAME"));
			student.setLastName(rs.getString("LASTNAME"));
			student.setEmail(rs.getString("EMAIL"));
			student.setStatus(rs.getInt("STATUS"));
			//student.setType(rs.getInt("TYPE")); ?????
			return student;
		}
	}
}
