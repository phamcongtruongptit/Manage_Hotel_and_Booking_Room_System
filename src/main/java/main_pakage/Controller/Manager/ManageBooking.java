package main_pakage.Controller.Manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import main_pakage.Model.BookedRoom;
import main_pakage.Model.Booking;
import main_pakage.Service.BookedRoomService;
import main_pakage.Service.BookingService;

@Controller
@RequestMapping("/manager/bookings")
public class ManageBooking {
	
	@Autowired 
	private BookingService bookingService ; 
	@Autowired
	private BookedRoomService bookedRoomService ; 
	
	@GetMapping("")
	public String getListOfBooking(Model model) {
		
		List<Booking> list = bookingService.findAll() ; 
		model.addAttribute("listOfBookings", list) ; 
		return "Manager/BookingList" ; 
	}
	
	@GetMapping("/{bookingID}")
	public String getListBookedRoomOfBooking(Model model , @PathVariable("bookingID") Integer bookingID ) {
		List<BookedRoom> list = bookedRoomService.getBookedRoomOfBooking(bookingID) ; 
		model.addAttribute("listBookedRoomOfBooking", list) ; 
		return "Manager/ListBookedRoomOfBooking" ; 
	}
}
