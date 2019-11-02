package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.SurveyResultDAO;
import am.dproc.sms.models.SurveyResult;

@Repository
public class SurveyResultDAOImpl implements SurveyResultDAO {

    @Autowired
    JdbcTemplate template;

    public static final String CREATE = "INSERT INTO SURVEY_RESULT (STUDENT_ID, EXT, EST, AGR, CSN, OPN, CREATION_DATE, CHANGE_DATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL = "SELECT * FROM SURVEY_RESULT";
    private static final String GET_BY_STUDENT_ID = "SELECT * FROM SURVEY_RESULT WHERE STUDENT_ID = ?";
    private static final String UPDATE_BY_STUDENT_ID = "UPDATE SURVEY_RESULT SET EXT=?, EST=?, AGR=?, CSN=?, OPN=?, CHANGE_DATE=? WHERE STUDENT_ID = ?";
    private static final String DELETE_BY_STUDENT_ID = "DELETE FROM SURVEY_RESULT WHERE STUDENT_ID = ?";
    private static final String DELETE_ALL = "DELETE FROM SURVEY_RESULT";

    @Override
    public int create(SurveyResult surveyResult) {
//		KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
        long currentTimeStamp = System.currentTimeMillis();
        return template.update(CREATE, surveyResult.getStudentId(), surveyResult.getExt(), surveyResult.getEst(),
                surveyResult.getAgr(), surveyResult.getCsn(), surveyResult.getOpn(), currentTimeStamp,
                currentTimeStamp);
//		jdbcTemplate.update(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//				PreparedStatement ps = con.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
//				ps.setInt(1, surveyResult.getStudentId());
//				ps.setFloat(2, surveyResult.getExt());
//				ps.setFloat(3, surveyResult.getEst());
//				ps.setFloat(4, surveyResult.getAgr());
//				ps.setFloat(5, surveyResult.getCsn());
//				ps.setFloat(6, surveyResult.getOpn());
//				ps.setLong(7, currentTimeStamp);
//				ps.setLong(8, currentTimeStamp);
//				return ps;
//			}
//		}, keyHolder);
//		Map<String, Object> map = keyHolder.getKeys();
//		System.out.println("-=-=-=-=-=->>>>map: "+map);
//		List<Map<String, Object>> list = keyHolder.getKeyList();
//		System.out.println("-=-=-=-=-=->>>>list: "+map);
//		
//		return keyHolder.getKey().intValue();
    }

    @Override
    public SurveyResult getByStudentId(int studentId) {
        return template.queryForObject(GET_BY_STUDENT_ID, new SurveyResultMapper(), studentId);
    }

    @Override
    public List<SurveyResult> getAll() {
        return template.query(GET_ALL, new SurveyResultMapper());
    }

    @Override
    public int update(SurveyResult surveyResult) {
        return template.update(UPDATE_BY_STUDENT_ID, surveyResult.getExt(), surveyResult.getEst(),
                surveyResult.getAgr(), surveyResult.getCsn(), surveyResult.getOpn(), System.currentTimeMillis(),
                surveyResult.getStudentId());
    }

    @Override
    public int delete(int studentId) {
        return template.update(DELETE_BY_STUDENT_ID, studentId);
    }

    @Override
    public int deleteAll() {
        return template.update(DELETE_ALL);
    }

    private static class SurveyResultMapper implements RowMapper<SurveyResult> {
        @Override
        public SurveyResult mapRow(ResultSet rs, int rowNum) throws SQLException {
            SurveyResult surveyResult = new SurveyResult();

            surveyResult.setStudentId(rs.getInt("STUDENT_ID"));
            surveyResult.setExt(rs.getFloat("EXT"));
            surveyResult.setEst(rs.getFloat("EST"));
            surveyResult.setAgr(rs.getFloat("AGR"));
            surveyResult.setCsn(rs.getFloat("CSN"));
            surveyResult.setOpn(rs.getFloat("OPN"));

            return surveyResult;
        }
    }
}
