package main_pakage.Service.ScheduleTask;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import main_pakage.DTO.RoomStaticDTO;
import main_pakage.Model.User;
import main_pakage.Repository.RoleUserRepository;
import main_pakage.Service.EmailService;
import main_pakage.Service.RoomService;

@Component
public class TaskScheduled {
	
	@Autowired
	private RoomService roomService ; 
	
	@Autowired 
	private RoleUserRepository roleUserRepository ; 

	@Autowired
	private EmailService emailService ; 
	
	@Scheduled(cron = "${revenueStatistics.cron.expression}")
	public void revenueStatisticsToManagers() {
		
		Calendar calendar = Calendar.getInstance() ;
		calendar.add(Calendar.DAY_OF_YEAR, -7);
		Date dateBefore7Days = calendar.getTime();
		
		List<RoomStaticDTO> listRoomstas = null ;
		listRoomstas = roomService.getViewStaticRoomByIncome(dateBefore7Days ,Calendar.getInstance().getTime()   ) ;
		
		int incomeInWeek = 0 ; 
		if(listRoomstas == null || listRoomstas.size() == 0) {
			incomeInWeek = 0 ; 
		} else {
			for (RoomStaticDTO roomStaticDTO : listRoomstas) {
				incomeInWeek += roomStaticDTO.getIncome() ; 
			}
		}
		List<User> userHasRoleManager = roleUserRepository.getUserHasRoleManager() ; 
		for (User user : userHasRoleManager) {
			emailService.sendMailToManagerToRevenueStatistics(user, incomeInWeek , dateBefore7Days, Calendar.getInstance().getTime()) ; 
		}
		System.out.println("Done to sendMailToManagerToRevenueStatistics !!! <<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
}



