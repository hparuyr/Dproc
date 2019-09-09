package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.StudentCommentDAO;
import am.dproc.sms.models.StudentComment;

@Repository
public class StudentCommentDAODBImpl implements StudentCommentDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String GET_COMMENT_BY_ID = "SELECT * FROM mydb.STUDENT_COMMENT WHERE ID = ?";
	private static final String GET_COMMENT_BY_TOPIC_ID = "SELECT * FROM mydb.STUDENT_COMMENT WHERE TOPIC_ID = ?";
	private static final String GET_COMMENT_ID = "SELECT ID FROM mydb.STUDENT_COMMENT WHERE STUDENT_ID = ? AND CREATION_DATE = ?";
	private static final String DELETE_COMMENT_BY_ID = "DELETE FROM mydb.STUDENT_COMMENT WHERE ID = ?";
	private static final String ADD_COMMENT = "INSERT INTO mydb.STUDENT_COMMENT (COMMENT, CREATION_DATE, CHANGE_DATE, TOPIC_ID, STUDENT_ID) VALUES(?, ?, ?, ?, ?)";
	private static final String EDIT_COMMENT = "UPDATE mydb.STUDENT_COMMENT SET COMMENT = ?, CHANGE_DATE = ? WHERE ID = ?";

	@Override
	public StudentComment getComment(Integer id) {
		return jdbctemplate.queryForObject(GET_COMMENT_BY_ID, new StudentCommentMapper(), id);
	}

	@Override
	public List<StudentComment> getCommentsOfTopic(Integer topicID) {
		return jdbctemplate.query(GET_COMMENT_BY_TOPIC_ID, new StudentCommentMapper(), topicID);
	}

	@Override
	public Integer getCommentID(Integer studentID, Long currentTimeMillis) {
		return jdbctemplate.queryForObject(GET_COMMENT_ID, Integer.class,
				new Object[] { studentID, currentTimeMillis });
	}

	@Override
	public Integer deleteComment(Integer id) {
		return jdbctemplate.update(DELETE_COMMENT_BY_ID, id);
	}

	@Override
	public Integer addComment(StudentComment comment) {
		Long currentTimeMillis = new java.util.Date().getTime();
		jdbctemplate.update(ADD_COMMENT, new Object[] { comment.getComment(), currentTimeMillis, currentTimeMillis,
				comment.getTopicID(), comment.getStudentID() });
		return getCommentID(comment.getStudentID(), currentTimeMillis);
	}

	@Override
	public Integer editComment(Integer id, String comment) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(EDIT_COMMENT, new Object[] { comment, currentTimeMillis, id });
	}

	private static class StudentCommentMapper implements RowMapper<StudentComment> {
		@Override
		public StudentComment mapRow(ResultSet rs, int rowNum) throws SQLException {
			StudentComment student = new StudentComment();
			student.setId(rs.getInt("id"));
			student.setComment(rs.getString("comment"));
			student.setCreationDateMillis(rs.getLong("creation_date"));
			student.setTopicID(rs.getInt("topic_id"));
			student.setStudentID(rs.getInt("student_id"));
			return student;
		}

	}

}
