package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Classroom;

public interface ClassroomService {

	public Integer addClassroom(Classroom classroom);

	public Classroom getClassroomByID(Integer id);

	public List<Classroom> getAllClassrooms();

	public List<Classroom> getClassrooms(Integer min, Integer max);

	public Integer editClassroom(Classroom classroom);

	public Integer deleteClassRoom(Integer id);

}
