package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.interfaces.ScheduleDAO;
import am.dproc.sms.models.ScheduleRecord;

@Repository
public class ScheduleDAODBImpl implements ScheduleDAO {

    @Autowired
    JdbcTemplate template;

    private static final String CREATE_SCHEDULE_RECORD = "INSERT INTO mydb.SCHEDULE_RECORD(GROUP_COURSE_ID, CLASSROOM_ID, LESSON_ID, START_DATE, END_DATE, CREATION_DATE) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_SCHEDULE_RECORD_BY_ID = "SELECT * FROM mydb.SCHEDULE_RECORD WHERE ID = ?";
    private static final String GET_SCHEDULE_RECORD_FOR_TEACHER_IN_PERIOD = "SELECT SCH.* FROM mydb.SCHEDULE_RECORD SCH JOIN GROUP_COURSE GC ON SCH.GROUP_COURSE_ID = GC.ID "
            + "WHERE GC.TEACHER_ID = ? AND ? <= SCH.END_DATE AND SCH.START_DATE <= ? ORDER BY SCH.START_DATE, SCH.END_DATE";
    private static final String GET_SCHEDULE_RECORD_FOR_STUDENT_IN_PERIOD = "SELECT SCH.* \r\n" +
            "FROM STUDENT_GROUP SG \r\n" +
            "   JOIN GROUP_COURSE GC ON SG.GROUP_ID = GC.GROUP_ID \r\n" +
            "   JOIN SCHEDULE_RECORD SCH ON SCH.GROUP_COURSE_ID = GC.ID \r\n" +
            "WHERE SG.STUDENT_ID = ? AND ? <= SCH.END_DATE AND SCH.START_DATE <= ? ORDER BY SCH.START_DATE, SCH.END_DATE";
    private static final String GET_SCHEDULE_RECORD_FOR_GROUP_COURSE_IN_PERIOD = "SELECT * FROM mydb.SCHEDULE_RECORD WHERE GROUP_COURSE_ID = ? "
            + " AND ? <= END_DATE AND START_DATE <= ? ORDER BY START_DATE, END_DATE";
    private static final String GET_SCHEDULE_RECORD_FOR_CLASSROOM_IN_PERIOD = "SELECT * FROM mydb.SCHEDULE_RECORD WHERE CLASSROOM_ID = ? "
            + " AND ? <= END_DATE AND START_DATE <= ? ORDER BY START_DATE, END_DATE";
    private static final String UPDATE_SCHEDULE_RECORD = "UPDATE mydb.SCHEDULE_RECORD SET GROUP_COURSE_ID = ?, CLASSROOM_ID = ?, LESSON_ID = ?, START_DATE = ?, END_DATE = ?, CHANGE_DATE = ? WHERE ID = ?";
    private static final String DELETE_SCHEDULE_RECORD = "DELETE FROM mydb.SCHEDULE_RECORD WHERE ID = ?";

    @Override
    public ScheduleRecord getScheduleRecordById(Integer id) {
        return template.queryForObject(GET_SCHEDULE_RECORD_BY_ID, new ScheduleMapper(), id);
    }

    @Override
    public List<ScheduleRecord> getScheduleForTeacherInPeriod(Integer teacherId, Long startDate, Long endDate) {
        return template.query(GET_SCHEDULE_RECORD_FOR_TEACHER_IN_PERIOD, new ScheduleMapper(), teacherId, startDate,
                endDate);
    }

    @Override
    public List<ScheduleRecord> getScheduleForStudentInPeriod(Integer studentId, Long startDate, Long endDate) {
        return template.query(GET_SCHEDULE_RECORD_FOR_STUDENT_IN_PERIOD, new ScheduleMapper(), studentId, startDate,
                endDate);
    }

    @Override
    public List<ScheduleRecord> getScheduleForGroupCourseInPeriod(Integer groupCourseId, Long startDate, Long endDate) {
        return template.query(GET_SCHEDULE_RECORD_FOR_GROUP_COURSE_IN_PERIOD, new ScheduleMapper(), groupCourseId,
                startDate, endDate);
    }

    @Override
    public List<ScheduleRecord> getScheduleForClassRoomInPeriod(Integer classRoomId, Long startDate, Long endDate) {
        return template.query(GET_SCHEDULE_RECORD_FOR_CLASSROOM_IN_PERIOD, new ScheduleMapper(), classRoomId, startDate,
                endDate);
    }

    @Override
    public Integer createScheduleRecord(ScheduleRecord scheduleRecord) {
        return template.update(CREATE_SCHEDULE_RECORD, scheduleRecord.getGroupCourseId(),
                scheduleRecord.getClassroomId(), scheduleRecord.getLessonId(), scheduleRecord.getStartDate().getTime(),
                scheduleRecord.getEndDate().getTime(), System.currentTimeMillis());
    }

    @Override
    public Integer updateScheduleRecord(ScheduleRecord scheduleRecord) {
        return template.update(UPDATE_SCHEDULE_RECORD, scheduleRecord.getGroupCourseId(),
                scheduleRecord.getClassroomId(), scheduleRecord.getLessonId(), scheduleRecord.getStartDate().getTime(),
                scheduleRecord.getEndDate().getTime(), System.currentTimeMillis(), scheduleRecord.getId());
    }

    @Override
    public Integer deleteScheduleRecord(Integer scheduleRecordId) {
        return template.update(DELETE_SCHEDULE_RECORD, scheduleRecordId);
    }

    private static class ScheduleMapper implements RowMapper<ScheduleRecord> {
        @Override
        public ScheduleRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
            ScheduleRecord sch = new ScheduleRecord();

            sch.setId(rs.getInt("ID"));
            sch.setGroupCourseId(rs.getInt("GROUP_COURSE_ID"));
            sch.setClassroomId(rs.getInt("CLASSROOM_ID"));
            sch.setLessonId(rs.getInt("LESSON_ID"));
            sch.setStartDate(new Date(rs.getLong("START_DATE")));
            sch.setEndDate(new Date(rs.getLong("END_DATE")));

            return sch;
        }
    }

}
