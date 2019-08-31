package am.dproc.sms.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import am.dproc.sms.rest.CourseController;
import am.dproc.sms.rest.GroupController;
import am.dproc.sms.rest.GroupCourseController;
import am.dproc.sms.rest.LessonController;
import am.dproc.sms.rest.TopicController;

@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(GroupController.class);
		register(GroupCourseController.class);
		register(CourseController.class);
		register(LessonController.class);
		register(TopicController.class);
	}
}
