package main_pakage.Service;

import java.util.List;

import main_pakage.Model.Role;
import main_pakage.Model.User;

public interface RoleUserService {

	List<Role> getRoleOfUser(User user);

	List<User> getUserHasRoleManager();

}
