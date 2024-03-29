package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Classroom;

public interface ClassroomDAO {

	public Classroom getClassroom(Integer id);
	
	public List<Classroom> getClassrooms();
	
	public List<Classroom> getClassrooms(Integer min, Integer max);
	
	public Integer deleteClassroom(Integer id);
			
	public Integer addClassroom(Classroom classroom);
	
	public Integer editClassroomNumber(Integer id, Integer numer);
	
	public Integer editClassroomCapacity(Integer id, Integer capacity);
	
	public Integer editClassroomType(Integer id, String type);
	
	public Integer editClassroomSubject(Integer id, String subject);
	
}
