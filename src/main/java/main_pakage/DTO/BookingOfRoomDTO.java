package main_pakage.DTO;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import main_pakage.Model.Bill;
import main_pakage.Model.BookedRoom;
import main_pakage.Model.Booking;
import main_pakage.Model.Client;
import main_pakage.Model.User;
//
//public class BookingOfRoomDTO extends Booking{
//	private int totalDays ; 
//	private int income ;
//	private String dateStart ; 
//	private String dateEnd ; 
//	
//	public BookingOfRoomDTO() {
//		super();
//	}
//
//	public BookingOfRoomDTO(int id, Date bookingDate, int selloff, String note, List<BookedRoom> bookedRooms,
//			Client client, User user, List<Bill> bills  , int totalDays , int income , String dateStart , String dateEnd) {
//		super(id, bookingDate, selloff, note, bookedRooms, client, user, bills);
//		this.totalDays = totalDays ; 
//		this.income = income ; 
//		this.dateStart = dateStart ; 
//		this.dateEnd = dateEnd  ; 
//	}
//
//	public int getIncome() {
//		return income;
//	}
//
//	public void setIncome(int income) {
//		this.income = income;
//	}
//
//	public int getTotalDays() {
//		return totalDays;
//	}
//
//	public void setTotalDays(int totalDays) {
//		this.totalDays = totalDays;
//	}
//
//	public String getDateStart() {
//		return dateStart;
//	}
//
//	public void setDateStart(String dateStart) {
//		this.dateStart = dateStart;
//	}
//
//	public String getDateEnd() {
//		return dateEnd;
//	}
//
//	public void setDateEnd(String dateEnd) {
//		this.dateEnd = dateEnd;
//	}
//}

@Data
@NoArgsConstructor
public class BookingOfRoomDTO {
	private int idBookedRoom ; 
	private String clientName ; 
	private Date dateStart ; 
	private Date dateEnd ; 
	private int priceOfRoom ; 
	private int totalDays ; 
	private int totalRevenue ;
	public BookingOfRoomDTO(int idBookedRoom, String clientName, Date dateStart, Date dateEnd, int priceOfRoom,
			int totalDays, int totalRevenue) {
		this.idBookedRoom = idBookedRoom;
		this.clientName = clientName;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.priceOfRoom = priceOfRoom;
		this.totalDays = totalDays;
		this.totalRevenue = totalRevenue;
	} 
	
	
	
}
