package am.dproc.sms.services;

import java.util.List;

import am.dproc.sms.modules.GroupCourseBean;

public interface GroupCourseService {
	public int create(int groupId, int courseId, int teacherId, long startDate, boolean isFinished);
	public List<GroupCourseBean> getByGroupID(int groupId);
	public List<GroupCourseBean> getByCourseID(int courseId);
	public List<GroupCourseBean> getAll();
}
