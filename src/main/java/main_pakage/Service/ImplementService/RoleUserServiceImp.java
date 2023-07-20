package main_pakage.Service.ImplementService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main_pakage.Model.Role;
import main_pakage.Model.User;
import main_pakage.Repository.RoleUserRepository;
import main_pakage.Service.RoleUserService;

@Service
public class RoleUserServiceImp  implements RoleUserService {
 
	@Autowired
	private RoleUserRepository roleUserRepository ;

	@Override
	public List<Role> getRoleOfUser(User user) {
		return roleUserRepository.getRoleOfUser(user);
	}

	@Override
	public List<User> getUserHasRoleManager() {
		return roleUserRepository.getUserHasRoleManager();
	} 
	
	
	
}
