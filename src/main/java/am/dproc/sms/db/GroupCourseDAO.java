package am.dproc.sms.db;

import java.util.List;

import am.dproc.sms.modules.GroupCourseBean;

public interface GroupCourseDAO {
	public int create(int groupId, int courseId, long start, boolean finished, int teacherId);
	public List<GroupCourseBean> getByGroupID(int groupId);
	public List<GroupCourseBean> getByCourseID(int courseId);
}
