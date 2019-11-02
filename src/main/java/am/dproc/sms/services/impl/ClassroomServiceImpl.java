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
    ClassroomDAO classroomDAO;

    @Override
    public Integer addClassroom(Classroom classroom) {
        if (classroom.getName() == null || classroom.getCapacity() == null || classroom.getSubject() == null
                || classroom.getType() == null) {
            return -1;
        }
        return this.classroomDAO.addClassroom(classroom);
    }

    @Override
    public Classroom getClassroomByID(Integer id) {
        return classroomDAO.getClassroom(id);
    }

    @Override
    public List<Classroom> getAllClassrooms() {
        return classroomDAO.getClassrooms();
    }

    @Override
    public List<Classroom> getClassrooms(Integer min, Integer max) {
        return classroomDAO.getClassroomsByCapacity(min, max);
    }

    @Override
    public Integer updateClassroom(Classroom classroom) {

        boolean bool = false;

        if (classroom.getName() != null) {
            if (this.classroomDAO.updateClassroomName(classroom.getId(), classroom.getName()) == 0) {
                return -1;
            }
            bool = true;
        }
        if (classroom.getCapacity() != null) {
            if (this.classroomDAO.updateClassroomCapacity(classroom.getId(), classroom.getCapacity()) == 0) {
                return -1;
            }
            bool = true;
        }
        if (classroom.getType() != null) {
            if (this.classroomDAO.updateClassroomType(classroom.getId(), classroom.getType()) == 0) {
                return -1;
            }
            bool = true;
        }
        if (classroom.getSubject() != null) {
            if (this.classroomDAO.updateClassroomSubject(classroom.getId(), classroom.getSubject()) == 0) {
                return -1;
            }
            bool = true;
        }

        return bool ? 1 : 0;
    }

    @Override
    public Integer deleteClassRoom(Integer id) {
        return classroomDAO.deleteClassroom(id);
    }

}
