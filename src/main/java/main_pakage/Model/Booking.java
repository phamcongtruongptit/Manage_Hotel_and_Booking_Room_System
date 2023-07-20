package main_pakage.Model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id ; 
	
	@Column(nullable = false )
	@Temporal(TemporalType.DATE)
	private Date bookingDate ; 
	
	@Column(nullable  = true)
	private int selloff ; 
	
	@Column(nullable = true , columnDefinition = "varchar(250)")
	private String note ; 
	
	@OneToMany(mappedBy = "booking")
	private List<BookedRoom> bookedRooms = new ArrayList<>() ; 
	
	@ManyToOne
	@JoinColumn(name = "clientID")
	private Client client ; 
	
	@ManyToOne
	@JoinColumn(name = "userID")
	private User user  ;
	
	@OneToMany(mappedBy =  "booking" , cascade = CascadeType.ALL )
	private List<Bill> bills = new ArrayList<>() ; 
}





















