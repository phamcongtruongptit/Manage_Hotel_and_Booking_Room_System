package main_pakage.Model;

import java.util.ArrayList;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ; 
	
	@Column(nullable =  false , columnDefinition = "varchar(50)")
	private String name ; 
	
	@Column(nullable =  false , columnDefinition = "varchar(50)")
	private String type ; 
	
	@Column(nullable =  false )
	private int price ; 
	
	@Column(nullable = true , columnDefinition = "varchar(250)")
	private String description ; 
	
	@ManyToOne
	@JoinColumn(name = "hotelId")
	private Hotel hotel ; 
	
	@OneToMany(mappedBy = "room" , cascade =  CascadeType.ALL )
	List<BookedRoom> bookedRooms = new ArrayList<>() ;

	
	public Room(int id , String name , String type) {
		this.id = id ; 
		this.name = name  ; 
		this.type =  type ; 
	}
	
}
