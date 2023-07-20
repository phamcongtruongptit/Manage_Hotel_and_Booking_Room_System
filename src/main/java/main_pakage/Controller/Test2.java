package main_pakage.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class Test2 {

	@GetMapping()
	@ResponseBody
	public String test() {
		return "test " ; 
	}
}
