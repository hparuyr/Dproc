package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.GroupDAO;
import am.dproc.sms.models.Group;
import am.dproc.sms.services.interfaces.GroupService;
import am.dproc.sms.services.interfaces.StudentService;

@Service
public class GroupServiceImpl implements GroupService{
	@Autowired
	GroupDAO group;
	
	@Autowired
	StudentService student;

	@Override
	public Integer addGroup(Group group) {
		return this.group.addGroup(group);
	}

	@Override
	public Group getGroup(Integer id) {
		Group group = this.group.getGroup(id);
		group.setListOfStudents(this.student.getGroupStudents(id));
		return group;
	}
	@Override
	public List<Group> getSchoolGroups(Integer schoolId) {
			return this.group.getGroupsBySchoolId(schoolId);
		}
	@Override
	public List<Group> getGroups() {
		return group.getGroups();
	}

	@Override
	public Integer updateGroup(Group group) {
		if (group.getName() != null) {
			return this.group.updateGroupName(group.getId(), group.getName());
		}
		return 0;
	}

	@Override
	public Integer deleteGroup(Integer id) {
		return this.group.deleteGroup(id);
	}
}
