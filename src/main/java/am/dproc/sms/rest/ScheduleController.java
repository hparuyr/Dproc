package am.dproc.sms.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import am.dproc.sms.models.ScheduleRecord;
import am.dproc.sms.services.interfaces.ScheduleService;
import io.swagger.annotations.Api;

@RestController
@Path(value = "/schedule")
@Api(value = "ScheduleController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;

	@POST
	public Integer addScheduleRecord(ScheduleRecord scheduleRecord) {
		return scheduleService.addScheduleRecord(scheduleRecord);
	}

	@GET
	@Path(value = "/{id}")
	public ScheduleRecord getScheduleRecordById(@PathParam(value = "id") Integer id) {
		return scheduleService.getScheduleRecordById(id);
	}

	@GET
	@Path(value = "teacher/{teacherId}/{start}/{end}")
	public List<ScheduleRecord> getScheduleForTeacherInPeriod(
			@PathParam(value = "teacherId") Integer teacherId,
			@PathParam(value = "start") Long startDate,
			@PathParam(value = "end") Long endDate) {
		return scheduleService.getScheduleForTeacherInPeriod(teacherId, startDate, endDate);
	}

	@GET
	@Path(value = "student/{studentId}/{start}/{end}")
	public List<ScheduleRecord> getScheduleForStudentInPeriod(
			@PathParam(value = "studentId") Integer studentId,
			@PathParam(value = "start") Long startDate,
			@PathParam(value = "end") Long endDate) {
		return scheduleService.getScheduleForStudentInPeriod(studentId, startDate, endDate);
	}

	@GET
	@Path(value = "groupCourse/{groupCourseId}/{start}/{end}")
	public List<ScheduleRecord> getScheduleForGroupCourseInPeriod(
			@PathParam(value = "groupCourseId") Integer groupCourseId,
			@PathParam(value = "start") Long starDate,
			@PathParam(value = "end") Long endDate) {
		return scheduleService.getScheduleForGroupCourseInPeriod(groupCourseId, starDate, endDate);
	}

	@GET
	@Path(value = "classRoom/{classroomId}/{start}/{end}")
	public List<ScheduleRecord> getScheduleForClassRoomInPeriod(
			@PathParam(value = "classroomId") Integer classroomId,
			@PathParam(value = "start") Long starDate,
			@PathParam(value = "end") Long endDate) {
		return scheduleService.getScheduleForClassRoomInPeriod(classroomId, starDate, endDate);
	}

	@PUT
	public Integer updateScheduleRecord(ScheduleRecord scheduleRecord) {
		return scheduleService.updateScheduleRecord(scheduleRecord);
	}

	@DELETE
	@Path(value = "/{id}")
	public Integer deleteScheduleRecord(@PathParam(value = "id") Integer scheduleRecordId) {
		return scheduleService.deleteScheduleRecord(scheduleRecordId);
	}
}
