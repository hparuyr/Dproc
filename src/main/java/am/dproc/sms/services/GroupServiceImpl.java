package am.dproc.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.GroupDAO;
import am.dproc.sms.modules.GroupBean;

@Service
public class GroupServiceImpl implements GroupService{
	@Autowired
	GroupDAO dao;
	
	public int create(String name, int schoolId) {
		return dao.create(name, schoolId);
	}
	
	public GroupBean get(int id) {
		return dao.get(id);
	}

	public List<GroupBean> getAll() {
		return dao.getAll();
	}
	
	
}
