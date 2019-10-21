package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Group;

public interface GroupDAO {

    Integer addGroup(Group group);

    List<Integer> addGroups(List<Group> groups);

    Group getGroup(Integer id);

    List<Group> getGroups();

    List<Group> getGroupsBySchoolId(Integer schoolId);

	List<Group> getGroupsByStudentId(Integer studentId);

	Integer updateGroupName(Integer id, String name);

    Integer deleteGroup(Integer id);
}