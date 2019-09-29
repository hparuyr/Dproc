package am.dproc.sms.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.GroupDAO;
import am.dproc.sms.models.Group;

@Repository
public class GroupDAODBImpl implements GroupDAO {
	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String ADD_GROUP = "INSERT INTO mydb.GROUP (NAME,CREATION_DATE,CHANGE_DATE, SCHOOL_ID) VALUES (?, ?, ?, ?)";
	private static final String GET_GROUP_BY_ID = "SELECT * FROM mydb.GROUP WHERE ID = ?";
	private static final String GET_GROUPS = "SELECT * FROM mydb.GROUP";
	private static final String GET_GROUPS_BY_SCHOOL_ID = "SELECT * FROM mydb.GROUP WHERE SCHOOL_ID = ?";
	private static final String UPDATE_GROUP_NAME = "UPDATE mydb.GROUP SET NAME = ?, CHANGE_DATE = ? WHERE ID = ?";
	private static final String DELETE_GROUP_BY_ID = "DELETE FROM mydb.GROUP WHERE ID = ?";

	@Override
	public Integer addGroup(Group group) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(ADD_GROUP,
				new Object[] { group.getName(), currentTimeMillis, currentTimeMillis, group.getSchoolId() });
	}

	@Override
	public Group getGroup(Integer id) {
		return jdbctemplate.queryForObject(GET_GROUP_BY_ID, new Object[] { id }, new GroupMapper());
	}
	
	@Override
	public List<Integer> addGroups(List<Group> groups) {
		Long currentTimeMillis = System.currentTimeMillis();
		return jdbctemplate.execute(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(ADD_GROUP, Statement.RETURN_GENERATED_KEYS);
				for (Group group : groups) {
					ps.setString(1, group.getName());
					ps.setInt(2, group.getSchoolId());
					ps.setLong(3, currentTimeMillis);
					ps.setLong(4, currentTimeMillis);
					ps.addBatch();
				}
				ps.executeBatch();
				return ps;
			}
		}, new PreparedStatementCallback<List<Integer>>() {
			@Override
			public List<Integer> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ResultSet rs = ps.getGeneratedKeys();
				List<Integer> ids = new ArrayList<>();
				while (rs.next()) {
					ids.add(rs.getInt(1));
				}
				return ids;
			}
		});
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
	public Integer updateGroupName(Integer id, String name) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_GROUP_NAME, new Object[] { name, currentTimeMillis, id });
	}

	@Override
	public Integer deleteGroup(Integer id) {
		return jdbctemplate.update(DELETE_GROUP_BY_ID, id);
	}

	private static class GroupMapper implements RowMapper<Group> {
		@Override
		public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
			Group group = new Group();
			group.setId(rs.getInt("id"));
			group.setName(rs.getString("name"));
			group.setCreationDate(rs.getLong("creation_date"));
			group.setSchoolId(rs.getInt("school_id"));
			return group;
		}

	}
}
