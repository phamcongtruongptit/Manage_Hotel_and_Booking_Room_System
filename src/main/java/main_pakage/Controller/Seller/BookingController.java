package main_pakage.Controller.Seller;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import main_pakage.DTO.BookedRoomDTO;
import main_pakage.DTO.BookingDTO;
import main_pakage.Model.BookedRoom;
import main_pakage.Model.Client;
import main_pakage.Model.Room;
import main_pakage.Model.User;
import main_pakage.Service.BookedRoomService;
import main_pakage.Service.BookingService;
import main_pakage.Service.ClientService;
import main_pakage.Service.RoomService;
import main_pakage.Service.UserService;

@Controller
@RequestMapping("seller/booking")
public class BookingController {
	
	@Autowired
	private RoomService roomService ; 
	@Autowired
	private HttpSession session ; 
	@Autowired
	private ClientService clientService ; 
	@Autowired 
	private UserService userService ; 
	
	@Autowired 
	private BookingService bookingService ; 
	@Autowired
	private BookedRoomService bookedRoomService ; 
	
	@GetMapping("")
	public String booking( Model model ,@RequestParam(name = "checkIn", required = false) String checkIn , 
			@RequestParam(name = "checkOut" , required = false) String checkOut  ) {
		if( (checkIn!=null && checkOut != null) || (session.getAttribute("checkIn")!=null)) {
			if(session.getAttribute("checkIn")!=null) {
				session.setAttribute("checkIn", session.getAttribute("checkIn") + "");
				session.setAttribute("checkOut", session.getAttribute("checkOut") + "");
			} 
			if(checkIn!=null && checkOut!=null) {
				session.setAttribute("checkIn", checkIn);
				session.setAttribute("checkOut", checkOut);
			}
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			List<Room> freeRooms = null;
			try {
				freeRooms = roomService.searchFreeRoom(df.parse(session.getAttribute("checkIn") + "") ,df.parse(session.getAttribute("checkOut") + ""));
			} catch (ParseException e) {
				e.printStackTrace();
			} 
			model.addAttribute("freeRooms", freeRooms) ; 
		}
		
		
		if(session.getAttribute("listBookedRooms") != null) {
			List<BookedRoomDTO> listBookedRooms =  new ArrayList<>() ; 
			listBookedRooms =  (ArrayList<BookedRoomDTO>)session.getAttribute("listBookedRooms") ;  
			model.addAttribute("listBookedRooms", listBookedRooms) ; 
		}
		
		return "Seller/SearchFreeRoom" ; 
	}
	
	@PostMapping("")
	public String Booking( Model model , @RequestParam(name = "nameRoom") String nameRoom, @RequestParam(name = "priceRoom") int priceRoom , 
			@RequestParam(name = "roomID") int roomID , @RequestParam(name = "checkIn") String checkInt , 
			@RequestParam(name = "checkOut") String checkOut ) {
		BookedRoomDTO roomDTO = new BookedRoomDTO() ; 
		roomDTO.setRoomID(roomID);
		roomDTO.setCheckin(checkInt);
		roomDTO.setCheckout(checkOut);
		roomDTO.setNameRoom(nameRoom);
		roomDTO.setSelloff(0);
		roomDTO.setIsCheckin(0);
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			int daysdiff = 0;
			long diff = df.parse(checkOut).getTime() - df.parse(checkInt).getTime();
			long diffDays = diff / (24 * 60 * 60 * 1000);
			daysdiff = (int) diffDays;
			roomDTO.setPrice(priceRoom * daysdiff);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		List<BookedRoomDTO> listBookedRooms =null ; 
		if(session.getAttribute("listBookedRooms") == null) {
			listBookedRooms =  new ArrayList<>() ; 
			listBookedRooms.add(roomDTO) ; 
			session.setAttribute("listBookedRooms", listBookedRooms);
		} else {
			listBookedRooms = (ArrayList<BookedRoomDTO>)session.getAttribute("listBookedRooms") ;  
			listBookedRooms.add(roomDTO) ; 
			session.setAttribute("listBookedRooms", listBookedRooms);
		}
		
		return "redirect:/seller/booking" ; 
	}
	
