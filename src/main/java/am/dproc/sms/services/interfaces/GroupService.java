package am.dproc.sms.services.interfaces;

import java.util.List;

import am.dproc.sms.models.Group;

public interface GroupService {
	public int create(String name, int schoolId);
	public Group get(int id) ;
	public List<Group> getAll();	
}
