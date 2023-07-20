package main_pakage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import main_pakage.Model.Client;
import main_pakage.Model.Hotel;
import main_pakage.Model.Role;
import main_pakage.Model.RoleUser;
import main_pakage.Model.Room;
import main_pakage.Model.User;
import main_pakage.Repository.ClientRepository;
import main_pakage.Repository.HotelRepository;
import main_pakage.Repository.RoleRepository;
import main_pakage.Repository.RoleUserRepository;
import main_pakage.Repository.RoomRepository;
import main_pakage.Repository.UserRepository;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class HotelManagementApplication   implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(HotelManagementApplication.class, args);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder() ; 
	}

	
	@Autowired HotelRepository hotelRepository ; 
	@Autowired ClientRepository clientRepository ; 
	@Autowired UserRepository userRepository ; 
	@Autowired RoleRepository roleRepository ; 
	@Autowired RoleUserRepository roleUserRepository ;
	@Autowired RoomRepository roomRepository ; 
	// Add Data In First Run
	@Override
	public void run(String... args) throws Exception {
		System.out.println(">>>>>>>>>>>>>>>>>>>>> RUN SUCESSFULL MINIPROJECT !!!!!!!!! <<<<<<<<<<<<<");
	
//		Hotel hotel = new Hotel(1, "NccPlus Hotel", "23 Tran Phu, Ha Dong, HN", 4 , "View Beautiful", null) ; 
//		hotelRepository.save(hotel) ; 
//		
//		Client client1 = new Client(1, "Pham Cong Truong", "03749234682", "Ha Dong, HN", "0383472842", "phamcongtruog.ptit@gmail.com", "",null) ;  
//		Client client2 = new Client(2, "Do Van Tung", "6387848957334", "NInh Binh", "0637493734", "dovantung@gmail.com", "",null) ;  
//		Client client3 = new Client(3, "Dam Van Tung", "8236438482473", "Hung Yen", "0927462849", "trungdv@gmail.com", "",null) ;  
//		Client client4 = new Client(4, "Dao Cuong Thinh", "259865848854", "Hai Duong", "0586985452", "thinhdv@gmail.com", "",null) ;  
//		Client client5 = new Client(5, "Hoang NGoc Thang", "237830374", "Nam Dinh", "0238435902", "thangdn@gmail.com", "",null) ;  
//		clientRepository.save(client1) ; 
//		clientRepository.save(client2) ; 
//		clientRepository.save(client3) ; 
//		clientRepository.save(client4) ; 
//		clientRepository.save(client5) ; 
//		
//		User user1 = new User(1, "nam.nguyenvan", passwordEncoder().encode("nam.nguyenvan") , "Nguyen Van Nam", "namnv@gmail.com", null, null, null) ; //seller
//		User user2 = new User(2, "hai.nguyenminh", passwordEncoder().encode("hai.nguyenminh"), "Nguyen Minh Hai", "hainm@gmail.com", null, null, null) ; //manager
//		User user3 = new User(3, "diep.letuan", passwordEncoder().encode("diep.letuan") , "Le Tuan Diep", "dieplt@gmail.com", null, null, null) ; //manager
//		User user4 = new User(4, "thanh.nguyentuan", passwordEncoder().encode("thanh.nguyentuan"), "Nguyen Tuan Thanh", "thanhnt@gmail.com", null, null, null) ;  //seller
//		userRepository.save(user1) ; 
//		userRepository.save(user2) ; 
//		userRepository.save(user3) ; 
//		userRepository.save(user4) ; 
//		
//		Role roleManger = new Role(1,"MANAGER", null) ; 
//		Role roleSeller = new Role(2, "SELLER", null) ; 
//		roleRepository.save(roleManger) ; 
//		roleRepository.save(roleSeller) ; 
//		
//		RoleUser roleUser1 = new RoleUser(1, roleSeller, user1) ; 
//		RoleUser roleUser2 = new RoleUser(2, roleManger, user2) ; 
//		RoleUser roleUser3 = new RoleUser(3, roleManger, user3) ; 
//		RoleUser roleUser4 = new RoleUser(4, roleSeller, user4) ; 
//		roleUserRepository.save(roleUser1) ; 
//		roleUserRepository.save(roleUser2) ; 
//		roleUserRepository.save(roleUser3) ; 
//		roleUserRepository.save(roleUser4) ; 
//		
//		Room room1 = new Room(1, "101", "Standard" , 20, "" , hotel, null) ; 
//		Room room2 = new Room(2, "102", "Vip" , 30, "" , hotel, null) ; 
//		Room room3 = new Room(3, "103", "Standard" , 14, "" , hotel, null) ; 
//		Room room4 = new Room(4, "104", "Premium" , 26, "" , hotel, null) ; 
//		Room room5 = new Room(5, "201", "Vip" ,14, "" , hotel, null) ; 
//		Room room6 = new Room(6, "202", "Premium" , 32, "" , hotel, null) ; 
//		Room room7 = new Room(7, "203", "Standard" , 8, "" , hotel, null) ; 
//		Room room8 = new Room(8, "204", "Vip" , 18, "" , hotel, null) ; 
//		roomRepository.save(room1) ; 
//		roomRepository.save(room2) ; 
//		roomRepository.save(room3) ; 
//		roomRepository.save(room4) ; 
//		roomRepository.save(room5) ; 
//		roomRepository.save(room6) ; 
//		roomRepository.save(room7) ; 
//		roomRepository.save(room8) ; 
		
	}

}
