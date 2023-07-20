package main_pakage.Controller.Manager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import main_pakage.DTO.BookingOfRoomDTO;
import main_pakage.DTO.RoomStaticDTO;
import main_pakage.Service.BookingService;
import main_pakage.Service.RoomService;

@Controller
@RequestMapping("manager/viewStatic")
public class ViewStaticController {
	
	@Autowired
	private RoomService roomService ; 
	@Autowired 
	private BookingService bookingService ; 
	
	@GetMapping("/chooseViewStaticType")
	public String getChooseType(Model model) {
		return "Manager/ChooseViewStaticType" ; 
	}
	
	@PostMapping("/chooseViewStaticType")
	public String postChooseType(Model model , @RequestParam(name = "object") String object, @RequestParam(name = "type") String type) {
		
		if(object.equals("room") && type.equals("revenue")) {
			return "redirect:/manager/viewStatic/room" ; 
		}
		return "Manager/ChooseViewStaticType" ;  
	}
	
	@GetMapping("/room")
	public String getViewStaticRoom(Model model , @RequestParam(name = "startDate" , required = false) String startDate , 
			@RequestParam(name = "endDate" , required = false) String endDate){
		
		if(startDate!=null && endDate != null) {
			model.addAttribute("startDate", startDate)  ; 
			model.addAttribute("endDate", endDate)  ; 
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd") ; 
			List<RoomStaticDTO> listRoomstas = null ;
			try {
				listRoomstas = roomService.getViewStaticRoomByIncome(df.parse(startDate) , df.parse(endDate)) ;
			} catch (ParseException e) {
				e.printStackTrace();
			} 
			
			model.addAttribute("listRoomstas", listRoomstas) ; 
			
			return "Manager/ViewStatic" ; 
		}
		return "Manager/ViewStatic" ; 
	}
	
	@PostMapping("/room")
	public String getViewDetailOfRoom(Model model , @RequestParam("roomId") int roomId , 
			@RequestParam("startDate") String startDateString , 
			@RequestParam("endDate") String endDateString) {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd") ; 
		Date startDate = new Date() ; 
		Date endDate = new Date();
		try {
			startDate = df.parse(startDateString) ; 
			endDate = df.parse(endDateString) ; 
		} catch (Exception e) {
			e.printStackTrace() ; 
		} 
		List<BookingOfRoomDTO> listBookingOfRooms = bookingService.getBookingOfRoom(roomId, startDate, endDate) ; 
		model.addAttribute("listBookingOfRooms", listBookingOfRooms) ; 
		return "Manager/ViewDetail" ; 
	}
	
	
	
}
