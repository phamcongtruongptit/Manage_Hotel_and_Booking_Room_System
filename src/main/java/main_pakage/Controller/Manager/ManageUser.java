package main_pakage.Controller.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import main_pakage.DTO.UserDTO;
import main_pakage.Model.Role;
import main_pakage.Model.RoleUser;
import main_pakage.Model.User;
import main_pakage.Repository.RoleUserRepository;
import main_pakage.Service.EmailService;
import main_pakage.Service.RoleUserService;
import main_pakage.Service.UserService;

@Controller
@RequestMapping("manager/users")
public class ManageUser {
	
	@Autowired
	private UserService userService ; 
	
	@Autowired
	private RoleUserService roleUserService  ;
	
	@Autowired
	private RoleUserRepository roleUserRepository  ;
	
	@Autowired
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder() ; 
	}
	
	@Autowired
	private EmailService emailService ; 
	
	@GetMapping("")
	public String getListUser(Model model) {
		List<User> listUsers = userService.findAll() ; 
		List<UserDTO> listUserDTO = new  ArrayList<>() ; 
		listUsers.forEach(item -> {
			UserDTO userDTO = new UserDTO(); 
			BeanUtils.copyProperties(item, userDTO); 
			List<Role> roles = roleUserService.getRoleOfUser(item) ; 
			roles.forEach( item1-> {
				if (item1.getRoleName().equals("MANAGER")) userDTO.setManager(true) ; 
				if (item1.getRoleName().equals("SELLER")) userDTO.setSeller(true) ;  
			}) ; 
			listUserDTO.add(userDTO) ; 
		}) ; 
		model.addAttribute("listUserDTO", listUserDTO) ; 
		return "Manager/UserList" ; 
	}
	
	@GetMapping("/add")
	public String adduser(Model model) {
		UserDTO userDTO = new UserDTO() ; 
		userDTO.setId(-1) ; 
		model.addAttribute("userDTO", userDTO) ; 
		return "Manager/AddUser" ; 
	}
	
	@PostMapping("/add")
	@Transactional
	public String saveuser(Model model , @ModelAttribute("userDTO") UserDTO userDTO) {
		User user = new User() ; 
		BeanUtils.copyProperties(userDTO, user) ; 
		user.setUsername(fullNameToUsername(userDTO.getFullname())) ; 
		String password = genarateRamdomPassword() ; 
		user.setPassword(passwordEncoder().encode(password)) ; 
		try {
			userService.save(user) ; 
		} catch (Exception e) {
			return "redirect:/manager/users/add" ; 
		}
		
		User userToSaveRoleUser = userService.findByUsernameOrEmail(user.getUsername()) ; 
		if(userDTO.isManager()) {
			RoleUser roleUser = new RoleUser(-1, new Role(1,"MANAGER", null), userToSaveRoleUser) ; 
			roleUserRepository.save(roleUser) ; 
		}
		if(userDTO.isSeller()) {
			RoleUser roleUser = new RoleUser(-1, new Role(2, "SELLER", null), userToSaveRoleUser) ; 
			roleUserRepository.save(roleUser) ; 
		}
		
		emailService.sendMailWelcomNewUser(userToSaveRoleUser, password)  ; 
		return "redirect:/manager/users" ; 
	}
	
	
	
	
	
	private String fullNameToUsername(String fullName) {
		StringBuilder username = new StringBuilder("") ;  
		String []list = fullName.trim().toLowerCase().split(" ") ; 
		int size= list.length; 
		username.append(list[size-1]).append(".") ; 
		for(int i = 0 ; i< size-1; i++) {
			username.append(list[i]) ; 
		}
		return username.toString() ; 
	}
	
	private String genarateRamdomPassword() {
		 String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
	        StringBuilder password = new StringBuilder();
	        Random random = new Random();
	        for (int i = 0; i < 8; i++) {
	            int index = random.nextInt(chars.length());
	            password.append(chars.charAt(index));
	        }
	        return password.toString() ; 
	}
	
}


























