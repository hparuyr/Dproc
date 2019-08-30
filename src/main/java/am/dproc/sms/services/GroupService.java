package am.dproc.sms.services;

import java.util.List;

import am.dproc.sms.modules.GroupBean;

public interface GroupService {
	public int create(String name, int schoolId);
	public GroupBean get(int id) ;
	public List<GroupBean> getAll();	
}