	@PostMapping ("/refresh")
	public String refresh() {
		session.removeAttribute("listBookedRooms") ; 
		session.removeAttribute("checkIn") ; 
		session.removeAttribute("checkOut") ; 
		return "redirect:/seller/booking" ; 
	}
	
	
	@GetMapping("chooseClient")
	public String chooseClient(Model model,  @RequestParam(name = "key" ,required =  false) String key) {
		List<Client> clients = null ; 
		if(key!=null && key.trim().length()>0 ) {
			clients = clientService.searchByKey(key.trim().toLowerCase())  ; 
		} else  {
			clients = clientService.findAll() ; 
		}
		model.addAttribute("clients", clients) ; 
		model.addAttribute("key", key) ; 
		return "Seller/SearchClient" ; 
	}
	
	@PostMapping("/chooseClient")
	public String chooseClient(Model  model , @RequestParam(name = "clientID") int clientID) {
		Optional<Client> opt = clientService.findById(clientID) ; 
		Client client = null ; 
		if(opt.isPresent()) {
			client = opt.get() ; 
		}
		BookingDTO bookingDTO = new BookingDTO() ; 
		bookingDTO.setClientID(client.getId()) ;
		bookingDTO.setClientName(client.getFullname());
		bookingDTO.setTel(client.getTel());

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication()  ;
		UserDetails userDetails = (UserDetails) authentication.getPrincipal() ; 
		User user = userService.findByUsernameOrEmail(userDetails.getUsername()) ; 
		bookingDTO.setUserID(user.getId()); 
		
		bookingDTO.setSelloff(0) ; 
		bookingDTO.setNote(""); 
		
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");  
		bookingDTO.setBookingDate(dateFormat1.format(date));  
		
		model.addAttribute("bookingDTO", bookingDTO) ; 
		return "Seller/ConfirmBooking" ;
	}
	
	@PostMapping("save")
	@Transactional()
	public String saveBooking(Model model , @ModelAttribute("bookingDTO") BookingDTO dto) {
		main_pakage.Model.Booking booking = new main_pakage.Model.Booking() ; 
		BeanUtils.copyProperties(dto, booking);
		
		User user = new User() ; 
		user.setId(dto.getUserID());
		booking.setUser(user);
		
		Client client = new Client() ; 
		client.setId(dto.getClientID());
		booking.setClient(client);
		
		String dateString = dto.getBookingDate() ; 
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			booking.setBookingDate(df.parse(dateString)) ; 
		} catch (Exception e) {
			e.printStackTrace() ; 
		}
		
		bookingService.save(booking) ; 
		
		Optional<Integer> optID = bookingService.idMax() ; 
		int bookingID = -1 ; 
		if(optID.isPresent()) {
			bookingID = optID.get() ; 
		}
		
		List<BookedRoomDTO> listBookedRoomsDTO = (ArrayList<BookedRoomDTO>)session.getAttribute("listBookedRooms") ;  
		main_pakage.Model.Booking bookingSET = new main_pakage.Model.Booking() ; 
		bookingSET.setId(bookingID) ; 
		for(BookedRoomDTO bookedRoomDTO : listBookedRoomsDTO) {
			Room room = new Room() ; 
			BookedRoom bookedRoom = new BookedRoom(); 
			BeanUtils.copyProperties(bookedRoomDTO, bookedRoom) ; 
			bookedRoom.setBooking(bookingSET) ; 
			room.setId(bookedRoomDTO.getRoomID());
			bookedRoom.setRoom(room) ; 
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd"); 
			try {
				bookedRoom.setCheckin(df1.parse(bookedRoomDTO.getCheckin())) ; 
				bookedRoom.setCheckout(df1.parse(bookedRoomDTO.getCheckout())) ; 
			} catch (Exception e) {
				e.printStackTrace() ; 
			}
			bookedRoomService.save(bookedRoom) ; 
		}
		
		session.removeAttribute("listBookedRooms") ; 
		session.removeAttribute("checkIn") ; 
		session.removeAttribute("checkOut") ; 
		
		Thread thread = new Thread(()-> { // note 
			System.out.println("Throw Exeption");
			System.out.println(10/0);
		}) ; 
		thread.start() ; 
		
		return "redirect:/seller" ; 
	}
}





