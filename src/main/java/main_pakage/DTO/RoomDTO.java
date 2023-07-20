package main_pakage.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
	private int id ; 
	private String name ; 
	private String type ; 
	private int price ; 
	private String description ; 
}
