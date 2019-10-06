package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Course;

public interface CourseDAO {

    Integer addCourse(Course course);

    Course getCourse(Integer id);

    List<Course> getCourses();

    List<Course> getCoursesByGroupId(Integer groupId);

    Integer editCourseName(Integer id, String name);

    Integer editCourseDescription(Integer id, String description);

    Integer editCourseDuration(Integer id, Integer duration);

    Integer editCourseDurationUnitType(Integer id, String durationUnitType);

    Integer deleteCourse(Integer id);

}
