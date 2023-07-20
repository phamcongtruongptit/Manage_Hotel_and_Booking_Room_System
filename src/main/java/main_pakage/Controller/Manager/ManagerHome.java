package main_pakage.Controller.Manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("manager")
public class ManagerHome {

//	@Autowired
//	private BookedRoomServiceImp bookedRoomServiceImplement ; 
	
	@GetMapping()
	public String managerHomeView() {
		
		//Test complexquery
//		List<Object[]> listViewStatic = bookedRoomServiceImplement.getDemoComplexQuery() ; 
//		for (Object[] objects : listViewStatic) {
//			System.out.print(objects[0].toString() + "   ");
//			System.out.print(objects[1].toString() + "   ");
//			System.out.print(objects[2].toString() + "   ");
//			System.out.println();
//		}
		//oke banj owi
		return "Manager/ManageHomeView" ; 
	}
}
