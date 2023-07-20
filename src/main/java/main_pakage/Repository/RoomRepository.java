package main_pakage.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main_pakage.DTO.RoomStaticDTO;
import main_pakage.Model.Room;

@Repository
public interface RoomRepository  extends JpaRepository<Room, Integer>{
//	@Query("SELECT room FROM Room room WHERE room.name LIKE %:key%")
//	List<Room> searchByKey(@Param("key") String key) ; 
	
	Page<Room> findAllByNameContains(String name, Pageable pageable);
	
	@Query("SELECT room FROM Room room WHERE room.id NOT IN (SELECT room1.room.id FROM BookedRoom room1"
			+ "WHERE room1.checkout > :key1 AND room1.checkin < :key2 )")
	List<Room> searchFreeRoom(@Param("key1") Date key1 , @Param("key2")  Date key2) ;  // toois uwu
	
	
//	@Query("SELECT room.id, room.name, room.type, \r\n"
//			+ "(SELECT SUM(DATEDIFF( LEAST(b.checkout, :endDate ) , GREATEST(b.checkin, :startDate) )) FROM BookedRoom b WHERE b.room.id = room.id and b.checkout > :startDate and b.checkin < :endDate GROUP BY b.room.id) as days, \r\n"
//			+ "(SELECT SUM(DATEDIFF( LEAST(b.checkout, :endDate ) , GREATEST(b.checkin, :startDate))*room.price) FROM BookedRoom b WHERE b.room.id = room.id and b.checkout > :startDate and b.checkin < :endDate GROUP BY b.room.id) as income \r\n"
//			+ "FROM Room room  WHERE\r\n"
//			+ "(SELECT SUM(DATEDIFF( LEAST(b.checkout, :endDate ) , GREATEST (b.checkin, :startDate) )) FROM BookedRoom b WHERE b.room.id = room.id and b.checkout > :startDate and b.checkin < :endDate GROUP BY b.room.id) IS NOT NULL ORDER BY income DESC , days DESC")
//	List<Object[]> getViewStaticRoomByIncome(@Param("startDate") Date startDate , @Param("endDate")Date endDate) ; 
	
	@Query("SELECT new main_pakage.DTO.RoomStaticDTO(room.id, room.name, room.type, \r\n"
			+ "(SELECT SUM(DATEDIFF( LEAST(b.checkout, :endDate ) , GREATEST(b.checkin, :startDate) )) FROM BookedRoom b WHERE b.room.id = room.id and b.checkout > :startDate and b.checkin < :endDate GROUP BY b.room.id) as days, \r\n"
			+ "(SELECT SUM(DATEDIFF( LEAST(b.checkout, :endDate ) , GREATEST(b.checkin, :startDate))*room.price) FROM BookedRoom b WHERE b.room.id = room.id and b.checkout > :startDate and b.checkin < :endDate GROUP BY b.room.id) as income) \r\n"
			+ "FROM Room room  WHERE\r\n"
			+ "(SELECT SUM(DATEDIFF( LEAST(b.checkout, :endDate ) , GREATEST (b.checkin, :startDate) )) FROM BookedRoom b WHERE b.room.id = room.id and b.checkout > :startDate and b.checkin < :endDate GROUP BY b.room.id) IS NOT NULL ORDER BY income DESC , days DESC")
	List<RoomStaticDTO> getViewStaticRoomByIncome(@Param("startDate") Date startDate , @Param("endDate")Date endDate) ; 
}















