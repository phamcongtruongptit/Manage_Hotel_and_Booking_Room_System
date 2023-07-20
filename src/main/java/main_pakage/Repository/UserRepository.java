package main_pakage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import main_pakage.Model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("SELECT user FROM User user WHERE user.username = :keyword or user.email = :keyword ")
	User findByUsernameOrEmail(String keyword) ; 
}
