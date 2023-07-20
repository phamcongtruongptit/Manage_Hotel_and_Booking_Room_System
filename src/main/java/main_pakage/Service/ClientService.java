package main_pakage.Service;

import java.util.List;
import java.util.Optional;

import main_pakage.Model.Client;

public interface ClientService {

	Optional<Client> findById(Integer id);
	
	List<Client> findAll();
	
	List<Client> searchByKey(String key);
}
