package am.dproc.sms.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.SchoolDAO;
import am.dproc.sms.models.School;

@Repository
public class SchoolDAODBImpl implements SchoolDAO {

    @Autowired

    JdbcTemplate jdbcTemplate;

    private static final String ADD_SCHOOL = "" +
            "INSERT " +
            "INTO mydb.SCHOOL (NAME, ADDRESS, PHONE_NUMBER, EMAIL, CREATION_DATE) " +
            "VALUES(?, ?, ?, ?, ?)";
    private static final String GET_SCHOOL_BY_ID = "" +
            "SELECT ID, NAME, ADDRESS, PHONE_NUMBER, EMAIL " +
            "FROM mydb.SCHOOL " +
            "WHERE ID = ?";
    private static final String GET_SCHOOLS = "" +
            "SELECT ID, NAME, ADDRESS, PHONE_NUMBER, EMAIL " +
            "FROM mydb.SCHOOL";
    private static final String UPDATE_SCHOOL_NAME = "" +
            "UPDATE mydb.SCHOOL " +
            "SET NAME = ?, CHANGE_DATE = ? " +
            "WHERE ID = ?";
    private static final String UPDATE_SCHOOL_ADDRESS = "" +
            "UPDATE mydb.SCHOOL " +
            "SET ADDRESS = ?, CHANGE_DATE = ? " +
            "WHERE ID = ?";
    private static final String UPDATE_SCHOOL_PHONE_NUMBER = "" +
            "UPDATE mydb.SCHOOL " +
            "SET PHONE_NUMBER = ?, CHANGE_DATE = ? " +
            "WHERE ID = ?";
    private static final String UPDATE_SCHOOL_EMAIL = "" +
            "UPDATE mydb.SCHOOL " +
            "SET EMAIL = ?, CHANGE_DATE = ? " +
            "WHERE ID = ?";
    private static final String DELETE_SCHOOL_BY_ID = "" +
            "DELETE " +
            "FROM mydb.SCHOOL " +
            "WHERE ID = ?";

    @Override
    public Integer addSchool(School school) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(ADD_SCHOOL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, school.getName());
            ps.setString(2, school.getAddress());
            ps.setString(3, school.getPhoneNumber());
            ps.setString(4, school.geteMail());
            ps.setLong(5, System.currentTimeMillis());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public School getSchool(Integer id) {
        return jdbcTemplate.queryForObject(GET_SCHOOL_BY_ID, new SchoolMapper(), id);
    }

    @Override
    public List<School> getSchools() {
        return jdbcTemplate.query(GET_SCHOOLS, new SchoolMapper());
    }

    @Override
    public Integer updateSchoolName(Integer id, String name) {
        return jdbcTemplate.update(UPDATE_SCHOOL_NAME, name, System.currentTimeMillis(), id);
    }

    @Override
    public Integer updateSchoolAddress(Integer id, String address) {
        return jdbcTemplate.update(UPDATE_SCHOOL_ADDRESS, address, System.currentTimeMillis(), id);
    }

    @Override
    public Integer updateSchoolPhoneNumber(Integer id, String phoneNumber) {
        return jdbcTemplate.update(UPDATE_SCHOOL_PHONE_NUMBER, phoneNumber, System.currentTimeMillis(), id);
    }

    @Override
    public Integer updateSchoolEMail(Integer id, String eMail) {
        return jdbcTemplate.update(UPDATE_SCHOOL_EMAIL, eMail, System.currentTimeMillis(), id);
    }

    @Override
    public Integer deleteSchool(Integer id) {
        return jdbcTemplate.update(DELETE_SCHOOL_BY_ID, id);
    }

    private static class SchoolMapper implements RowMapper<School> {

        @Override
        public School mapRow(ResultSet rs, int rowNum) throws SQLException {
            School school = new School();

            school.setId(rs.getInt("ID"));
            school.setName(rs.getString("NAME"));
            school.setAddress(rs.getString("ADDRESS"));
            school.setPhoneNumber(rs.getString("PHONE_NUMBER"));
            school.seteMail(rs.getString("EMAIL"));

            return school;
        }
    }
}
