package am.dproc.sms.db.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.GroupDAO;
import am.dproc.sms.models.Group;

@Repository
public class GroupDAODBImpl implements GroupDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String ADD_GROUP = "INSERT INTO mydb.GROUP (NAME, SCHOOL_ID,CREATION_DATE) VALUES (?, ?, ?)";
	private static final String GET_GROUP_BY_ID = "SELECT * FROM mydb.GROUP WHERE ID = ?";
	private static final String GET_GROUPS_BY_STUDENT_ID = "SELECT `GROUP`.`ID`, `GROUP`.`SCHOOL_ID`, `GROUP`.`NAME` FROM mydb.`STUDENT_GROUP`"
														+ " join mydb.`GROUP` on `STUDENT_GROUP`.`GROUP_ID`= `GROUP`.`ID`  WHERE `STUDENT_ID`=?;";
	private static final String GET_GROUPS = "SELECT * FROM mydb.GROUP";
	private static final String GET_GROUPS_BY_SCHOOL_ID = "SELECT * FROM mydb.GROUP WHERE SCHOOL_ID = ?";
	private static final String UPDATE_GROUP_NAME = "UPDATE mydb.GROUP SET NAME = ?, CHANGE_DATE = ? WHERE ID = ?";
	private static final String DELETE_GROUP_BY_ID = "DELETE FROM mydb.GROUP WHERE ID = ?";

	@Override
	public Integer addGroup(Group group) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(ADD_GROUP,
				group.getName(), group.getSchoolId(), currentTimeMillis);
	}

	@Override
	public List<Integer> addGroups(List<Group> groups) {
		Long currentTimeMillis = System.currentTimeMillis();
		return jdbctemplate.execute((PreparedStatementCreator) con -> {
			PreparedStatement ps = con.prepareStatement(ADD_GROUP, Statement.RETURN_GENERATED_KEYS);
			for (Group group : groups) {
				ps.setString(1, group.getName());
				ps.setInt(2, group.getSchoolId());
				ps.setLong(3, currentTimeMillis);
				ps.addBatch();
			}
			ps.executeBatch();
			return ps;
		}, ps -> {
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
		return jdbctemplate.queryForObject(GET_GROUP_BY_ID, new Object[] { id }, new GroupMapper());
	}

	@Override
	public List<Group> getGroups() {
		return jdbctemplate.query(GET_GROUPS, new GroupMapper());
	}

	@Override
	public List<Group> getGroupsBySchoolId(Integer schoolId) {
		return jdbctemplate.query(GET_GROUPS_BY_SCHOOL_ID, new GroupMapper(), schoolId);
	}

	@Override
	public List<Group> getGroupsByStudentId(Integer studentId) {
		return jdbctemplate.query(GET_GROUPS_BY_STUDENT_ID, new GroupMapper(), studentId);
	}
	
	@Override
	public Integer updateGroupName(Integer id, String name) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_GROUP_NAME, name, currentTimeMillis, id);
	}

	@Override
	public Integer deleteGroup(Integer id) {
		return jdbctemplate.update(DELETE_GROUP_BY_ID, id);
	}

	private static class GroupMapper implements RowMapper<Group> {
		@Override
		public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
			Group group = new Group();
			group.setId(rs.getInt("ID"));
			group.setName(rs.getString("NAME"));
			group.setSchoolId(rs.getInt("SCHOOL_ID"));
			return group;
		}

	}
}
