package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.GroupCourse;

public interface GroupCourseDAO {
	
	public int create(GroupCourse groupCourse);

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
