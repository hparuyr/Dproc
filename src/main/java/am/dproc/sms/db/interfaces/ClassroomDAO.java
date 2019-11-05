package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Classroom;

public interface ClassroomDAO {

	Integer addClassroom(Classroom classroom);

	Classroom getClassroom(Integer id);

	List<Classroom> getClassrooms();

	List<Classroom> getClassroomsByCapacity(Integer min, Integer max);

	Integer updateClassroomName(Integer id, String name);

	Integer updateClassroomCapacity(Integer id, Integer capacity);

	Integer updateClassroomType(Integer id, String type);

	Integer updateClassroomSubject(Integer id, String subject);

	Integer deleteClassroom(Integer id);

}
