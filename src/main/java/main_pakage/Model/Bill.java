package main_pakage.Model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Bills")
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ; 
	
	@Column(nullable =  false )
	@Temporal(TemporalType.DATE)
	private Date paymentDate ;   
	
	@Column(nullable = false )
	private int amount ; 
	
	@Column(nullable = false , columnDefinition = "varchar(50)")
	private String paymentType ; 
	
	@Column(nullable = true , columnDefinition = "varchar(250)")
	private String note ; 
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user ; 
	
	@ManyToOne
	@JoinColumn(name = "bookingID")
	private Booking booking ; 
}



































