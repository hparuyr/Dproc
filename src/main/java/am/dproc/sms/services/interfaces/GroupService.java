package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Group;

public interface GroupService {

	Integer addGroup(Group group);

	List<Integer> addGroups(List<Group> groups);

	Group getGroup(Integer id);

	List<Group> getSchoolGroups(Integer schoolID);

	Group getGroupsByIDAndSchoolID(Integer id, Integer schoolID);

	List<Group> getGroups();

	List<Group> getGroupsByStudentId(Integer studentID);

	Integer updateGroup(Group group);

	Integer deleteGroup(Integer id);
}
