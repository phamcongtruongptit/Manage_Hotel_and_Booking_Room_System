package main_pakage.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import main_pakage.DTO.RoomStaticDTO;
import main_pakage.Model.Room;

public interface RoomService {

	Optional<Room> findById(Integer id);
	
	List<Room> findAll();
	
	<S extends Room> S save(S entity);

	List<Room> searchByKey(String key);

	List<Room> searchFreeRoom(Date key1, Date key2);

	List<RoomStaticDTO> getViewStaticRoomByIncome(Date startDate, Date endDate);

	Page<Room> getAllRoomByPagingAndSorting(int pageNum, String sortField, String sortDir);

	Page<Room> getAllRoomByPagingAndSortingandSearching(int pageNum, String sortField, String sortDir , String search);

	void delete(Room entity);
}
