package zara.zio.turn.persistence;

import java.util.List;

import zara.zio.turn.domain.GroupVO;
import zara.zio.turn.domain.TravelListVO;

public interface GroupTravelService {
	public void create(GroupVO group) throws Exception;
	public int selectGroupCode(GroupVO group) throws Exception;
	
	// --------------- // 
	
	public void create(TravelListVO travel) throws Exception;
	public List<TravelListVO> planDayList(TravelListVO travel) throws Exception ;
	public int travel_place(TravelListVO travel) throws Exception ; // travel_list에 같은 placeCode가 있는지
}
