package main_pakage.Service.ImplementService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main_pakage.Model.User;
import main_pakage.Repository.UserRepository;
import main_pakage.Service.UserService;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserRepository userRepository ;

	@Override
	public User findByUsernameOrEmail(String keyword) {
		return userRepository.findByUsernameOrEmail(keyword);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public <S extends User> S save(S entity) {
		return userRepository.save(entity);
	} 
	
	
}
