package am.dproc.sms.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import am.dproc.sms.rest.AdminController;
import am.dproc.sms.rest.AnswerController;
import am.dproc.sms.rest.CourseController;
import am.dproc.sms.rest.GroupController;
import am.dproc.sms.rest.GroupCourseController;
import am.dproc.sms.rest.LessonController;
import am.dproc.sms.rest.QuestionController;
import am.dproc.sms.rest.SchoolController;
import am.dproc.sms.rest.StudentController;
import am.dproc.sms.rest.TeacherController;
import am.dproc.sms.rest.TestController;
import am.dproc.sms.rest.TopicController;

@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(SchoolController.class);
		register(GroupController.class);
		register(AdminController.class);
		register(TeacherController.class);
		register(StudentController.class);
		register(GroupCourseController.class);
		register(CourseController.class);
		register(LessonController.class);
		register(TopicController.class);
		register(AnswerController.class);
		register(QuestionController.class);
		register(TestController.class);
		
	}
}
