package am.dproc.sms.services.impl;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import am.dproc.sms.db.interfaces.StudentDAO;
import am.dproc.sms.models.Student;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	StudentDAO studentDao;

	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Student student = studentDao.getStudentByEmail(username);

		if (student == null) {
			throw new UsernameNotFoundException(username);
		}

		HashSet<GrantedAuthority> authorities = new HashSet<>();
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
		authorities.add(authority);
		
		return new User(student.getEmail(), student.getPassword(), authorities);
	}

}