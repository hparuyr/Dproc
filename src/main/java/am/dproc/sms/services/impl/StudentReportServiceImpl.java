package am.dproc.sms.services.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import am.dproc.sms.models.Course;
import am.dproc.sms.models.Student;
import am.dproc.sms.models.SurveyResult;
import am.dproc.sms.services.interfaces.AssessmentService;
import am.dproc.sms.services.interfaces.CourseService;
import am.dproc.sms.services.interfaces.GroupCourseService;
import am.dproc.sms.services.interfaces.StudentReportService;
import am.dproc.sms.services.interfaces.StudentService;
import am.dproc.sms.services.interfaces.SurveyResultService;
import am.dproc.sms.services.interfaces.TestResultService;

@Service
public class StudentReportServiceImpl implements StudentReportService {

	@Override
	public File getStudentReport(Integer studentId) {
		return null;
	}
	/*
	@Autowired
	StudentService studentService;
	@Autowired
	CourseService courseService;
	@Autowired
	GroupCourseService groupCourseService;
	@Autowired
	TestResultService testResultService;
	@Autowired
	AssessmentService assessmentService;
	@Autowired
	SurveyResultService surveyResultService;

	@Override
	public File getStudentReport(Integer studentId) {
		File pdf = null;
		try {
			pdf = File.createTempFile("test", "pdf");
			PdfWriter writer = new PdfWriter(pdf);
			PdfDocument pdfDoc = new PdfDocument(writer);

			Document doc = new Document(pdfDoc);
			Student studentService = studentService.getStudent(studentId);
			addPersonalData(doc, studentService);
			addProfileData(doc, studentService);
			addCoursesData(doc, studentService);
			addTotalAVGScores(doc, studentService);

			doc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pdf;
	}

	private void addTotalAVGScores(Document doc, Student studentService) {
		doc.add(new Paragraph("AVG score for all courses").setBold().setTextAlignment(TextAlignment.CENTER));
		Table scores = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
		scores.addCell("Test");
		scores.addCell("Assessment");
		Double avgTestScore = testResultService.getAverageTestResultForStudent(studentService.getId());
		scores.addCell(avgTestScore == null ? "0" : avgTestScore.toString());
		Double avgAssessmentScore = assessmentService.getAverageScoreByStudent(studentService.getId());
		scores.addCell(avgAssessmentScore == null ? "0" : avgAssessmentScore.toString());

		doc.add(scores);
		doc.add(new Paragraph(""));
		doc.add(new Paragraph(""));
	}

	private void addProfileData(Document doc, Student studentService) {
		doc.add(new Paragraph("Profile").setBold().setTextAlignment(TextAlignment.CENTER));
		SurveyResult result = surveyResultService.getByStudentId(studentService.getId());

		Table profile = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();
		profile.addCell("Ext");
		profile.addCell("Est");
		profile.addCell("Agr");
		profile.addCell("Csn");
		profile.addCell("Opn");

		profile.addCell(String.valueOf(result.getExt()));
		profile.addCell(String.valueOf(result.getEst()));
		profile.addCell(String.valueOf(result.getAgr()));
		profile.addCell(String.valueOf(result.getCsn()));
		profile.addCell(String.valueOf(result.getOpn()));

		doc.add(profile);
		doc.add(new Paragraph(""));
		doc.add(new Paragraph(""));
	}

	private void addCoursesData(Document doc, Student studentService) {
		doc.add(new Paragraph("Courses").setBold().setTextAlignment(TextAlignment.CENTER));

		List<Course> courses = courseService.getCoursesByGroupId(studentService.getGroupId());

		Table coursesTable = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();
		coursesTable.addCell("Name");
		coursesTable.addCell("Description");
		coursesTable.addCell("Start date");
		coursesTable.addCell("AVG test score");
		coursesTable.addCell("AVG homework score");

		Integer studentId1 = studentService.getId();
		for (Course course : courses) {
			Integer courseId = course.getId();
			coursesTable.addCell(course.getName());
			coursesTable.addCell(course.getDescription());
			Date startDate = new Date(
					groupCourseService.getByGroupAndCourse(studentService.getGroupId(), courseId).getStartDate());
			coursesTable.addCell(startDate.toString());
			Double testAvgScore = testResultService.getAverageTestResultForStudentCourse(studentId1, courseId);
			coursesTable.addCell(testAvgScore == null ? "0" : testAvgScore.toString());
			Double assessmentAvgScore = assessmentService.getAverageScoreByStudentCourse(studentId1, courseId);
			coursesTable.addCell(assessmentAvgScore == null ? "0" : assessmentAvgScore.toString());
		}

		doc.add(coursesTable);
		doc.add(new Paragraph(""));
		doc.add(new Paragraph(""));
	}

	private void addPersonalData(Document doc, Student studentService) {
		Paragraph title = new Paragraph(String.format("%s %s", studentService.getFirstName(), studentService.getLastName())).setBold()
				.setTextAlignment(TextAlignment.CENTER);
		doc.add(title);

		try {
			String url = studentService.getStudentInfo().getImageUrl();
			ImageData imageData = ImageDataFactory.add(url);
			Image pdfImg = new Image(imageData).setAutoScaleHeight(true).setAutoScaleWidth(true); //.setAutoScale(true); //.setWidth(100).setHeight(90);
			doc.add(pdfImg);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		doc.add(new Paragraph(String.format("e-Mail\t%s", studentService.getEmail())));
		doc.add(new Paragraph(String.format("Phone\t%s", "-")));
		doc.add(new Paragraph(String.format("Date of Birth\t%s", "-")));
		doc.add(new Paragraph(String.format("Admission date\t%s", "-")));
		doc.add(new Paragraph(String.format("Group\t%s", studentService.getGroupId())));

		doc.add(new Paragraph(""));
		doc.add(new Paragraph(""));
	}
	 */
}
