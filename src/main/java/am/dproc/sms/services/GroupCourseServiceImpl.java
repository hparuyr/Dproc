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
	public int create(int groupId, int courseId, int teacherId, long startDate, boolean isFinished) {
		return dao.create(groupId, courseId, teacherId, startDate, isFinished);
	}

	@Override
	public List<GroupCourseBean> getByGroupID(int groupId) {
		return dao.getByGroupID(groupId);
	}

	@Override
	public List<GroupCourseBean> getByCourseID(int courseId) {
		return dao.getByCourseID(courseId);
	}

	@Override
	public List<GroupCourseBean> getAll() {
		return dao.getAll();
	}
	
	
}
