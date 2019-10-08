package am.dproc.sms.services.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.models.Assignment;
import am.dproc.sms.models.GroupCourse;
import am.dproc.sms.models.Lesson;
import am.dproc.sms.models.Student;
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
	private static final String LINE_DEVIDER = "\n";


	@Override
	public File getCSVFile(Integer teacherID) throws IOException {
		File file = new File(("src/main/info" + System.currentTimeMillis() + ".csv"));
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write("STUDENT NAME");
		fileWriter.write(DEFAULT_SEPARATOR);
		fileWriter.write("STUDENT SURNAME");
		fileWriter.write(DEFAULT_SEPARATOR);
		fileWriter.write("STUDENT EMAIL");
		fileWriter.write(DEFAULT_SEPARATOR);
		fileWriter.write("GROUP NAME");
		fileWriter.write(DEFAULT_SEPARATOR);
		fileWriter.write("COURSE NAME");
		fileWriter.write(DEFAULT_SEPARATOR);
		fileWriter.write("LESSON TITLE");
		fileWriter.write(DEFAULT_SEPARATOR);
		fileWriter.write("ASSIGNMENT TITLE");
		fileWriter.write(DEFAULT_SEPARATOR);
		fileWriter.write("ASSIGNMENT FEEDBACK");
		fileWriter.write(DEFAULT_SEPARATOR);
		fileWriter.write("ASSESSMENT");
		fileWriter.write(LINE_DEVIDER);
		List<GroupCourse> listOfGroupCourse = groupCourse.getByTeacherID(teacherID);
		System.out.println(Arrays.toString(listOfGroupCourse.toArray()));
		for (int i = 0; i < listOfGroupCourse.size(); i++) {
			List<Student> listOfStudents = student.getGroupStudents(listOfGroupCourse.get(i).getGroupId());
			System.out.println(Arrays.toString(listOfStudents.toArray()));
			for (int j = 0; j < listOfStudents.size(); j++) {
				fileWriter.write(listOfStudents.get(j).getFirstname());
				fileWriter.write(DEFAULT_SEPARATOR);
				fileWriter.write(listOfStudents.get(j).getLastname());
				fileWriter.write(DEFAULT_SEPARATOR);
				fileWriter.write(listOfStudents.get(j).getEmail());
				fileWriter.write(DEFAULT_SEPARATOR);
				fileWriter.write(group.getGroup(listOfGroupCourse.get(i).getGroupId()).getName());
				fileWriter.write(DEFAULT_SEPARATOR);
				fileWriter.write(course.getCourse(listOfGroupCourse.get(i).getCourseId()).getName());
				fileWriter.write(DEFAULT_SEPARATOR);
				List<Lesson> listOfLessons = lesson.getCourseLessons(listOfGroupCourse.get(i).getCourseId());
				System.out.println(Arrays.toString(listOfLessons.toArray()));
				for (int k = 0; k < listOfLessons.size(); k++) {
					Assignment assignment = this.assignment.getAssignmentByLessonID(listOfLessons.get(k).getId(), teacherID);
					fileWriter.write(listOfLessons.get(k).getName());
					fileWriter.write(DEFAULT_SEPARATOR);
					fileWriter.write(assignment.getTitle());
					//fileWriter.write(DEFAULT_SEPARATOR);
					//fileWriter.write(this.assignment.getAssignmentComment(assignment.getId()));
					fileWriter.write(DEFAULT_SEPARATOR);
					fileWriter.write(assessment.getAssessmentByStudentIDAndAssignmentID(listOfStudents.get(j).getId(), listOfLessons.get(k).getId()).toString());
					fileWriter.write(LINE_DEVIDER);
					for (int l = 0; l < 5; l++) {
						if (k != listOfLessons.size() - 1) {
							fileWriter.write("");
							fileWriter.write(DEFAULT_SEPARATOR);
						}
					}
				}
			}
		}
		fileWriter.close();
		return file;
	}

}
