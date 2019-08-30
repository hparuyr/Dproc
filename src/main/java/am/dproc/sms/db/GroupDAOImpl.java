package am.dproc.sms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.modules.GroupBean;

@Repository
public class GroupDAOImpl implements GroupDAO{
	@Autowired
	JdbcTemplate template;

	public static String CREATE_GROUP_SQL = "INSERT INTO `GROUP`(NAME, SCHOOL_ID) VALUES (?, ?)";
	public static String GET_GROUP_SQL = "SELECT * FROM `GROUP` WHERE ID = ?";
	public static String GET_GROUPS_SQL = "SELECT * FROM `GROUP`";

	
	public int create(String name, int schoolId) {
		return template.update(CREATE_GROUP_SQL,name, schoolId);
	}

	public GroupBean get(int id) {
		GroupBean group = template.queryForObject(GET_GROUP_SQL, new Object[] {id}, new GroupMapper() );
		return group;
	}
	
	public List<GroupBean> getAll(){
		List<GroupBean> groups = template.query(GET_GROUPS_SQL, new GroupMapper());
		return groups;
	}
	
	private static class GroupMapper implements RowMapper<GroupBean> {
		@Override
		public GroupBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			GroupBean group = new GroupBean();
			group.setId(rs.getInt("ID"));
			group.setName(rs.getString("NAME"));
			group.setSchoolId(rs.getInt("SCHOOL_ID"));
			return group;
		}
		
	}
}
