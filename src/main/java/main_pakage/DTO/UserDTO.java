package main_pakage.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private int id ; 
	private String fullname ; 
	private String username ; 
	private String email  ; 
	private boolean isManager  = false ; 
	private boolean isSeller = false ; 
}
