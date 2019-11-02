package am.dproc.sms.services.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import am.dproc.sms.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.services.interfaces.AssessmentService;
import am.dproc.sms.services.interfaces.AssignmentService;
import am.dproc.sms.services.interfaces.CSVExportService;
import am.dproc.sms.services.interfaces.CourseService;
import am.dproc.sms.services.interfaces.GroupCourseService;
import am.dproc.sms.services.interfaces.GroupService;
import am.dproc.sms.services.interfaces.LessonService;
import am.dproc.sms.services.interfaces.StudentService;
import am.dproc.sms.services.interfaces.TeacherInfoService;
import am.dproc.sms.services.interfaces.TeacherService;

@Service
public class CSVExportServiceImpl implements CSVExportService {

    @Autowired
    TeacherService teacher;

    @Autowired
    TeacherInfoService teacherInfo;

    @Autowired
    GroupCourseService groupCourse;

    @Autowired
    StudentService student;

    @Autowired
    CourseService course;

    @Autowired
    LessonService lesson;

    @Autowired
    GroupService group;

    @Autowired
    AssessmentService assessment;

    @Autowired
    AssignmentService assignment;

    private static final String DEFAULT_SEPARATOR = ";";
    private static final String LINE_DIVIDER = "\n";


    @Override
    public File getCSVFile(Integer teacherID, Integer schoolID) throws IOException {

        File file = new File(("src/main/info" + System.currentTimeMillis() + ".csv"));
        FileWriter csvWriter = new FileWriter(file);

        String[] header = {
                "STUDENT NAME", "STUDENT SURNAME",
                "STUDENT EMAIL", "GROUP NAME",
                "COURSE NAME", "LESSON", "ASSIGNMENT",
                "FEEDBACK", "ASSESSMENT"
        };

        for (int i = 0; i < header.length; i++) {
            csvWriter.append(header[i]);
            if (i != header.length - 1)
                csvWriter.append(DEFAULT_SEPARATOR);
        }
        csvWriter.append(LINE_DIVIDER);

        List<GroupCourse> listOfGroupCourse = groupCourse.getByTeacherIDAndSchoolId(teacherID, schoolID);

        System.out.println(Arrays.toString(listOfGroupCourse.toArray()));

        for (GroupCourse value : listOfGroupCourse) {
            if (!value.getFinished()) {
                List<Student> listOfStudents = student.getGroupStudents(value.getGroupId());

                System.out.println(Arrays.toString(listOfStudents.toArray()));

                if (listOfStudents.size() != 0) {
                    for (Student listOfStudent : listOfStudents) {
                        csvWriter.append(listOfStudent.getFirstname());
                        csvWriter.append(DEFAULT_SEPARATOR);
                        csvWriter.append(listOfStudent.getLastname());
                        csvWriter.append(DEFAULT_SEPARATOR);
                        csvWriter.append(listOfStudent.getEmail());
                        csvWriter.append(DEFAULT_SEPARATOR);
                        csvWriter.append(group.getGroup(value.getGroupId()).getName());
                        csvWriter.append(DEFAULT_SEPARATOR);

                        Course course = this.course.getCourse(value.getCourseId());

                        System.out.println(course.toString());

                        csvWriter.append(course.getName());
                        csvWriter.append(DEFAULT_SEPARATOR);

                        List<Lesson> listOfLessons = course.getListOfLessons();

                        System.out.println(Arrays.toString(listOfLessons.toArray()));

                        for (Lesson listOfLesson : listOfLessons) {
                            csvWriter.append(listOfLesson.getName());
                            csvWriter.append(DEFAULT_SEPARATOR);

                            Assignment assignment = this.assignment.getAssignmentByLessonID(listOfLesson.getId(), teacherID);

                            System.out.println(assignment.toString());

                            csvWriter.append(assignment.getTitle());
                            csvWriter.append(DEFAULT_SEPARATOR);

                            Assessment assessment = this.assessment.getAssessmentObjByStudentIDAndAssignmentID(listOfStudent.getId(), assignment.getId());

                            System.out.println(assessment.toString());

                            csvWriter.append(assessment.getComment());
                            csvWriter.append(DEFAULT_SEPARATOR);
                            csvWriter.append(assessment.getScore().toString());
                            csvWriter.append(DEFAULT_SEPARATOR);

                        }

                    }
                }
            }
        }

        csvWriter.close();
        return file;
    }

}
