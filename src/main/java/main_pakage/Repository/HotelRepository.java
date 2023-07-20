package main_pakage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main_pakage.Model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel , Integer>{

}
