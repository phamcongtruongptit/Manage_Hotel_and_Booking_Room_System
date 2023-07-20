package main_pakage.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main_pakage.Model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	@Query("SELECT client FROM Client client WHERE client.fullname LIKE %:key% OR client.tel LIKE %:key%")
	List<Client> searchByKey(@Param("key") String key) ; 
}
