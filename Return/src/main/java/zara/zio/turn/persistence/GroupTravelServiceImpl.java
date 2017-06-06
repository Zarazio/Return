package zara.zio.turn.persistence;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import zara.zio.turn.dao.GroupTravelDAO;
import zara.zio.turn.domain.GroupVO;
import zara.zio.turn.domain.TravelListVO;

@Service
public class GroupTravelServiceImpl implements GroupTravelService {
	
	@Inject 
	private GroupTravelDAO dao ;
	
	@Override
	public void create(GroupVO group) throws Exception {
		// TODO Auto-generated method stub
		dao.create(group);
	}

	@Override
	public int selectGroupCode(GroupVO group) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectGroupCode(group);
	}
	
	// --------------- // 
	
	@Override
	public void create(TravelListVO travel) throws Exception {
		// TODO Auto-generated method stub
		dao.create(travel);
	}

}
