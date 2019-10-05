package am.dproc.sms.db.interfaces;

import java.util.List;
import am.dproc.sms.models.ScheduleRecord;

public interface ScheduleDAO {
	public ScheduleRecord getScheduleRecordById(Integer id);

	public List<ScheduleRecord> getScheduleForTeacherInPeriod(Integer teacherId, Long startDate, Long endDate);

	public List<ScheduleRecord> getScheduleForGroupCourseInPeriod(Integer groupCourseId, Long startDate, Long endDate);

	public List<ScheduleRecord> getScheduleForClassRoomInPeriod(Integer classRoomId, Long startDate, Long endDate);

	public Integer createScheduleRecord(ScheduleRecord scheduleRecord);

	public Integer updateScheduleRecord(ScheduleRecord scheduleRecord);

	public Integer deleteScheduleRecord(Integer scheduleRecordId);

	List<ScheduleRecord> getScheduleForStudentInPeriod(Integer studentId, Long startDate, Long endDate);

}
