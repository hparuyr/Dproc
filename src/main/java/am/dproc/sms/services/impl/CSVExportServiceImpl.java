package am.dproc.sms.services.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    TeacherService teacherService;

    @Autowired
    TeacherInfoService teacherInfoService;

    @Autowired
    GroupCourseService groupCourseService;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    @Autowired
    LessonService lessonService;

    @Autowired
    GroupService groupService;

    @Autowired
    AssessmentService assessmentService;

    @Autowired
    AssignmentService assignmentService;

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

        List<GroupCourse> listOfGroupCourse = groupCourseService.getByTeacherIDAndSchoolId(teacherID, schoolID);

        System.out.println(Arrays.toString(listOfGroupCourse.toArray()));

        for (GroupCourse value : listOfGroupCourse) {
            if (!value.getFinished()) {
                List<Student> listOfStudents = studentService.getGroupStudents(value.getGroupId());

                System.out.println(Arrays.toString(listOfStudents.toArray()));

                if (listOfStudents.size() != 0) {
                    for (Student listOfStudent : listOfStudents) {
                        csvWriter.append(listOfStudent.getFirstName());
                        csvWriter.append(DEFAULT_SEPARATOR);
                        csvWriter.append(listOfStudent.getLastName());
                        csvWriter.append(DEFAULT_SEPARATOR);
                        csvWriter.append(listOfStudent.getEmail());
                        csvWriter.append(DEFAULT_SEPARATOR);
                        csvWriter.append(groupService.getGroup(value.getGroupId()).getName());
                        csvWriter.append(DEFAULT_SEPARATOR);

                        Course course = courseService.getCourse(value.getCourseId());

                        csvWriter.append(course.getName());
                        csvWriter.append(DEFAULT_SEPARATOR);

                        List<Lesson> listOfLessons = course.getListOfLessons();

                        System.out.println(Arrays.toString(listOfLessons.toArray()));

                        for (int i = 0; i < listOfLessons.size(); i++) {
                            csvWriter.append(listOfLessons.get(i).getName());
                            csvWriter.append(DEFAULT_SEPARATOR);

                            Assignment assignment = assignmentService.getAssignmentByLessonIdAndTeacherId(listOfLessons.get(i).getId(), teacherID);

                            if (assignment != null) {
                                System.out.println(assignment.toString());
                                csvWriter.append(assignment.getTitle());
                                csvWriter.append(DEFAULT_SEPARATOR);

                                Assessment assessment = assessmentService.getAssessmentObjByStudentIdAndAssignmentId(listOfStudent.getId(), assignment.getId());

                                if (assessment != null) {
                                    System.out.println(assessment.toString());
                                    csvWriter.append(assessment.getComment());
                                    csvWriter.append(DEFAULT_SEPARATOR);
                                    csvWriter.append(assessment.getScore().toString());
                                    csvWriter.append(DEFAULT_SEPARATOR);
                                } else {
                                    csvWriter.append("-");
                                    csvWriter.append(DEFAULT_SEPARATOR);
                                    csvWriter.append("-");
                                    csvWriter.append(DEFAULT_SEPARATOR);
                                }
                                csvWriter.append(LINE_DIVIDER);
                                if (listOfLessons.size() - 1 != i) {
                                    for (int j = 0; j < 5; j++) {
                                        csvWriter.append(DEFAULT_SEPARATOR);
                                    }
                                }

                            } else {
                                for (int j = 0; j < 3; j++) {
                                    csvWriter.append("-");
                                    csvWriter.append(DEFAULT_SEPARATOR);
                                }
                                csvWriter.append(LINE_DIVIDER);
                                if (listOfLessons.size() - 1 != i) {
                                    for (int j = 0; j < 5; j++) {
                                        csvWriter.append(DEFAULT_SEPARATOR);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        csvWriter.close();
        return file;
    }

}
