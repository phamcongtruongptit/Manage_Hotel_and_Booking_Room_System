package main_pakage.Controller.Seller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import main_pakage.Model.BookedRoom;
import main_pakage.Model.Booking;
import main_pakage.Model.User;
import main_pakage.Service.BookedRoomService;
import main_pakage.Service.BookingService;
import main_pakage.Service.UserService;

@Controller
@RequestMapping("/seller/history")
public class HistoryController {
	
	@Autowired
	private BookingService bookingService ; 
	@Autowired
	private BookedRoomService bookedRoomService  ; 
	@Autowired
	private UserService userService ; 
	
	@GetMapping("")
	public String viewHistoryBookingMySelf(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication()  ; // ok ??
		UserDetails userDetails = (UserDetails) authentication.getPrincipal() ; 
		User user = userService.findByUsernameOrEmail(userDetails.getUsername()) ;
		
		List<Booking> list = bookingService.getBookingOffUser(user.getId()) ; 
		model.addAttribute("listOfBookings", list) ; 
		return "Seller/BookingList" ; 
	}
	
	@PostMapping()
	public String viewDetailOfBooking(Model model , @RequestParam(name = "bookingID") int bookingID) {
		List<BookedRoom> list = bookedRoomService.getBookedRoomOfBooking(bookingID) ; 
		model.addAttribute("listBookedRoomOfBooking", list) ; 
		return "Seller/BookingDetail" ; 
	}
	
}














