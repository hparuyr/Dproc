package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.GroupCourse;

public interface GroupCourseService {

    Integer create(GroupCourse groupCourse);

    GroupCourse getById(Integer id);

    GroupCourse getByGroupAndCourse(Integer groupId, Integer courseId);

    List<GroupCourse> getByGroupID(Integer groupId);

    List<GroupCourse> getByCourseID(Integer courseId);

    List<GroupCourse> getAll();

    List<GroupCourse> getByTeacherID(Integer id);

    List<GroupCourse> getByTeacherIDAndSchoolId(Integer teacherID, Integer schoolID);

    Integer update(GroupCourse groupCourse);

    Integer deleteByGroupID(Integer groupId);

    Integer deleteByCourseID(Integer courseId);

    Integer deleteAll();

}
