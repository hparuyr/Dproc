package am.dproc.sms.services;

import java.util.List;

import am.dproc.sms.modules.GroupCourseBean;

public interface GroupCourseService {
	public int create(int groupId, int courseId, int teacherId, long startDate, boolean isFinished);
	public GroupCourseBean getById(int id);
	public GroupCourseBean getByGroupAndCourse(int groupId, int courseId);
	public List<GroupCourseBean> getByGroupID(int groupId);
	public List<GroupCourseBean> getByCourseID(int courseId);
	public List<GroupCourseBean> getAll();
	public int update(GroupCourseBean groupCourseBean);
	public int deleteByGroupID(int groupId);
	public int deleteByCourseID(int courseId);
	public int deleteAll();
}
