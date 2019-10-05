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
	public Integer addClassroom(Classroom classroom) {
		if (classroom.getName() == null || classroom.getCapacity() == null || classroom.getSubject() == null
				|| classroom.getType() == null) {
			return 0;
		}
		return this.classroom.addClassroom(classroom);
	}

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
	public Integer editClassroom(Classroom classroom) {

		boolean bool = false;
		
		if (classroom.getName() != null) {
			if (this.classroom.editClassroomName(classroom.getId(), classroom.getName()) == 0) {
				return -1;
			}
			bool = true;
		}
		if (classroom.getCapacity() != null) {
			if (this.classroom.editClassroomCapacity(classroom.getId(), classroom.getCapacity()) == 0) {
				return -1;
			}
			bool = true;
		}
		if (classroom.getType() != null) {
			if (this.classroom.editClassroomType(classroom.getId(), classroom.getType()) == 0) {
				return -1;
			}
			bool = true;
		}
		if (classroom.getSubject() != null) {
			if (this.classroom.editClassroomSubject(classroom.getId(), classroom.getSubject()) == 0) {
				return -1;
			}
			bool = true;
		}

		return bool == true ? 1 : 0;
	}

	@Override
	public Integer deleteClassRoom(Integer id) {
		return classroom.deleteClassroom(id);
	}

}
