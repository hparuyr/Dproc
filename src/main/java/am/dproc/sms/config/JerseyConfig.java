package am.dproc.sms.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import am.dproc.sms.rest.GroupCourseController;

@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(GroupCourseController.class);
	}
}
