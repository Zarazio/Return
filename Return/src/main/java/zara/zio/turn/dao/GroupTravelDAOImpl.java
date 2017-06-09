package zara.zio.turn.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import zara.zio.turn.domain.GroupVO;
import zara.zio.turn.domain.TravelListVO;

@Repository
public class GroupTravelDAOImpl implements GroupTravelDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private String NAMESPACE = "zara.zio.groupTravelMapper" ;
	
	@Override
	public void create(GroupVO group) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE + ".group_insert", group) ;
	}
	
	@Override
	public int selectGroupCode(GroupVO group) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE+ ".groupCode", group);
	}
	
	// --------------- // 
	
	@Override
	public void create(TravelListVO travel) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE + ".planList_insert", travel) ; 
	}

	@Override
	public List<TravelListVO> planDayList(TravelListVO travel) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".planDayList" , travel) ;
	}

	@Override
	public int travel_place(TravelListVO travel) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".travel_place", travel);
	}

	@Override
	public void planPriority(TravelListVO travel) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.selectOne(NAMESPACE + ".planPriority", travel);
	}

	@Override
	public void planDelete(TravelListVO travel) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(NAMESPACE + ".planDelete", travel);
	}

}
