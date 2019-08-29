package am.dproc.sms.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import am.dproc.sms.modules.GroupBean;

@Repository
public class GroupDAOImpl implements GroupDAO{
	@Autowired
	JdbcTemplate template;

	public static String CREATE_GROUP_SQL = "INSERT INTO GROUP(NAME) VALUES (?)";

	
	public int create(String name) {
		return template.update(CREATE_GROUP_SQL,name);
	}

}
