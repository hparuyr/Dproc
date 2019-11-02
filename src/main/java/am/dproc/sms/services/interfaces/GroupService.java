package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Group;

public interface GroupService {

	Integer addGroup(Group group);

	List<Integer> addGroups(List<Group> groups);

	Group getGroup(Integer id);

	List<Group> getSchoolGroups(Integer schoolId);

	Group getGroupsByIDAndSchoolId(Integer id, Integer schoolId);

	List<Group> getGroups();

	List<Group> getGroupsByStudentId(Integer studentId);

	Integer updateGroup(Group group);

	Integer deleteGroup(Integer id);
}
