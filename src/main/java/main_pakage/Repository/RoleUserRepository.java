package main_pakage.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import main_pakage.Model.Role;
import main_pakage.Model.RoleUser;
import main_pakage.Model.User;

public interface RoleUserRepository extends JpaRepository<RoleUser, Integer> {
	
	@Query("SELECT role FROM RoleUser roleUser WHERE roleUser.user = :user")
	List<Role> getRoleOfUser(User user) ; 
	
	@Query("SELECT user FROM RoleUser roleUser WHERE roleUser.role.roleName = 'MANAGER'")
	List<User> getUserHasRoleManager() ; 
	
}