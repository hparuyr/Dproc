package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Classroom;

public interface ClassroomService {

	Integer addClassroom(Classroom classroom);

	Classroom getClassroomByID(Integer id);

	List<Classroom> getAllClassrooms();

	List<Classroom> getClassrooms(Integer min, Integer max);

	Integer updateClassroom(Classroom classroom);

	Integer deleteClassRoom(Integer id);

}
