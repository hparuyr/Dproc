package am.dproc.sms.db;

import java.util.List;

import am.dproc.sms.modules.GroupCourseBean;

public interface GroupCourseDAO {
	public int create(int groupId, int courseId, int teacherId, long startDate, boolean isFinished);
	public List<GroupCourseBean> getByGroupID(int groupId);
	public List<GroupCourseBean> getByCourseID(int courseId);
	public List<GroupCourseBean> getAll();
}
