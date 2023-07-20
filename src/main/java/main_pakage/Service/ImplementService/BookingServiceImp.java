package main_pakage.Service.ImplementService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main_pakage.DTO.BookingOfRoomDTO;
import main_pakage.Model.BookedRoom;
import main_pakage.Model.Booking;
import main_pakage.Model.Client;
import main_pakage.Repository.BookingRepository;
import main_pakage.Service.BookingService;

@Service
public class BookingServiceImp  implements BookingService{
	
	@Autowired 
	private BookingRepository bookingRepository ;

	@Override
	public <S extends Booking> S save(S entity) {
		return bookingRepository.save(entity);
	}

	@Override
	public Optional<Integer> idMax() {
		return bookingRepository.idMax();
	}
	
	@Override
	public List<Booking> findAll() {
		List<Booking> list = bookingRepository.findAll() ; 
		for (int k = 0, j = list.size() - 1; k < j; k++){
			list.add(k, list.remove(j));
        }
		return list;
	}
	
	@Override
	public List<Booking> getBookingOffUser(int userID) {
		List<Booking> list = bookingRepository.getBookingOfUser(userID) ; 
		for (int k = 0, j = list.size() - 1; k < j; k++){
			list.add(k, list.remove(j));
        }
		return list;
	}

	//	
//	@Override
//	public List<BookingOfRoomDTO> getBookingOfRoom(int idRoom, Date startDate, Date endDate) {
//		List<BookingOfRoomDTO> list = new ArrayList<>() ; 
//		
//		List<Object[]> listOjects = bookingRepository.getBookingOfRoom(idRoom, startDate, endDate) ; 
//		
//		for (Object[] objects : listOjects) {
//			BookingOfRoomDTO bookingOfRoom = new BookingOfRoomDTO() ; 
//			BookedRoom bookedRoom = new BookedRoom() ; 
//			Client client = new Client() ; 
//			bookedRoom.setId(Integer.parseInt(objects[0].toString()));
//			client.setFullname(objects[1].toString());
//			DateFormat df = new SimpleDateFormat("yyyy-MM-dd") ; 
//			try {
//				bookedRoom.setCheckin(df.parse(objects[2].toString()));
//				bookedRoom.setCheckout(df.parse(objects[3].toString()));
//				bookingOfRoom.setDateStart(df.format(bookedRoom.getCheckin()));
//				bookingOfRoom.setDateEnd(df.format(bookedRoom.getCheckout()));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			bookedRoom.setPrice(Integer.parseInt(objects[4].toString()));
//			bookingOfRoom.setTotalDays(Integer.parseInt(objects[5].toString()));
//			bookingOfRoom.setIncome(Integer.parseInt(objects[6].toString()));
//			bookingOfRoom.setClient(client);
//			bookingOfRoom.getBookedRooms().add(bookedRoom) ; 
//			
//			list.add(bookingOfRoom) ; 
//		}
//		return list ; 
//	} 
	@Override
	public List<BookingOfRoomDTO> getBookingOfRoom(int idRoom, Date startDate, Date endDate) {
		List<BookingOfRoomDTO> list = bookingRepository.getBookingOfRoom(idRoom, startDate, endDate) ; 
		return list ; 
	} 
	
	

}
