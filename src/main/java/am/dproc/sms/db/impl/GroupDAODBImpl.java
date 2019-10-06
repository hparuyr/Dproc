package am.dproc.sms.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.GroupDAO;
import am.dproc.sms.models.Group;

@Repository
public class GroupDAODBImpl implements GroupDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String ADD_GROUP = "" +
            "INSERT " +
            "INTO mydb.GROUP (SCHOOL_ID, NAME, CREATION_DATE) " +
            "VALUES (?, ?, ?)";
    private static final String GET_GROUP_BY_ID = "" +
            "SELECT ID, SCHOOL_ID, NAME " +
            "FROM mydb.GROUP " +
            "WHERE ID = ?";
    private static final String GET_GROUPS = "" +
            "SELECT ID, SCHOOL_ID, NAME " +
            "FROM mydb.GROUP";
    private static final String GET_GROUPS_BY_SCHOOL_ID = "" +
            "SELECT ID, SCHOOL_ID, NAME " +
            "FROM mydb.GROUP " +
            "WHERE SCHOOL_ID = ?";
    private static final String UPDATE_GROUP_NAME = "" +
            "UPDATE mydb.GROUP " +
            "SET NAME = ?, CHANGE_DATE = ? " +
            "WHERE ID = ?";
    private static final String DELETE_GROUP_BY_ID = "" +
            "DELETE " +
            "FROM mydb.GROUP " +
            "WHERE ID = ?";

    @Override
    public Integer addGroup(Group group) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(ADD_GROUP, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, group.getSchoolId());
            ps.setString(2, group.getName());
            ps.setLong(3, System.currentTimeMillis());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public List<Integer> addGroups(List<Group> groups) {
        return jdbcTemplate.execute(con -> {
            PreparedStatement ps = con.prepareStatement(ADD_GROUP, Statement.RETURN_GENERATED_KEYS);
            for (Group group : groups) {
                ps.setString(1, group.getName());
                ps.setInt(2, group.getSchoolId());
                ps.setLong(3, System.currentTimeMillis());
                ps.addBatch();
            }
            ps.executeBatch();
            return ps;
        }, (PreparedStatementCallback<List<Integer>>) ps -> {
            ResultSet rs = ps.getGeneratedKeys();
            List<Integer> ids = new ArrayList<>();
            while (rs.next()) {
                ids.add(rs.getInt(1));
            }
            return ids;
        });
    }

    @Override
    public Group getGroup(Integer id) {
        return jdbcTemplate.queryForObject(GET_GROUP_BY_ID, new Object[]{id}, new GroupMapper());
    }

    @Override
    public List<Group> getGroups() {
        return jdbcTemplate.query(GET_GROUPS, new GroupMapper());
    }

    @Override
    public List<Group> getGroupsBySchoolId(Integer schoolId) {
        return jdbcTemplate.query(GET_GROUPS_BY_SCHOOL_ID, new GroupMapper(), schoolId);
    }

    @Override
    public Integer updateGroupName(Integer id, String name) {
        return jdbcTemplate.update(UPDATE_GROUP_NAME, name, System.currentTimeMillis(), id);
    }

    @Override
    public Integer deleteGroup(Integer id) {
        return jdbcTemplate.update(DELETE_GROUP_BY_ID, id);
    }

    private static class GroupMapper implements RowMapper<Group> {
        @Override
        public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
            Group group = new Group();

            group.setId(rs.getInt("ID"));
            group.setSchoolId(rs.getInt("SCHOOL_ID"));
            group.setName(rs.getString("NAME"));

            return group;
        }

    }
}
