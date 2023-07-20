package main_pakage.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY )
	private int id ; 
	
	private String roleName ; 
	
	@OneToMany(mappedBy = "role")
	List<RoleUser> roleAboutUsers = new ArrayList<>() ; 
	
	
}
