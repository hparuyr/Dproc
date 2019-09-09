package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.root.GroupDAO;
import am.dproc.sms.models.Group;

@Repository
public class GroupDAOImpl implements GroupDAO {
	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String ADD_GROUP = "INSERT INTO mydb.GROUP (NAME,CREATION_DATE,CHANGE_DATE, SCHOOL_ID) VALUES (?, ?, ?, ?)";
	private static final String GET_GROUP_BY_ID= "SELECT * FROM mydb.GROUP WHERE ID = ?";
	private static final String GET_GROUPS = "SELECT * FROM mydb.GROUP";
	private static final String GET_GROUPS_BY_SCHOOL_ID = "SELECT * FROM mydb.GROUP WHERE SCHOOL_ID = ?";
	private static final String UPDATE_GROUP_NAME = "UPDATE mydb.GROUP SET NAME = ?, CHANGE_DATE = ? WHERE ID = ?";
	private static final String DELETE_GROUP_BY_ID = "DELETE FROM mydb.GROUP WHERE ID = ?";
	@Override
	public Integer addGroup(Group group) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(ADD_GROUP, new Object[] { group.getName(),currentTimeMillis, currentTimeMillis , group.getSchoolId()});
		
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
	public List<Group> getGroupsBySchoolId(Integer schoolId){
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
			group.setCreationDate(new Date(rs.getLong("creation_date")));
			group.setSchoolId(rs.getInt("schoolId "));
			return group;
		}

	}
}
