package main_pakage.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
	private int id ; 
	private String bookingDate ; 
	private int selloff ; 
	private String note ; 
	private int clientID ; 
	private int userID ; 
	private String clientName ; 
	private String Tel ; 
}
