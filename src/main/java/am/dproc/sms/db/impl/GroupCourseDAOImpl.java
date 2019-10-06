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

import am.dproc.sms.db.interfaces.GroupCourseDAO;
import am.dproc.sms.models.GroupCourse;

@Repository
public class GroupCourseDAOImpl implements GroupCourseDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String CREATE_SQL = "" +
            "INSERT " +
            "INTO GROUP_COURSE(GROUP_ID, COURSE_ID, TEACHER_ID, START_DATE, FINISHED, CREATION_DATE) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_BY_ID_SQL = "" +
            "SELECT ID, GROUP_ID, COURSE_ID, TEACHER_ID, START_DATE, FINISHED, CREATION_DATE " +
            "FROM GROUP_COURSE " +
            "WHERE ID = ?";
    private static final String GET_BY_GROUP_AND_COURSE_SQL = "" +
            "SELECT ID, GROUP_ID, COURSE_ID, TEACHER_ID, START_DATE, FINISHED, CREATION_DATE " +
            "FROM GROUP_COURSE " +
            "WHERE GROUP_ID=? " +
            "AND COURSE_ID = ?";
    private static final String GET_BY_GROUP_SQL = "" +
            "SELECT ID, GROUP_ID, COURSE_ID, TEACHER_ID, START_DATE, FINISHED, CREATION_DATE* " +
            "FROM GROUP_COURSE " +
            "WHERE GROUP_ID = ?";
    private static final String GET_BY_COURSE_SQL = "" +
            "SELECT ID, GROUP_ID, COURSE_ID, TEACHER_ID, START_DATE, FINISHED, CREATION_DATE " +
            "FROM GROUP_COURSE " +
            "WHERE COURSE_ID = ?";
    private static final String GET_ALL_SQL = "" +
            "SELECT ID, GROUP_ID, COURSE_ID, TEACHER_ID, START_DATE, FINISHED, CREATION_DATE " +
            "FROM GROUP_COURSE";
    private static final String GET_BY_TEACHER_ID_SQL = "" +
            "SELECT ID, GROUP_ID, COURSE_ID, TEACHER_ID, START_DATE, FINISHED, CREATION_DATE " +
            "FROM GROUP_COURSE " +
            "WHERE TEACHER_ID = ?";
    private static final String UPDATE_SQL = "" +
            "UPDATE GROUP_COURSE " +
            "SET GROUP_ID = ?, COURSE_ID = ?, TEACHER_ID = ?, START_DATE = ?, FINISHED = ?, CHANGE_DATE = ? " +
            "WHERE ID = ?";
    private static final String DELETE_BY_GROUP_SQL = "" +
            "DELETE " +
            "FROM GROUP_COURSE " +
            "WHERE GROUP_ID = ?";
    private static final String DELETE_BY_COURSE_SQL = "" +
            "DELETE " +
            "FROM GROUP_COURSE " +
            "WHERE COURSE_ID = ?";
    private static final String DELETE_ALL_SQL = "" +
            "DELETE " +
            "FROM GROUP_COURSE";

    @Override
    public Integer create(GroupCourse groupCourse) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        long currentTimeStamp = System.currentTimeMillis();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, groupCourse.getGroupId());
            ps.setInt(2, groupCourse.getCourseId());
            ps.setInt(3, groupCourse.getTeacherId());
            ps.setLong(4, groupCourse.getStartDate());
            ps.setBoolean(5, groupCourse.getFinished());
            ps.setLong(6, currentTimeStamp);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public GroupCourse getById(Integer id) {
        return jdbcTemplate.queryForObject(GET_BY_ID_SQL, new Object[]{id}, new GroupCourseMapper());
    }

    @Override
    public GroupCourse getByGroupAndCourse(Integer groupId, Integer courseId) {
        return jdbcTemplate.queryForObject(GET_BY_GROUP_AND_COURSE_SQL, new Object[]{groupId, courseId},
                new GroupCourseMapper());
    }

    @Override
    public List<GroupCourse> getByGroupID(Integer groupId) {
        return jdbcTemplate.query(GET_BY_GROUP_SQL, new Object[]{groupId}, new GroupCourseMapper());
    }

    @Override
    public List<GroupCourse> getByCourseID(Integer courseId) {
        return jdbcTemplate.query(GET_BY_COURSE_SQL, new Object[]{courseId}, new GroupCourseMapper());
    }

    @Override
    public List<GroupCourse> getAll() {
        return jdbcTemplate.query(GET_ALL_SQL, new GroupCourseMapper());
    }

    @Override
    public List<GroupCourse> getByTeacherID(Integer id) {
        return jdbcTemplate.query(GET_BY_TEACHER_ID_SQL, new GroupCourseMapper(), id);
    }

    @Override
    public Integer update(GroupCourse groupCourse) {
        return jdbcTemplate.update(UPDATE_SQL, groupCourse.getGroupId(), groupCourse.getCourseId(),
                groupCourse.getTeacherId(), groupCourse.getStartDate(), groupCourse.getFinished(),
                System.currentTimeMillis(), groupCourse.getId());
    }

    @Override
    public Integer deleteByGroupID(Integer groupId) {
        return jdbcTemplate.update(DELETE_BY_GROUP_SQL, groupId);
    }

    @Override
    public Integer deleteByCourseID(Integer courseId) {
        return jdbcTemplate.update(DELETE_BY_COURSE_SQL, courseId);
    }

    @Override
    public Integer deleteAll() {
        return jdbcTemplate.update(DELETE_ALL_SQL);
    }

    private static class GroupCourseMapper implements RowMapper<GroupCourse> {
        @Override
        public GroupCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
            GroupCourse groupCourse = new GroupCourse();

            groupCourse.setId(rs.getInt("ID"));
            groupCourse.setGroupId(rs.getInt("GROUP_ID"));
            groupCourse.setCourseId(rs.getInt("COURSE_ID"));
            groupCourse.setTeacherId(rs.getInt("TEACHER_ID"));
            groupCourse.setStartDate(rs.getLong("START_DATE"));
            groupCourse.setFinished(rs.getBoolean("FINISHED"));

            return groupCourse;
        }
    }
}
