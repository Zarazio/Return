package zara.zio.turn.dao;

import zara.zio.turn.domain.GroupVO;
import zara.zio.turn.domain.TravelListVO;

public interface GroupTravelDAO {
	
	public void create(GroupVO group) throws Exception;
	public int selectGroupCode(GroupVO group) throws Exception;
	
	// --------------- // 
	
	public void create(TravelListVO travel) throws Exception;
	
}
