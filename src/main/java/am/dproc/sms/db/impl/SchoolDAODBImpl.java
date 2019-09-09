package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.SchoolDAO;
import am.dproc.sms.models.School;

@Repository
public class SchoolDAODBImpl implements SchoolDAO {

	@Autowired
	JdbcTemplate jdbctemplate;

	private static final String ADD_SCHOOL = "INSERT INTO mydb.SCHOOL (NAME, ADDRESS, CREATION_DATE, CHANGE_DATE) VALUES(?, ?, ?, ?)";
	private static final String GET_SCHOOL_BY_ID = "SELECT * FROM mydb.SCHOOL WHERE ID = ?";
	private static final String GET_SCHOOLS = "SELECT * FROM mydb.SCHOOL";
	private static final String UPDATE_SCHOOL_NAME = "UPDATE mydb.SCHOOL SET NAME = ?, CHANGE_DATE = ? WHERE ID = ?";
	private static final String UPDATE_SCHOOL_ADDRESS = "UPDATE mydb.SCHOOL SET ADDRESS = ?, CHANGE_DATE = ? WHERE ID = ?";
	private static final String DELETE_SCHOOL_BY_ID = "DELETE FROM mydb.SCHOOL WHERE ID = ?";

	@Override
	public Integer addSchool(School school) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(ADD_SCHOOL,
				new Object[] { school.getName(), school.getAddress(), currentTimeMillis, currentTimeMillis });
	}

	@Override
	public School getSchool(Integer id) {
		return jdbctemplate.queryForObject(GET_SCHOOL_BY_ID, new SchoolMapper(), id);
	}

	@Override
	public List<School> getSchooles() {
		return jdbctemplate.query(GET_SCHOOLS, new SchoolMapper());
	}

	@Override
	public Integer updateSchoolName(Integer id, String name) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_SCHOOL_NAME, new Object[] { name, currentTimeMillis, id });
	}

	@Override
	public Integer updateSchoolAddress(Integer id, String address) {
		Long currentTimeMillis = new java.util.Date().getTime();
		return jdbctemplate.update(UPDATE_SCHOOL_ADDRESS, new Object[] { address, currentTimeMillis, id });
	}

	@Override
	public Integer deleteSchool(Integer id) {
		return jdbctemplate.update(DELETE_SCHOOL_BY_ID, id);
	}

	private static class SchoolMapper implements RowMapper<School> {

		@Override
		public School mapRow(ResultSet rs, int rowNum) throws SQLException {
			School school = new School();
			school.setId(rs.getInt("id"));
			school.setName(rs.getString("name"));
			school.setAddress(rs.getString("address"));
			school.setCreationDate(rs.getLong("creation_date"));
			return school;

		}

	}

}
