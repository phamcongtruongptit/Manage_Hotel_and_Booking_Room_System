package main_pakage.Controller.Manager;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import main_pakage.DTO.RoomDTO;
import main_pakage.Model.Hotel;
import main_pakage.Model.Room;
import main_pakage.Service.RoomService;

@Controller
@RequestMapping("manager/rooms")
public class ManageRoomController {
	@Autowired
	private RoomService roomService ; 
	@Autowired
	private HttpSession session ; 

	@GetMapping("/page/{pageNum}")
	public String getRoom(Model model ,@PathVariable(name = "pageNum") int pageNum , @RequestParam("sortField") String sortField , 
			@RequestParam("sortDir") String sortDir , @RequestParam(name = "search" , required =  false) String search ) {
		
		System.out.println(search + ": !!!");
		
		if(search == null || search.length() == 0 ) {
			Page<Room> page = roomService.getAllRoomByPagingAndSorting(pageNum, sortField, sortDir) ; 
			List<Room> listRooms = page.getContent() ; 
			
			model.addAttribute("currentPage", pageNum)  ; 
			model.addAttribute("totalPages", page.getTotalPages()) ; 
			
			model.addAttribute("sortField",sortField) ; 
			model.addAttribute("sortDir", sortDir) ; 
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ?"decs" : "asc") ; 
			model.addAttribute("search",null) ; 
			
			model.addAttribute("rooms", listRooms) ; 
		} else {
			Page<Room> page = roomService.getAllRoomByPagingAndSortingandSearching(pageNum, sortField, sortDir, search) ; 
			List<Room> listRooms = page.getContent() ; 
			
			model.addAttribute("currentPage", pageNum)  ; 
			model.addAttribute("totalPages", page.getTotalPages()) ; 
			
			model.addAttribute("sortField",sortField) ; 
			model.addAttribute("sortDir", sortDir) ; 
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ?"decs" : "asc") ; 
			
			model.addAttribute("search",search) ; 
			
			model.addAttribute("rooms", listRooms) ; 
		}
		
		return "Manager/ManageRoomView" ; 
		
	}
	
	
	@GetMapping("add")
	public String addRoom(Model model ) {
		RoomDTO roomdto = new RoomDTO() ; 
		roomdto.setId(-1);
		model.addAttribute("roomdto", roomdto);
		return "Manager/EditRoom" ; 
	}                                                                                                                 
	
	@GetMapping("edit/{roomID}")
	public String editRoom(Model model , @PathVariable("roomID") Integer id) {
		Optional<Room> opt = roomService.findById(id) ; 
		if(opt.isPresent()) {
			Room room = opt.get() ; 
			RoomDTO roomdto = new RoomDTO() ; 
			BeanUtils.copyProperties(room, roomdto) ; 
			model.addAttribute("roomdto", roomdto) ; 
			session.setAttribute("editForm", "editForm") ; 
			return "Manager/EditRoom" ;  
		}
		return "redirect:/manager/rooms" ;
	}
	
	@PostMapping("/save")
	public String saveRoom(Model model , @ModelAttribute("roomdto") RoomDTO dto  ) {
		Room room = new Room() ; 
		BeanUtils.copyProperties(dto, room) ;
		Hotel hotel = new Hotel() ; 
		hotel.setId(1) ; 
		room.setHotel(hotel) ; 
		roomService.save(room) ; 
		session.removeAttribute("editForm") ; 
		return "redirect:/manager/rooms/page/1?sortField=id&sortDir=desc" ;
	}
	
	@GetMapping("/delete/{roomID}")
	public String deleteRoom(Model model , @PathVariable("roomID") Integer id) {
		Optional<Room> opt = roomService.findById(id) ; 
		if(opt.isPresent()) {
			Room room = opt.get() ; 
			roomService.delete(room) ; 
			return "redirect:/manager/rooms/page/1?sortField=id&sortDir=asc" ;
		}
		return "redirect:/home" ;
	}
}













