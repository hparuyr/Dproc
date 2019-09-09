package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.root.GroupCourseDAO;
import am.dproc.sms.models.GroupCourse;
import am.dproc.sms.services.root.GroupCourseService;

@Service
public class GroupCourseServiceImpl implements GroupCourseService{
	@Autowired
	GroupCourseDAO dao;


	@Override
	public int create(GroupCourse groupCourse) {
		return dao.create(groupCourse);
	}

	@Override
	public GroupCourse getById(int id) {
		return dao.getById(id);
	}

	@Override
	public GroupCourse getByGroupAndCourse(int groupId, int courseId) {
		return dao.getByGroupAndCourse(groupId, courseId);
	}

	@Override
	public List<GroupCourse> getByGroupID(int groupId) {
		return dao.getByGroupID(groupId);
	}

	@Override
	public List<GroupCourse> getByCourseID(int courseId) {
		return dao.getByCourseID(courseId);
	}

	@Override
	public List<GroupCourse> getAll() {
		return dao.getAll();
	}
	
	@Override
	public int update(GroupCourse groupCourse) {
		return dao.update(groupCourse);
	}

	@Override
	public int deleteByGroupID(int groupId) {
		return dao.deleteByGroupID(groupId);
	}

	@Override
	public int deleteByCourseID(int courseId) {
		return dao.deleteByCourseID(courseId);
	}

	@Override
	public int deleteAll() {
		return dao.deleteAll();
	}
	
	
}
