package zara.zio.turn.dao;

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

}
