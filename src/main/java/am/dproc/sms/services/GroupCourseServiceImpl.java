package am.dproc.sms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.GroupCourseDAO;

@Service
public class GroupCourseServiceImpl implements GroupCourseService{
	@Autowired
	GroupCourseDAO dao;
	
	@Override
	public int create(int groupId, int courseId) {
		return dao.create(groupId, courseId);
	}
	
}
