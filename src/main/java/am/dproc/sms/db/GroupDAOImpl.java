package am.dproc.sms.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import am.dproc.sms.modules.GroupBean;

@Repository
public class GroupDAOImpl implements GroupDAO{
	@Autowired
	JdbcTemplate template;

	public static String CREATE_GROUP_SQL = "INSERT INTO `GROUP`(NAME, SCHOOL_ID) VALUES (?, ?)";
	public static String GET_GROUP_SQL = "SELECT * FROM `GROUP`";

	
	public int create(String name, int schoolId) {
		return template.update(CREATE_GROUP_SQL,name, schoolId);
	}

//	public GroupBean get(int id) {
//		template.query(GET_GROUP_SQL, )
//	}
}
