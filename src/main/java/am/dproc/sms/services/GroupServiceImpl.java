package am.dproc.sms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.GroupDAO;
import am.dproc.sms.modules.GroupBean;

@Service
public class GroupServiceImpl implements GroupCourseService{
	@Autowired
	GroupDAO dao;
	
	public int create(String name) {
		return dao.create(name);
	}
}
