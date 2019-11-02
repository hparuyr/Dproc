package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.CourseDAO;
import am.dproc.sms.models.Course;
import am.dproc.sms.services.interfaces.CourseService;
import am.dproc.sms.services.interfaces.LessonService;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDAO courseDAO;

    @Autowired
    LessonService lessonService;

    @Override
    public Integer addCourse(Course course) {
        if (course.getSchoolId() == null || course.getName() == null || course.getDescription() == null
                || course.getDuration() == null || course.getDurationUnitType() == null) {
            return -1;
        }
        Integer courseID = this.courseDAO.addCourse(course);

        if (course.getListOfLessons() != null) {
            for (int i = 0; i < course.getListOfLessons().size(); i++) {
                course.getListOfLessons().get(i).setCourseId(courseID);
                lessonService.addLesson(course.getListOfLessons().get(i));
            }
        }

        return this.courseDAO.addCourse(course);
    }

    @Override
    public Course getCourse(Integer id) {
        Course course = this.courseDAO.getCourse(id);
        course.setListOfLessons(lessonService.getCourseLessons(id));
        return course;
    }

    @Override
    public List<Course> getCourses() {
        return courseDAO.getCourses();
    }

    @Override

    public List<Course> getCoursesByGroupId(Integer groupId) {
        return courseDAO.getCoursesByGroupId(groupId);
    }


    @Override
    public Integer deleteCourse(Integer id) {
        if (getCourse(id).getListOfLessons().size() == 0) {
            return courseDAO.deleteCourse(id);
        }
        return 0;
    }

    public Integer updateCourse(Course course) {

        boolean bool = false;

        if (course.getName() != null) {
            if (this.courseDAO.updateCourseName(course.getId(), course.getName()) == 0) {
                return -1;
            }
            bool = true;
        }
        if (course.getDescription() != null) {
            if (this.courseDAO.updateCourseDescription(course.getId(), course.getDescription()) == 0) {
                return -1;
            }
            bool = true;
        }
        if (course.getDuration() != null) {
            if (this.courseDAO.updateCourseDuration(course.getId(), course.getDuration()) == 0) {
                return -1;
            }
            bool = true;
        }
        if (course.getDurationUnitType() != null) {
            if (this.courseDAO.updateCourseDurationUnitType(course.getId(), course.getDurationUnitType()) == 0) {
                return -1;
            }
            bool = true;
        }

        return bool ? 1 : 0;
    }
}
