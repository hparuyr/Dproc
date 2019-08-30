package am.dproc.sms.services;

import java.util.List;

import am.dproc.sms.modules.CourseBean;

public interface CourseService {
	public int create(String name, int duration, String description, String location);
	public CourseBean getById(int id);
	public List<CourseBean> getByName(String name);
	public List<CourseBean> getAll();
}
