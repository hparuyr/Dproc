package am.dproc.sms.services.root;

import java.util.List;

import am.dproc.sms.models.Group;

public interface GroupService {
	public int create(String name, int schoolId);
	public Group get(int id) ;
	public List<Group> getAll();	
}
