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
public class BookedRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ; 
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date checkin ; 
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date checkout ; 
	
	@Column(nullable = false )
	private int price ; 
	
	@Column(nullable = false )
	private int isCheckin ; 
	
	@Column(nullable = true)
	private int selloff ; 
	
	@ManyToOne
	@JoinColumn(name = "roomID")
	private Room room ;
	
	@ManyToOne
	@JoinColumn(name = "bookingID")
	private Booking booking ; 
	
	@OneToMany(mappedBy = "bookedRoom"  , cascade = CascadeType.ALL)
	private List<UsedService> usedServices = new ArrayList<>() ; 
	
}
