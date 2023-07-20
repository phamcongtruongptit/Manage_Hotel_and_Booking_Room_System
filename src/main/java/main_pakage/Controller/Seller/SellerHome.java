package main_pakage.Controller.Seller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("seller")
public class SellerHome {
	@GetMapping
	public String home() {
		return "Seller/SellerHome" ; 
	}
}
