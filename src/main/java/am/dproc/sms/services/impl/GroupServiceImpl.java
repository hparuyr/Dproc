package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.GroupDAO;
import am.dproc.sms.models.Group;
import am.dproc.sms.services.interfaces.GroupService;
import am.dproc.sms.services.interfaces.StudentService;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	GroupDAO groupDAO;

	@Autowired
	StudentService studentService;

	@Override
	public Integer addGroup(Group group) {
		return this.groupDAO.addGroup(group);
	}

	@Override
	public List<Integer> addGroups(List<Group> groups) {
		return groupDAO.addGroups(groups);
	}

	@Override
	public Group getGroup(Integer id) {
		Group group = this.groupDAO.getGroup(id);
		group.setListOfStudents(this.studentService.getGroupStudents(id));
		return group;
	}

	@Override
	public List<Group> getSchoolGroups(Integer schoolId) {
		return this.groupDAO.getGroupsBySchoolId(schoolId);
	}

	@Override
	public Group getGroupsByIDAndSchoolId(Integer id, Integer schoolId){
		return null;
	}

	@Override
	public List<Group> getGroupsByStudentId(Integer studentId) {
		return groupDAO.getGroupsByStudentId(studentId);
	}

	@Override
	public List<Group> getGroups() {
		return groupDAO.getGroups();
	}

	@Override
	public Integer updateGroup(Group group) {
		if (group.getName() != null) {
			return this.groupDAO.updateGroupName(group.getId(), group.getName());
		}
		return 0;
	}

	@Override
	public Integer deleteGroup(Integer id) {
		return this.groupDAO.deleteGroup(id);
	}
}
