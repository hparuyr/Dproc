package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.GroupCourse;

public interface GroupCourseService {

    Integer add(GroupCourse groupCourse);

    GroupCourse getById(Integer id);

    GroupCourse getByGroupAndCourse(Integer groupId, Integer courseId);

    List<GroupCourse> getByGroupId(Integer groupId);

    List<GroupCourse> getByCourseID(Integer courseId);

    List<GroupCourse> getAll();

    List<GroupCourse> getByTeacherId(Integer id);

    List<GroupCourse> getByTeacherIDAndSchoolId(Integer teacherId, Integer schoolId);

    Integer update(GroupCourse groupCourse);

    Integer deleteByGroupId(Integer groupId);

    Integer deleteByCourseId(Integer courseId);

    Integer deleteAll();

}
