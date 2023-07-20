package main_pakage.Service;

import java.util.List;

import main_pakage.Model.BookedRoom;

public interface BookedRoomService {
	
	<S extends BookedRoom> S save(S entity);

	List<BookedRoom> getBookedRoomOfBooking(Integer bookingID);
}
