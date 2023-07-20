package main_pakage.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
//	public static void main(String[] args) throws ParseException {
//		String startDateString = "2023-02-05";
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
//		Date startDate;
//		try {
//		    startDate = df.parse(startDateString);
//		    String newDateString = df.format(startDate);
//		    System.out.println(startDate);
//		    System.out.println(newDateString);
//		} catch (ParseException e) {
//		    e.printStackTrace();
//		}
//		Date date = Calendar.getInstance().getTime();  
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
//		DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");  
//		String strDate = dateFormat1.format(date);  
//		System.out.println(date);
//		System.out.println("Converted String: " + strDate);  
//	}
	public static void main(String[] args) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -7);
		Date dateBefore7Days = calendar.getTime();
		System.out.println("NOW: "  + Calendar.getInstance().getTime());
		System.out.println("BEFORE 7Days: "  + dateBefore7Days); 
	}
}
