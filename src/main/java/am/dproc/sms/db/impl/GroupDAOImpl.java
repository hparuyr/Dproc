package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.GroupDAO;
import am.dproc.sms.models.Group;

@Repository
public class GroupDAOImpl implements GroupDAO {
	@Autowired
	JdbcTemplate template;

	public static String CREATE_GROUP_SQL = "INSERT INTO `GROUP`(NAME, SCHOOL_ID) VALUES (?, ?)";
	public static String GET_GROUP_SQL = "SELECT * FROM `GROUP` WHERE ID = ?";
	public static String GET_GROUPS_SQL = "SELECT * FROM `GROUP`";

	public int create(String name, int schoolId) {
		return template.update(CREATE_GROUP_SQL, name, schoolId);
	}

	public Group get(int id) {
		Group group = template.queryForObject(GET_GROUP_SQL, new Object[] { id }, new GroupMapper());
		return group;
	}

	public List<Group> getAll() {
		List<Group> groups = template.query(GET_GROUPS_SQL, new GroupMapper());
		return groups;
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
