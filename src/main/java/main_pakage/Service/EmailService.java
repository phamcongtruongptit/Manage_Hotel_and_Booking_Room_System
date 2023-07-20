package main_pakage.Service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import main_pakage.Model.User;

@Service

public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender ; 
	
	@Async
	public void sendMailWelcomNewUser(User user , String password) {
		try {
			SimpleMailMessage message = new SimpleMailMessage() ; 
			
			message.setFrom("bee.prime.k42@gmail.com") ; 
			message.setTo(user.getEmail());
			message.setSubject("Welcom " +  user.getFullname() + " To System");
			StringBuilder body= new StringBuilder("") ; 
			body.append("Welcom " +  user.getFullname() + " to Hotel Management System !\n") ; 
			body.append("Now you can login to system with username: " + user.getUsername() + " and password: " + 
			password + ". \n") ; 
			body.append("In addition, you can also login to the system with your email with the same password as above !") ; 
			message.setText(body.toString());
			mailSender.send(message);
			System.out.println(">>>>>>>> Send Mail Welcom New User Sucessfull ! <<<<<<<<<");
		} catch (Exception e) {
			System.out.println(">>>>>>>> Failed To Send Mail Welcom New User! <<<<<<<<<");
		}
	}
	
//	@Async
	public void sendMailToManagerToRevenueStatistics(User user , int income , Date start , Date end) {
		try {
			SimpleMailMessage message = new SimpleMailMessage() ; 
			
			message.setFrom("bee.prime.k42@gmail.com") ; 
			message.setTo(user.getEmail());
			message.setSubject("Revenue Statistics In This Week");
			
			
			StringBuilder body= new StringBuilder("") ; 
			body.append("Hello " +  user.getFullname() + " !\n\n") ; 
		 
			body.append("Our hotel made " +income +  "$ this week (" + start + " - " + end + ").\n\n") ; 
			body.append("Have a good weekend !!!") ; 
			
			message.setText(body.toString());
			mailSender.send(message);
			System.out.println(">>>>>>>> Send Mail To Manager To Revenue Statistics Sucessfull ! <<<<<<<<<");
		} catch (Exception e) {
			System.out.println(">>>>>>>> Failed To Manager To Revenue Statistics Sucessfull <<<<<<<<<");
		}
	}


}
