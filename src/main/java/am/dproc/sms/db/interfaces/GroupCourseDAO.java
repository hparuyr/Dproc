package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.GroupCourse;

public interface GroupCourseDAO {

    Integer create(GroupCourse groupCourse);

    GroupCourse getById(Integer id);

    GroupCourse getByGroupAndCourse(Integer groupId, Integer courseId);

    List<GroupCourse> getByGroupId(Integer groupId);

    List<GroupCourse> getByCourseId(Integer courseId);

    List<GroupCourse> getByTeacherId(Integer teacherId);

    List<GroupCourse> getByTeacherIdAndSchoolId(Integer teacherId, Integer schoolId);

    List<GroupCourse> getAll();

    Integer update(GroupCourse groupCourse);

    Integer updateTeacherId(Integer id, Integer teacherId);

    Integer updateStartDate(Integer id, Long startDate);

    Integer deleteByGroupId(Integer groupId);

    Integer deleteByCourseId(Integer courseId);

    Integer deleteById(Integer id);

    Integer deleteAll();
}
