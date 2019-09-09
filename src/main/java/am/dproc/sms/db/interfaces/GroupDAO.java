package am.dproc.sms.db.root;


import java.util.List;

import am.dproc.sms.models.Group;

public interface GroupDAO {

	public Integer addGroup(Group group);

	public Group getGroup(Integer id);

	public List<Group> getGroups();
	
	public List<Group> getGroupsBySchoolId(Integer schoolId);

	public 	Integer updateGroupName(Integer id, String name );

	public Integer deleteGroup(Integer id);

}
