package main_pakage.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import main_pakage.Model.Role;
import main_pakage.Model.User;

@Service
public class UserDetailServiceImp implements UserDetailsService{

	@Autowired
	private UserService userService ; 
	@Autowired
	private RoleUserService roleUserService  ; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsernameOrEmail(username) ; 
		if(user == null) {
			System.out.println("User: " + username + " is not found !");
			throw new UsernameNotFoundException("User Name Not Found") ;
		}
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword() , getRoleOfUser(user) ) ; 
		
		return userDetails;
	}

	private Collection<? extends GrantedAuthority> getRoleOfUser(User user) {
		Set<GrantedAuthority> setRoles = new HashSet<>() ; 
		List<Role> listRoles = roleUserService.getRoleOfUser(user) ; 
		listRoles.forEach(item -> {
			setRoles.add(new SimpleGrantedAuthority(item.getRoleName())) ; 
		});
		return setRoles ;
	}

}



















