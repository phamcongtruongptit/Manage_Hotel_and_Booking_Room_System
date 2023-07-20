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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Hotel {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private int id  ; 
	
	@Column(nullable = false , columnDefinition = "varchar(50)")
	private String name ; 
	
	@Column(nullable = false , columnDefinition = "varchar(250)")
	private String address ; 
	
	@Column(nullable = false)
	private int starLevel ; 
	
	@Column(nullable = true, columnDefinition = "varchar(250)")
	private String description ; 
	
	@OneToMany(mappedBy = "hotel"  , cascade =  CascadeType.ALL)
	private List<Room> rooms = new ArrayList<>() ; 
}
