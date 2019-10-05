package am.dproc.sms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
//	@Autowired
//	DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { // auth.inMemoryAuthentication() // .withUser("user") //
	 * .password("user") // .roles("USER") // .and() // .withUser("admin") //
	 * .password("admin") // .roles("ADMIN") // .and() // .withUser("test") //
	 * .password("test") // .roles("DIRECTOR");
	 *
	 * auth.jdbcAuthentication() .dataSource(dataSource)
	 * .usersByUsernameQuery("select name, password, status from student where email = ?"
	 * )
	 * .authoritiesByUsernameQuery("select name, info from student where name = ?");
	 *
	 *
	 * }
	 */	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().httpBasic().disable()
			.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasAnyRole("USER", "ADMIN")
			.antMatchers("/").permitAll()
			.and().formLogin().loginPage("/login").permitAll();
	}


	@Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
    
}
