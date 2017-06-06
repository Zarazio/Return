package zara.zio.turn.persistence;

import zara.zio.turn.domain.GroupVO;
import zara.zio.turn.domain.TravelListVO;

public interface GroupTravelService {
	public void create(GroupVO group) throws Exception;
	public int selectGroupCode(GroupVO group) throws Exception;
	
	// --------------- // 
	
	public void create(TravelListVO travel) throws Exception;
}
