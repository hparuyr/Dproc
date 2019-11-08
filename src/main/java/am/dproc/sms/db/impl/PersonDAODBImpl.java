package am.dproc.sms.db.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.PersonDAO;

@Repository
public class PersonDAODBImpl implements PersonDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String GET_PERSON_BY_EMAIL = "SELECT `ID` FROM (SELECT `USER`.`ID`, `USER`.`EMAIL` FROM `USER` UNION SELECT `ADMIN`.`ID`, `ADMIN`.`EMAIL` FROM `ADMIN`)"
														+ " as `PERSON` where `EMAIL` = ?";

	@Override
	public Integer getPersonByEmail(String email) {
		try {
			return jdbctemplate.queryForObject(GET_PERSON_BY_EMAIL, Integer.class, email);
		} catch (DataAccessException e) {
			return 0;
		}
	}
}
