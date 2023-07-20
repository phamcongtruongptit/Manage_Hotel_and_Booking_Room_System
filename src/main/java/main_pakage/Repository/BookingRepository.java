package main_pakage.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main_pakage.DTO.BookingOfRoomDTO;
import main_pakage.Model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{
	@Query("select max(booking.id) from Booking booking ") 
	Optional<Integer> idMax() ; 
	
	@Query("SELECT new main_pakage.DTO.BookingOfRoomDTO (br.id , c.fullname, (CASE WHEN br.checkin > :startDate THEN br.checkin ELSE :startDate END) as Day_Start, (CASE WHEN br.checkout< :endDate THEN  br.checkout ELSE :endDate END) as Day_End , \r\n"
			+ "r.price , DATEDIFF( LEAST(:endDate , br.checkout ) , GREATEST(:startDate , br.checkin) ) as TotalDays , \r\n"
			+ "(r.price  *  DATEDIFF( LEAST(:endDate , br.checkout ) , GREATEST(:startDate , br.checkin) ) ) as income ) \r\n"
			+ "FROM Booking B , Client c, BookedRoom br, Room r \r\n"
			+ "WHERE br.room.id = :idRoom and c.id = B.client.id and B.id  = br.booking.id and br.room.id = r.id and br.checkin < :endDate and br.checkout > :startDate  order by income DESC")
	List<BookingOfRoomDTO> getBookingOfRoom(@Param("idRoom") int idRoom , @Param("startDate") Date startDate , @Param("endDate") Date endDate) ; 
	
	
	@Query("SELECT b FROM Booking b WHERE b.user.id = :userID")
	List<Booking> getBookingOfUser(@Param("userID") int userID) ; 
	
}

