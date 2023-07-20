package main_pakage.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class UsedService {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ; 
	
	@Column(nullable = false )
	private int quantity ; 
	
	@Column(nullable = false)
	private int price ; 
	
	@Column(nullable = false)
	private int selloff ; 
	
	@ManyToOne
	@JoinColumn(name = "bookedRoomID")
	private BookedRoom bookedRoom ; 
	
	@ManyToOne
	@JoinColumn(name = "serviceID")
	private Service service ; 
}
























