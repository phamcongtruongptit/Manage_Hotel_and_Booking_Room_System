package main_pakage.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main_pakage.Model.BookedRoom;

@Repository
public interface BookedRoomRepository extends JpaRepository<BookedRoom, Integer>{

	//demo: thong ke doanh thu cua tat ca cac phong. 
	@Query("SELECT room.id , room.name , (SELECT SUM(bookedRoom.price) FROM BookedRoom bookedRoom WHERE bookedRoom.room.id = room.id) as totalPrice FROM Room room "
			+ "WHERE (SELECT SUM(bookedRoom.price) FROM BookedRoom bookedRoom WHERE bookedRoom.room.id = room.id) IS NOT NULL order by totalPrice DESC" )
	List<Object[]> getDemoComplexQuery() ; 
	
	
	@Query("SELECT b FROM BookedRoom b WHERE b.booking.id = :bookingID")
	List<BookedRoom> getBookedRoomOfBooking(@Param("bookingID") int bookingID) ; 
	
	
}
