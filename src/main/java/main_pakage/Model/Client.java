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
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ; 
	
	@Column(nullable = false , columnDefinition = "varchar(50)")
	private String fullname ; 
	
	@Column(nullable = false , columnDefinition = "varchar(20)")
	private String idCard ; 
	
	@Column(nullable = false , columnDefinition = "varchar(250)")
	private String address ; 
	
	@Column(nullable = false , columnDefinition = "varchar(20)")
	private String tel ; 
	
	@Column(nullable = true , columnDefinition = "varchar(50)")
	private String email ; 
	
	@Column(nullable = true , columnDefinition = "varchar(250)")
	private String note ; 
	
	@OneToMany(mappedBy = "client" , cascade =  CascadeType.ALL )
	private List<Booking> bookings = new ArrayList<>() ; 
}















