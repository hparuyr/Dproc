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

import am.dproc.sms.db.interfaces.StudentCommentDAO;
import am.dproc.sms.models.StudentComment;

@Repository
public class StudentCommentDAODBImpl implements StudentCommentDAO {

    @Autowired
    JdbcTemplate jdbctemplate;

    private static final String ADD_COMMENT = ""
            + "INSERT "
            + "INTO mydb.STUDENT_COMMENT (TOPIC_ID, STUDENT_ID, COMMENT, CREATION_DATE) "
            + "VALUES(?, ?, ?, ?)";
    private static final String GET_COMMENT_BY_ID = ""
            + "SELECT ID, TOPIC_ID, STUDENT_ID, COMMENT "
            + "FROM mydb.STUDENT_COMMENT "
            + "WHERE ID = ?";
    private static final String GET_COMMENT_BY_TOPIC_ID = ""
            + "SELECT ID, TOPIC_ID, STUDENT_ID, COMMENT "
            + "FROM mydb.STUDENT_COMMENT "
            + "WHERE TOPIC_ID = ?";
    private static final String EDIT_COMMENT = ""
            + "UPDATE mydb.STUDENT_COMMENT "
            + "SET COMMENT = ?, CHANGE_DATE = ? "
            + "WHERE ID = ?";
    private static final String DELETE_COMMENT_BY_ID = ""
            + "DELETE "
            + "FROM mydb.STUDENT_COMMENT "
            + "WHERE ID = ?";

    @Override
    public Integer addComment(StudentComment comment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbctemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(ADD_COMMENT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, comment.getTopicID());
            ps.setInt(2, comment.getStudentID());
            ps.setString(3, comment.getComment());
            ps.setLong(4, System.currentTimeMillis());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public StudentComment getComment(Integer id) {
        return jdbctemplate.queryForObject(GET_COMMENT_BY_ID, new StudentCommentMapper(), id);
    }

    @Override
    public List<StudentComment> getCommentsOfTopic(Integer topicID) {
        return jdbctemplate.query(GET_COMMENT_BY_TOPIC_ID, new StudentCommentMapper(), topicID);
    }

    @Override
    public Integer editComment(Integer id, String comment) {
        return jdbctemplate.update(EDIT_COMMENT, comment, System.currentTimeMillis(), id);
    }

    @Override
    public Integer deleteComment(Integer id) {
        return jdbctemplate.update(DELETE_COMMENT_BY_ID, id);
    }

    private static class StudentCommentMapper implements RowMapper<StudentComment> {
        @Override
        public StudentComment mapRow(ResultSet rs, int rowNum) throws SQLException {
            StudentComment student = new StudentComment();

            student.setId(rs.getInt("ID"));
            student.setTopicID(rs.getInt("TOPIC_ID"));
            student.setStudentID(rs.getInt("STUDENT_ID"));
            student.setComment(rs.getString("COMMENT"));

            return student;
        }

    }

}
