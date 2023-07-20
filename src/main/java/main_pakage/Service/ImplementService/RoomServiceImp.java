package main_pakage.Service.ImplementService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import main_pakage.DTO.RoomStaticDTO;
import main_pakage.Model.Room;
import main_pakage.Repository.RoomRepository;
import main_pakage.Service.RoomService;

@Service
public class RoomServiceImp  implements RoomService{
	@Autowired
	private RoomRepository roomRepository ;

	@Override
	public <S extends Room> S save(S entity) {
		return roomRepository.save(entity);
	}

	@Override
	public List<Room> searchFreeRoom(Date key1, Date key2) {
		return roomRepository.searchFreeRoom(key1, key2);
	}
	
	

	@Override
	public void delete(Room entity) {
		roomRepository.delete(entity);
	}

	@Override
	public List<Room> searchByKey(String key) {
		return null;
	}

	@Override
	public List<Room> findAll() {
		return roomRepository.findAll();
	}

	@Override
	public Optional<Room> findById(Integer id) {
		return roomRepository.findById(id);
	}

//	@Override
//	public List<RoomStaticDTO> getViewStaticRoomByIncome(Date startDate, Date endDate) {
//		List<RoomStaticDTO>list = new ArrayList<>() ;
//		
//		List<Object[]> listObjects = roomRepository.getViewStaticRoomByIncome(startDate, endDate ) ; 
//		for (Object[] objects : listObjects) {
//			RoomStaticDTO roomsta  = new RoomStaticDTO() ; 
//			roomsta.setId(Integer.parseInt(objects[0].toString()));
//			roomsta.setName(objects[1].toString());
//			roomsta.setType(objects[2].toString());
//			roomsta.setFilledDays(Integer.parseInt(objects[3].toString()));
//			roomsta.setIncome(Integer.parseInt(objects[4].toString()));
//			list.add(roomsta) ; 
//		}
//		
//		return list ; 
//	}
	
	@Override
	public List<RoomStaticDTO> getViewStaticRoomByIncome(Date startDate, Date endDate) {
		List<RoomStaticDTO>list = roomRepository.getViewStaticRoomByIncome(startDate, endDate) ;
		return list ; 
	}
	
	
	@Override
	public Page<Room> getAllRoomByPagingAndSorting(int pageNum , String sortField, String sortDir){
		int pageSize = 5 ; 
		Pageable pageable = PageRequest.of(pageNum  - 1 , pageSize , 
				sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending() 
				) ; 
		return roomRepository.findAll(pageable) ; 
	}
	
	@Override
	public Page<Room> getAllRoomByPagingAndSortingandSearching(int pageNum , String sortField, String sortDir , String search){
		int pageSize = 5 ; 
		Pageable pageable = PageRequest.of(pageNum  - 1 , pageSize , 
				sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending() 
				) ; 
		return roomRepository.findAllByNameContains(search  , pageable) ; 
	}
	
}






















