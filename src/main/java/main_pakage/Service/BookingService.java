package main_pakage.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import main_pakage.DTO.BookingOfRoomDTO;
import main_pakage.Model.Booking;

public interface BookingService {
	
	<S extends Booking> S save(S entity);

	Optional<Integer> idMax();

	List<BookingOfRoomDTO> getBookingOfRoom(int idRoom, Date startDate, Date endDate);

	List<Booking> findAll();

	List<Booking> getBookingOffUser(int userID);

}
