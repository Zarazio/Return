package zara.zio.turn.dao;

import java.util.List;

import zara.zio.turn.domain.GroupVO;
import zara.zio.turn.domain.TravelListVO;

public interface GroupTravelDAO {
	
	public void create(GroupVO group) throws Exception;
	public int selectGroupCode(GroupVO group) throws Exception;
	
	// --------------- // 
	
	public void create(TravelListVO travel) throws Exception;
	public List<TravelListVO> planDayList(TravelListVO travel) throws Exception;
	public int travel_place(TravelListVO travel) throws Exception; // travel_list에 같은 placeCode가 있는지
	public void planPriority(TravelListVO travel) throws Exception;
	public void planDelete(TravelListVO travel) throws Exception;
	public List<TravelListVO> planRealTimePriority(TravelListVO travel) throws Exception;
}
