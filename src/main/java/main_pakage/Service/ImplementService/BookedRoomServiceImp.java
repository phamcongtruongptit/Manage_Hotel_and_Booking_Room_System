package main_pakage.Service.ImplementService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main_pakage.Model.BookedRoom;
import main_pakage.Repository.BookedRoomRepository;
import main_pakage.Service.BookedRoomService;

@Service
public class BookedRoomServiceImp implements BookedRoomService{
	@Autowired
	private BookedRoomRepository bookedRoomRepository ;

	@Override
	public <S extends BookedRoom> S save(S entity) {
		return bookedRoomRepository.save(entity);
	}

	@Override
	public List<BookedRoom> getBookedRoomOfBooking(Integer bookingID) {
		List<BookedRoom> list = bookedRoomRepository.getBookedRoomOfBooking(bookingID) ; 
		return list;
	}

	
	

//	@Override
//	public List<Object[]> getDemoComplexQuery() {
//		return bookedRoomRepository.getDemoComplexQuery();
//	}
	
}
