package am.dproc.sms.config;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import am.dproc.sms.rest.AdminController;
import am.dproc.sms.rest.AdminInfoController;
import am.dproc.sms.rest.AnswerController;
import am.dproc.sms.rest.AssessmentController;
import am.dproc.sms.rest.AssignmentController;
import am.dproc.sms.rest.ClassroomController;
import am.dproc.sms.rest.CourseController;
import am.dproc.sms.rest.GroupController;
import am.dproc.sms.rest.GroupCourseController;
import am.dproc.sms.rest.LessonController;
import am.dproc.sms.rest.QuestionController;
import am.dproc.sms.rest.ScheduleController;
import am.dproc.sms.rest.SchoolController;
import am.dproc.sms.rest.SendEmailController;
import am.dproc.sms.rest.StudentCommentController;
import am.dproc.sms.rest.StudentController;
import am.dproc.sms.rest.StudentInfoController;
import am.dproc.sms.rest.StudentReportController;
import am.dproc.sms.rest.SurveyController;
import am.dproc.sms.rest.TeacherController;
import am.dproc.sms.rest.TeacherInfoController;
import am.dproc.sms.rest.TestController;
import am.dproc.sms.rest.TestResultController;
import am.dproc.sms.rest.TopicController;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(SchoolController.class);
		register(GroupController.class);
		register(AdminController.class);
		register(AdminInfoController.class);
		register(TeacherController.class);
		register(TeacherInfoController.class);
		register(StudentController.class);
		register(StudentInfoController.class);
		register(GroupCourseController.class);
		register(CourseController.class);
		register(LessonController.class);
		register(TopicController.class);
		register(ClassroomController.class);
		register(StudentCommentController.class);
		register(AnswerController.class);
		register(QuestionController.class);
		register(TestController.class);
		register(TestResultController.class);
		register(ScheduleController.class);
		register(AssessmentController.class);
		register(AssignmentController.class);
		register(SurveyController.class);
		register(StudentReportController.class);
		register(SendEmailController.class);
	}

	@PostConstruct
	public void init() {
		this.configureSwagger();
	}

	private void configureSwagger() {
		this.register(ApiListingResource.class);
		this.register(SwaggerSerializers.class);

		BeanConfig swaggerConfigBean = new BeanConfig();
		swaggerConfigBean.setBasePath("/api");
		swaggerConfigBean.setResourcePackage("am.dproc.sms.rest");
		swaggerConfigBean.setPrettyPrint(true);
		swaggerConfigBean.setScan(true);
	}
}
