package main_pakage.Service.ImplementService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main_pakage.Model.Client;
import main_pakage.Repository.ClientRepository;
import main_pakage.Service.ClientService;

@Service
public class ClientServiceImp  implements ClientService{
	@Autowired
	private ClientRepository clientRepository ;

	@Override
	public List<Client> searchByKey(String key) {
		return clientRepository.searchByKey(key);
	}

	@Override
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	@Override
	public Optional<Client> findById(Integer id) {
		return clientRepository.findById(id);
	}
	
}
