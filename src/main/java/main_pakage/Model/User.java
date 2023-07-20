package main_pakage.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ; 
	
	@Column(nullable = false , columnDefinition = "varchar(25)" , unique = true)
	private String username ; 
	
	@Column(nullable = false , columnDefinition = "varchar(200)")
	private String password ; 
	
	@Column(nullable = false , columnDefinition = "varchar(50)")
	private String fullname ; 
	
	@Column(nullable = false , columnDefinition = "varchar(50)" , unique = true)
	private String email ; 
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	private List<Booking> bookings = new ArrayList<>() ; 
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL )
	private List<Bill> bills = new ArrayList<>() ;       
	
	@OneToMany(mappedBy = "user")
	private List<RoleUser> roleOfUsers = new ArrayList<>()  ;  
	
}
















