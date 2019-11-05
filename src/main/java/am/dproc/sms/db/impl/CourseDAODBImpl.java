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

import am.dproc.sms.db.interfaces.CourseDAO;
import am.dproc.sms.enums.CourseDurationUnitType;
import am.dproc.sms.models.Course;

@Repository
public class CourseDAODBImpl implements CourseDAO {

    @Autowired
    JdbcTemplate jdbctemplate;

    private static final String ADD_COURSE = ""
            + "INSERT "
            + "INTO mydb.COURSE (SCHOOL_ID, NAME, DESCRIPTION, DURATION, DURATION_UNIT_TYPE, CREATION_DATE) "
            + "VALUES(?, ?, ?, ?, ?, ?)";
    private static final String GET_COURSE_BY_ID = ""
            + "SELECT ID, SCHOOL_ID, NAME, DESCRIPTION, DURATION, DURATION_UNIT_TYPE "
            + "FROM mydb.COURSE "
            + "WHERE ID = ?";
    private static final String GET_COURSES = ""
            + "SELECT ID, SCHOOL_ID, NAME, DESCRIPTION, DURATION, DURATION_UNIT_TYPE "
            + "FROM mydb.COURSE";
    private static final String GET_COURSES_BY_GROUP_ID = ""
            + "SELECT C.* "
            + "FROM COURSE C "
            + "JOIN GROUP_COURSE GC "
            + "ON C.ID = GC.COURSE_ID "
            + "WHERE GC.GROUP_ID = ?";
    private static final String UPDATE_COURSE_NAME = ""
            + "UPDATE mydb.COURSE "
            + "SET NAME = ?, CHANGE_DATE = ? "
            + "WHERE ID = ?";
    private static final String UPDATE_COURSE_DURATION = ""
            + "UPDATE mydb.COURSE "
            + "SET DURATION = ?, CHANGE_DATE = ? "
            + "WHERE ID = ?";
    private static final String UPDATE_COURSE_DESCRIPTION = ""
            + "UPDATE mydb.COURSE "
            + "SET DESCRIPTION = ?, CHANGE_DATE = ? "
            + "WHERE ID = ?";
    private static final String UPDATE_COURSE_DURATION_UNIT_TYPE = ""
            + "UPDATE mydb.COURSE "
            + "SET DURATION_UNIT_TYPE = ?, CHANGE_DATE = ? "
            + "WHERE ID = ?";
    private static final String DELETE_COURSE_BY_ID = ""
            + "DELETE "
            + "FROM mydb.COURSE "
            + "WHERE ID = ?";

    @Override
    public Integer addCourse(Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbctemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(ADD_COURSE, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, course.getSchoolId());
            ps.setString(2, course.getName());
            ps.setString(3, course.getDescription());
            ps.setInt(4, course.getDuration());
            if (CourseDurationUnitType.DAYS.toString().toLowerCase().equals(course.getDurationUnitType().toLowerCase())) {
                ps.setInt(5, CourseDurationUnitType.DAYS.index());
            } else if (CourseDurationUnitType.WEEKS.toString().toLowerCase().equals(course.getDurationUnitType().toLowerCase())) {
                ps.setInt(5, CourseDurationUnitType.WEEKS.index());
            } else if (CourseDurationUnitType.MONTHS.toString().toLowerCase().equals(course.getDurationUnitType().toLowerCase())) {
                ps.setInt(5, CourseDurationUnitType.MONTHS.index());
            }
            ps.setLong(6, System.currentTimeMillis());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public Course getCourse(Integer id) {
        return jdbctemplate.queryForObject(GET_COURSE_BY_ID, new CourseMapper(), id);
    }

    @Override
    public List<Course> getCourses() {
        return jdbctemplate.query(GET_COURSES, new CourseMapper());
    }

    @Override
    public List<Course> getCoursesByGroupId(Integer groupId) {
        return jdbctemplate.query(GET_COURSES_BY_GROUP_ID, new CourseMapper(), groupId);
    }

    @Override
    public Integer updateCourseName(Integer id, String name) {
        return jdbctemplate.update(UPDATE_COURSE_NAME, name, System.currentTimeMillis(), id);
    }

    @Override
    public Integer updateCourseDescription(Integer id, String description) {
        return jdbctemplate.update(UPDATE_COURSE_DESCRIPTION, description, System.currentTimeMillis(), id);
    }

    @Override
    public Integer updateCourseDuration(Integer id, Integer duration) {
        return jdbctemplate.update(UPDATE_COURSE_DURATION, duration, System.currentTimeMillis(), id);
    }

    @Override
    public Integer updateCourseDurationUnitType(Integer id, String durationUnitType) {
        if (CourseDurationUnitType.DAYS.toString().toLowerCase().equals(durationUnitType.toLowerCase())) {
            return jdbctemplate.update(UPDATE_COURSE_DURATION_UNIT_TYPE, CourseDurationUnitType.DAYS.index(), System.currentTimeMillis(), id);
        } else if (CourseDurationUnitType.WEEKS.toString().toLowerCase().equals(durationUnitType.toLowerCase())) {
            return jdbctemplate.update(UPDATE_COURSE_DURATION_UNIT_TYPE, CourseDurationUnitType.WEEKS.index(), System.currentTimeMillis(), id);
        } else if (CourseDurationUnitType.MONTHS.toString().toLowerCase().equals(durationUnitType.toLowerCase())) {
            return jdbctemplate.update(UPDATE_COURSE_DURATION_UNIT_TYPE, CourseDurationUnitType.MONTHS.index(), System.currentTimeMillis(), id);
        }
        return -1;

    }

    @Override
    public Integer deleteCourse(Integer id) {
        return jdbctemplate.update(DELETE_COURSE_BY_ID, id);
    }

    private static class CourseMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

            Course course = new Course();
            course.setId(rs.getInt("ID"));
            course.setSchoolId(rs.getInt("SCHOOL_ID"));
            course.setName(rs.getString("NAME"));
            course.setDescription(rs.getString("DESCRIPTION"));
            course.setDuration(rs.getInt("DURATION"));
            if (CourseDurationUnitType.DAYS.index() == rs.getInt("DURATION_UNIT_TYPE")) {
                course.setDurationUnitType("Days");
            } else if (CourseDurationUnitType.WEEKS.index() == rs.getInt("DURATION_UNIT_TYPE")) {
                course.setDurationUnitType("Weeks");
            } else if (CourseDurationUnitType.MONTHS.index() == rs.getInt("DURATION_UNIT_TYPE")) {
                course.setDurationUnitType("Months");
            }
            return course;
        }

    }

}
