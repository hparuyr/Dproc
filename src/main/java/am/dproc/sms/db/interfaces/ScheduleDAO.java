package am.dproc.sms.db.interfaces;

import java.util.List;
import am.dproc.sms.models.ScheduleRecord;

public interface ScheduleDAO {

	Integer addScheduleRecord(ScheduleRecord scheduleRecord);

	ScheduleRecord getScheduleRecordById(Integer id);

	List<ScheduleRecord> getScheduleForTeacherInPeriod(Integer teacherId, Long startDate, Long endDate);

	List<ScheduleRecord> getScheduleForGroupCourseInPeriod(Integer groupCourseId, Long startDate, Long endDate);

	List<ScheduleRecord> getScheduleForClassRoomInPeriod(Integer classRoomId, Long startDate, Long endDate);

	Integer updateScheduleRecord(ScheduleRecord scheduleRecord);

	Integer deleteScheduleRecord(Integer scheduleRecordId);

	List<ScheduleRecord> getScheduleForStudentInPeriod(Integer studentId, Long startDate, Long endDate);

}
