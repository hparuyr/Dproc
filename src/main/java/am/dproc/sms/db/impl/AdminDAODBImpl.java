package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.AdminDAO;
import am.dproc.sms.models.Admin;

@Repository
public class AdminDAODBImpl implements AdminDAO {

    @Autowired
    JdbcTemplate jdbctemplate;

    private static final String ADD_ADMIN = "INSERT INTO mydb.ADMIN (NAME, SURNAME, EMAIL, PASSWORD, CREATION_DATE,CHANGE_DATE, SCHOOL_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ADMIN_BY_ID = "SELECT * FROM mydb.ADMIN WHERE ID = ?";
    private static final String GET_ADMINS = "SELECT * FROM mydb.ADMIN";
    private static final String GET_ADMINS_BY_SCHOOL_ID = "SELECT * FROM mydb.ADMIN WHERE SCHOOL_ID = ?";
    private static final String UPDATE_ADMIN_NAME = "UPDATE mydb.ADMIN SET NAME = ?, CHANGE_DATE = ?, WHERE ID = ?";
    private static final String UPDATE_ADMIN_SURNAME = "UPDATE mydb.ADMIN SET SURNAME = ?, CHANGE_DATE = ?, WHERE ID = ?";
    private static final String UPDATE_ADMIN_EMAIL = "UPDATE mydb.ADMIN SET EMAIL= ?, CHANGE_DATE = ?, WHERE ID = ?";
    private static final String UPDATE_ADMIN_PASSWORD = "UPDATE mydb.ADMIN SET PASSWORD = ?, CHANGE_DATE = ?, WHERE ID = ?";
    private static final String UPDATE_ADMIN_SCHOOL_ID = "UPDATE mydb.ADMIN SET SCHOOL_ID = ?, CHANGE_DATE = ?, WHERE ID = ?";
    private static final String DELETE_ADMIN_BY_ID = "DELETE FROM mydb.ADMIN WHERE ID = ?";

    @Override
    public Integer addAdmin(Admin admin) {
        return jdbctemplate.update(ADD_ADMIN, admin.getName(), admin.getSurname(), admin.getEmail(),
                admin.getPassword(), System.currentTimeMillis(), System.currentTimeMillis(), admin.getSchoolId());
    }

    @Override
    public Admin getAdmin(Integer id) {
        return jdbctemplate.queryForObject(GET_ADMIN_BY_ID, new AdminMapper(), id);
    }

    @Override
    public List<Admin> getAdmins() {
        return jdbctemplate.query(GET_ADMINS, new AdminMapper());
    }

    @Override
    public List<Admin> getAdminsBySchoolId(Integer schoolId) {
        return jdbctemplate.query(GET_ADMINS_BY_SCHOOL_ID, new AdminMapper(), schoolId);
    }

    @Override
    public Integer updateAdminName(Integer id, String name) {
        return jdbctemplate.update(UPDATE_ADMIN_NAME, name, System.currentTimeMillis(), id);
    }

    @Override
    public Integer updateAdminSurname(Integer id, String surname) {
        return jdbctemplate.update(UPDATE_ADMIN_SURNAME, surname, System.currentTimeMillis(), id);
    }

    @Override
    public Integer updateAdminEmail(Integer id, String email) {
        return jdbctemplate.update(UPDATE_ADMIN_EMAIL, email, System.currentTimeMillis(), id);
    }

    @Override
    public Integer updateAdminPassword(Integer id, String password) {
        return jdbctemplate.update(UPDATE_ADMIN_PASSWORD, password, System.currentTimeMillis(), id);
    }

    @Override
    public Integer updateAdminSchoolId(Integer id, Integer schoolId) {
        return jdbctemplate.update(UPDATE_ADMIN_SCHOOL_ID, schoolId, System.currentTimeMillis(), id);
    }

    @Override
    public Integer deleteAdmin(Integer id) {
        return jdbctemplate.update(DELETE_ADMIN_BY_ID, id);
    }

    private static class AdminMapper implements RowMapper<Admin> {
        @Override
        public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
            Admin admin = new Admin();

            admin.setId(rs.getInt("id"));
            admin.setName(rs.getString("name"));
            admin.setSurname(rs.getString("surname"));
            admin.setEmail(rs.getString("email"));
            admin.setPassword(rs.getString("password"));
            admin.setSchoolId(rs.getInt("schoolId"));

            return admin;
        }

    }
}
