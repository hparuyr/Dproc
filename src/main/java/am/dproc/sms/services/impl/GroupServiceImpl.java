package am.dproc.sms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.GroupDAO;
import am.dproc.sms.models.Group;
import am.dproc.sms.services.interfaces.GroupService;

@Service
public class GroupServiceImpl implements GroupService{
	@Autowired
	GroupDAO dao;
	
	public int create(String name, int schoolId) {
		return dao.create(name, schoolId);
	}
	
	public Group get(int id) {
		return dao.get(id);
	}

	public List<Group> getAll() {
		return dao.getAll();
	}
	
	
}
