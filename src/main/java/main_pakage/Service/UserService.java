package main_pakage.Service;

import java.util.List;

import main_pakage.Model.User;

public interface UserService {

	User findByUsernameOrEmail(String keyword);

	List<User> findAll();

	<S extends User> S save(S entity);

}
