package am.dproc.sms.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import am.dproc.sms.db.root.ScheduleDAO;
import am.dproc.sms.models.ScheduleRecord;

@Repository
public class ScheduleDAODBImpl implements ScheduleDAO {
	@Autowired
	JdbcTemplate template;

	private static final String CREATE_SCHEDULE_RECORD = "insert into mydb.SCHEDULE_RECORD(GROUP_COURSE_ID, CLASSROOM_ID, LESSON_ID, START_DATE, END_DATE, CREATION_DATE) values (?, ?, ?, ?, ?, ?)";
	private static final String GET_SCHEDULE_RECORD_BY_ID = "select * from mydb.SCHEDULE_RECORD where ID = ?";
	private static final String GET_SCHEDULE_RECORD_FOR_TEACHER_IN_PERIOD = "select SCH.* from mydb.SCHEDULE_RECORD SCH join GROUP_COURSE GC on SCH.GROUP_COURSE_ID = GC.ID "
			+ "where GC.TEACHER_ID = ? and ? <= SCH.END_DATE and SCH.START_DATE <= ? order by SCH.START_DATE, SCH.END_DATE";
	private static final String GET_SCHEDULE_RECORD_FOR_GROUP_COURSE_IN_PERIOD = "select * from mydb.SCHEDULE_RECORD where GROUP_COURSE_ID = ? "
			+ " and ? <= END_DATE and START_DATE <= ? order by START_DATE, END_DATE";
	private static final String GET_SCHEDULE_RECORD_FOR_CLASSROOM_IN_PERIOD = "select * from mydb.SCHEDULE_RECORD where CLASSROOM_ID = ? "
			+ " and ? <= END_DATE and START_DATE <= ? order by START_DATE, END_DATE";
	private static final String UPDATE_SCHEDULE_RECORD = "update mydb.SCHEDULE_RECORD set GROUP_COURSE_ID = ?, CLASSROOM_ID = ?, LESSON_ID = ?, START_DATE = ?, END_DATE = ?, CHANGE_DATE = ? where ID = ?";
	private static final String DELETE_SCHEDULE_RECORD = "delete from mydb.SCHEDULE_RECORD where ID = ?";

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
