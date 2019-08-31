package am.dproc.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.GroupCourseDAO;
import am.dproc.sms.modules.GroupCourseBean;

@Service
public class GroupCourseServiceImpl implements GroupCourseService{
	@Autowired
	GroupCourseDAO dao;

	@Override
	public int create(int groupId, int courseId, long start, boolean finished, int teacherId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GroupCourseBean> getByGroupID(int groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupCourseBean> getByCourseID(int courseId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
