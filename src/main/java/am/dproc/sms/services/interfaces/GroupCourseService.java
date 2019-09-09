package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.GroupCourse;

public interface GroupCourseService {
	public int create(int groupId, int courseId, int teacherId, long startDate, boolean isFinished);
	public GroupCourse getById(int id);
	public GroupCourse getByGroupAndCourse(int groupId, int courseId);
	public List<GroupCourse> getByGroupID(int groupId);
	public List<GroupCourse> getByCourseID(int courseId);
	public List<GroupCourse> getAll();
	public int update(GroupCourse groupCourse);
	public int deleteByGroupID(int groupId);
	public int deleteByCourseID(int courseId);
	public int deleteAll();
}
