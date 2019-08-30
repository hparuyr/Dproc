package am.dproc.sms.db;

import java.util.List;

import am.dproc.sms.modules.GroupBean;

public interface GroupDAO {
	public int create(String name, int schoolId);
	public GroupBean get(int id);
	public List<GroupBean> getAll();
}
