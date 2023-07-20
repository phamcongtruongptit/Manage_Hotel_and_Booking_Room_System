package main_pakage.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookedRoomDTO {
	private int id ; 
	private String checkin ; 
	private String checkout ; 
	private String nameRoom ; 
	private int price ; 
	private int isCheckin ; 
	private int selloff ; 
	private int roomID ; 
}
