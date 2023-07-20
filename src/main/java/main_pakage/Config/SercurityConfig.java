package main_pakage.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import main_pakage.Service.UserDetailServiceImp;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SercurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder() ; 
	}
	
	@Autowired 
	UserDetailServiceImp userDetailServiceImp ; 
	
	@Autowired
	LoginSuccessHandler loginSuccessHandler ; 

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailServiceImp).passwordEncoder(passwordEncoder()) ; 
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/login" , "/home").permitAll() ; 
		
		http.authorizeRequests().antMatchers("/manager/**").hasAnyAuthority("MANAGER") ;
		http.authorizeRequests().antMatchers("/seller/**").hasAnyAuthority("SELLER") ;
		
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403") ; 
		
		http.authorizeRequests().and()
			.formLogin().loginPage("/login").loginProcessingUrl("/authentication")
			.successHandler(loginSuccessHandler)
			.failureUrl("/login?loginError=true")
		.and()
			.logout().logoutUrl("/logout").logoutSuccessUrl("/login") ; 

	}
}















