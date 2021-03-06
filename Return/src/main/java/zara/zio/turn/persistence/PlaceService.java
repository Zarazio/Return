package zara.zio.turn.persistence;

import java.util.List;

import zara.zio.turn.domain.Pagination;
import zara.zio.turn.domain.PlaceVO;

public interface PlaceService {
	public void place_insert(PlaceVO vo) throws Exception;
	public void img_insert(List<PlaceVO> list) throws Exception;
	public int place_max() throws Exception;
	
	public List<PlaceVO> listPage(Pagination pagination) throws Exception;
	public int getTotalCount() throws Exception;
	public PlaceVO read(int no) throws Exception;
	public List<PlaceVO> readimg(int no) throws Exception;
	public void placeAll_delete(int no) throws Exception;
	public void pimg_delete(int no) throws Exception;
	public void place_update(PlaceVO vo, int no) throws Exception;
	
	public List<PlaceVO> readLocal(String local) throws Exception;
}
