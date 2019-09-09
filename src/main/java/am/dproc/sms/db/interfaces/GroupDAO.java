package am.dproc.sms.db.interfaces;

import java.util.List;

import am.dproc.sms.models.Group;

public interface GroupDAO {
	
	public int create(String name, int schoolId);

	public Group get(int id);

	public List<Group> getAll();
}
