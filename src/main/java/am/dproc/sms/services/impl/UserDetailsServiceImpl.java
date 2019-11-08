package am.dproc.sms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.StudentDAO;
import am.dproc.sms.models.Student;
import am.dproc.sms.models.UserPrincipal;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	StudentDAO studentDao;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Student student = studentDao.getStudentByEmail(username);

		if (student == null) {
			throw new UsernameNotFoundException(username);
		}

			return new UserPrincipal(student);
	}

}
