package am.dproc.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.CourseDAO;
import am.dproc.sms.modules.CourseBean;

@Service
public class CourseServiceImpl implements CourseService{
	@Autowired
	CourseDAO dao;

	@Override
	public int create(String name, int duration, String description, String location) {
		return dao.create(name, duration, description, location);
	}

	@Override
	public CourseBean getById(int id) {
		return dao.getById(id);
	}

	@Override
	public List<CourseBean> getByName(String name) {
		return dao.getByName(name);
	}

	@Override
	public List<CourseBean> getAll() {
		return dao.getAll();
	}
}
