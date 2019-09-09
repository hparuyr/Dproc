package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.TestDAO;
import am.dproc.sms.models.Test;

@Repository
public class TestDAODBImpl implements TestDAO {
	@Autowired
	JdbcTemplate template;

	private static final String CREATE_TEST = "insert into mydb.TEST(TITLE, LESSON_ID, CREATION_DATE) values (?, ?, ?)";
	private static final String GET_TEST_BY_ID = "select * from mydb.TEST where ID = ? ";
	private static final String GET_ALL_TESTS = "select * from mydb.TEST";
	private static final String UPDATE_TEST = "update mydb.TEST set TITLE = ?, LESSON_ID = ?, CHANGE_DATE = ? where ID = ?";
	private static final String DELETE_TEST = "delete from mydb.TEST where ID = ?";

	@Override
	public Integer createTest(Test test) {
		return template.update(CREATE_TEST, test.getTitle(), test.getLessonId(), System.currentTimeMillis());
	}

	@Override
	public Test getTest(Integer id) {
		Test test = template.queryForObject(GET_TEST_BY_ID, new TestMapper(), id);
		return test;
	}

	@Override
	public List<Test> getAllTests() {
		return template.query(GET_ALL_TESTS, new TestMapper());
	}

	@Override
	public Integer updateTest(Test test) {
		return template.update(UPDATE_TEST, test.getTitle(), test.getLessonId(), System.currentTimeMillis(),
				test.getId());
	}

	@Override
	public Integer deleteTest(Integer id) {
		return template.update(DELETE_TEST, id);
	}

	private static class TestMapper implements RowMapper<Test> {
		@Override
		public Test mapRow(ResultSet rs, int rownumber) throws SQLException {
			Test test = new Test();
			test.setId(rs.getInt("ID"));
			test.setTitle(rs.getString("TITLE"));
			test.setLessonId(rs.getInt("LESSON_ID"));

			return test;
		}
	}
}