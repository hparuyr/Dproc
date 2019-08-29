package am.dproc.sms.db;

import am.dproc.sms.modules.GroupBean;

public interface GroupDAO {
	public int create(String name, int schoolId);
}
