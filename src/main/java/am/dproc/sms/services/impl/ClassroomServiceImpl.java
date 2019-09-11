package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.ClassroomDAO;
import am.dproc.sms.models.Classroom;
import am.dproc.sms.services.interfaces.ClassroomService;

@Service
public class ClassroomServiceImpl implements ClassroomService {

	@Autowired
	ClassroomDAO classroom;

	@Override
	public Classroom getClassroomByID(Integer id) {
		return classroom.getClassroom(id);
	}

	@Override
	public List<Classroom> getAllClassrooms() {
		return classroom.getClassrooms();
	}

	@Override
	public List<Classroom> getClassrooms(Integer min, Integer max) {
		return classroom.getClassrooms(min, max);
	}

	@Override
	public Integer deleteClassRoom(Integer id) {
		return classroom.deleteClassroom(id);
	}

	@Override
	public Integer addClassroom(Classroom classroom) {
		if (classroom.getNumber() == null || classroom.getCapacity() == null || classroom.getSubject() == null
				|| classroom.getType() == null) {
			return 0;
		}
		return this.classroom.addClassroom(classroom);
	}

	@Override
	public Integer editClassroom(Classroom classroom) {
		if (classroom.getNumber() != null) {
			return this.classroom.editClassroomNumber(classroom.getId(), classroom.getNumber());
		}
		if (classroom.getCapacity() != null) {
			return this.classroom.editClassroomCapacity(classroom.getId(), classroom.getCapacity());
		}
		if (classroom.getType() != null) {
			return this.classroom.editClassroomType(classroom.getId(), classroom.getType());
		}
		if (classroom.getSubject() != null) {
			return this.classroom.editClassroomSubject(classroom.getId(), classroom.getSubject());
		}

		return 0;
	}

}
