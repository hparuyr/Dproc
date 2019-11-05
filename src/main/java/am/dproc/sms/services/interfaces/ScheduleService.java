package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.ScheduleRecord;

public interface ScheduleService {

	Integer addScheduleRecord(ScheduleRecord scheduleRecord);

	ScheduleRecord getScheduleRecordById(Integer id);

	List<ScheduleRecord> getScheduleForTeacherInPeriod(Integer teacherId, Long startDate, Long endDate);

	List<ScheduleRecord> getScheduleForStudentInPeriod(Integer studentId, Long startDate, Long endDate);

	List<ScheduleRecord> getScheduleForGroupCourseInPeriod(Integer groupCourseId, Long startDate, Long endDate);

	List<ScheduleRecord> getScheduleForClassRoomInPeriod(Integer classRoomId, Long startDate, Long endDate);

	Integer updateScheduleRecord(ScheduleRecord scheduleRecord);

	Integer deleteScheduleRecord(Integer scheduleRecordId);

}
