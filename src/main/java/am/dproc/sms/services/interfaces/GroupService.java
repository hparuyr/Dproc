package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Group;

public interface GroupService {

	public Integer addGroup(Group group);

	public List<Integer> addGroups(List<Group> groups);

	public Group getGroup(Integer id);

	public List<Group> getSchoolGroups(Integer schoolId);

	public List<Group> getGroupsByStudentId(Integer studentId);

	public List<Group> getGroups();

	public Integer updateGroup(Group group);

	public Integer deleteGroup(Integer id);
}
