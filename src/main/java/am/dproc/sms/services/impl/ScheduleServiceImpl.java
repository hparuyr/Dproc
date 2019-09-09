package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.ScheduleDAO;
import am.dproc.sms.models.ScheduleRecord;
import am.dproc.sms.services.interfaces.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	ScheduleDAO scheduleDAO;

	@Override
	public ScheduleRecord getScheduleRecordById(Integer id) {
		return scheduleDAO.getScheduleRecordById(id);
	}

	@Override
	public List<ScheduleRecord> getScheduleForTeacherInPeriod(Integer teacherId, Long startDate, Long endDate) {
		return scheduleDAO.getScheduleForTeacherInPeriod(teacherId, startDate, endDate);
	}

	@Override
	public List<ScheduleRecord> getScheduleForGroupCourseInPeriod(Integer groupCourseId, Long startDate, Long endDate) {
		return scheduleDAO.getScheduleForGroupCourseInPeriod(groupCourseId, startDate, endDate);
	}

	@Override
	public List<ScheduleRecord> getScheduleForClassRoomInPeriod(Integer classRoomId, Long startDate, Long endDate) {
		return scheduleDAO.getScheduleForClassRoomInPeriod(classRoomId, startDate, endDate);
	}

	@Override
	public Integer createScheduleRecord(ScheduleRecord scheduleRecord) {
		return scheduleDAO.createScheduleRecord(scheduleRecord);
	}

	@Override
	public Integer updateScheduleRecord(ScheduleRecord scheduleRecord) {
		return scheduleDAO.updateScheduleRecord(scheduleRecord);
	}

	@Override
	public Integer deleteScheduleRecord(Integer scheduleRecordId) {
		return scheduleDAO.deleteScheduleRecord(scheduleRecordId);
	}

}
