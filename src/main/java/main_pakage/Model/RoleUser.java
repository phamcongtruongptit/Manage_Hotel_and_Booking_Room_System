package main_pakage.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleUser {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id ; 
	
	@ManyToOne
	@JoinColumn(name = "roleID")
	private Role role ; 
	
	@ManyToOne
	@JoinColumn(name = "userID")
	private User user ; 
}
