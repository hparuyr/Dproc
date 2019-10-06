package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Classroom;

public interface ClassroomDAO {

	Integer addClassroom(Classroom classroom);

	Classroom getClassroom(Integer id);

	List<Classroom> getClassrooms();

	List<Classroom> getClassroomsByCapacity(Integer min, Integer max);

	Integer editClassroomName(Integer id, String name);

	Integer editClassroomCapacity(Integer id, Integer capacity);

	Integer editClassroomType(Integer id, String type);

	Integer editClassroomSubject(Integer id, String subject);

	Integer deleteClassroom(Integer id);

}
