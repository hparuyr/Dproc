package am.dproc.sms.db;

import am.dproc.sms.modules.GroupCourseBean;

public interface GroupCourseDAO {
	public int create(int groupId, int courseId, long start, boolean finished, int teacherId);
	public GroupCourseBean getByGroupID(int groupId);
	public GroupCourseBean getByCourseID(int courseId);
}
