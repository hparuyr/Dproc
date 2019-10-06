package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.GroupCourseDAO;
import am.dproc.sms.models.GroupCourse;
import am.dproc.sms.services.interfaces.GroupCourseService;

@Service
public class GroupCourseServiceImpl implements GroupCourseService{

	@Autowired
	GroupCourseDAO groupCourseDAO;

	@Override
	public Integer create(GroupCourse groupCourse) {
		if (groupCourse.getCourseId() == null || groupCourse.getGroupId() == null ||
				groupCourse.getStartDate() == null || groupCourse.getFinished() == null ||
				groupCourse.getTeacherId() == null) {
			return -1;
		}
		return groupCourseDAO.create(groupCourse);
	}

	@Override
	public GroupCourse getById(Integer id) {
		return groupCourseDAO.getById(id);
	}

	@Override
	public GroupCourse getByGroupAndCourse(Integer groupId, Integer courseId) {
		return groupCourseDAO.getByGroupAndCourse(groupId, courseId);
	}

	@Override
	public List<GroupCourse> getByGroupID(Integer groupId) {
		return groupCourseDAO.getByGroupID(groupId);
	}

	@Override
	public List<GroupCourse> getByCourseID(Integer courseId) {
		return groupCourseDAO.getByCourseID(courseId);
	}

	@Override
	public List<GroupCourse> getAll() {
		return groupCourseDAO.getAll();
	}
	
	@Override
	public List<GroupCourse> getByTeacherID(Integer id) {
		return groupCourseDAO.getByTeacherID(id);
	}
	
	@Override
	public Integer update(GroupCourse groupCourse) {
		return groupCourseDAO.update(groupCourse);
	}

	@Override
	public Integer deleteByGroupID(Integer groupId) {
		return groupCourseDAO.deleteByGroupID(groupId);
	}

	@Override
	public Integer deleteByCourseID(Integer courseId) {
		return groupCourseDAO.deleteByCourseID(courseId);
	}

	@Override
	public Integer deleteAll() {
		return groupCourseDAO.deleteAll();
	}
	
	
}
