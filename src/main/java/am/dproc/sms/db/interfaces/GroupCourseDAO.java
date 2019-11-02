package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.GroupCourse;

public interface GroupCourseDAO {

    Integer create(GroupCourse groupCourse);

    GroupCourse getById(Integer id);

    GroupCourse getByGroupAndCourse(Integer groupId, Integer courseId);

    List<GroupCourse> getByGroupID(Integer groupId);

    List<GroupCourse> getByCourseID(Integer courseId);

    List<GroupCourse> getByTeacherID(Integer teacherID);

    List<GroupCourse> getByTeacherIDAndSchoolId(Integer teacherID, Integer schoolID);

    List<GroupCourse> getAll();

    Integer update(GroupCourse groupCourse);

    Integer deleteByGroupID(Integer groupId);

    Integer deleteByCourseID(Integer courseId);

    Integer deleteAll();
}
