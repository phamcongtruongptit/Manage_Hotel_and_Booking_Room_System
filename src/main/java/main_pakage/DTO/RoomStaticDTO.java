package main_pakage.DTO;

import java.util.List;

import main_pakage.Model.BookedRoom;
import main_pakage.Model.Hotel;
import main_pakage.Model.Room;

public class RoomStaticDTO  extends Room{	
	private int filledDays  ; 
	private int income ;
	
	public RoomStaticDTO() {
		super();
	}
	
	public RoomStaticDTO(int id, String name, String type, int price, String description, Hotel hotel,
			List<BookedRoom> bookedRooms , int filledDays , int income) {
		super(id, name, type, price, description, hotel, bookedRooms);
		this.filledDays = filledDays ; 
		this.income  = income ; 
	}
	public int getFilledDays() {
		return filledDays;
	}
	public void setFilledDays(int filledDays) {
		this.filledDays = filledDays;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	
	public RoomStaticDTO(int id, String name, String type,  long filledDays , long income) {
		super(id, name, type) ; 
		this.filledDays = (int) filledDays ; 
		this.income  = (int) income ; 
	}

	
}
