package am.dproc.sms.services;

import java.util.List;

import am.dproc.sms.modules.GroupCourseBean;

public interface GroupCourseService {
	public int create(int groupId, int courseId, long start, boolean finished, int teacherId);
	public List<GroupCourseBean> getByGroupID(int groupId);
	public List<GroupCourseBean> getByCourseID(int courseId);
}
