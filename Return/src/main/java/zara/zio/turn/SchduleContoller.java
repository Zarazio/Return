package zara.zio.turn;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zara.zio.turn.domain.PlaceVO;
import zara.zio.turn.persistence.PlaceService;

@Controller
public class SchduleContoller {
	
	@Inject
	private PlaceService service;
	
	@RequestMapping (value="scheduleSet", method=RequestMethod.POST) // 스케쥴 페이지 이동 
	public String schduleSet(String scheduleDate, String local, Model model) {
		
		System.out.println(scheduleDate);
		
		if(scheduleDate.isEmpty())
			scheduleDate = "nullTest";
		
		if(local.isEmpty())
			local = "알수없는 지역";
		
		model.addAttribute("scheduleDate", scheduleDate);
		model.addAttribute("local", local);
		
		return "schedulePage/schdulePageA";
	}
	
	@RequestMapping (value="scheduleSet", method=RequestMethod.GET) // 스케쥴 페이지 이동 
	public String schduleSetG(String scheduleDate, String local, Model model) {
		
		System.out.println(scheduleDate);
		
		if(scheduleDate == null)
			scheduleDate = "nullTest";
		
		if(local == null)
			local = "알수없는 지역";
		
		model.addAttribute("scheduleDate", scheduleDate);
		model.addAttribute("local", local);
		
		return "schedulePage/schdulePageA";
	}
	
	@ResponseBody
	@RequestMapping (value="placeList", method=RequestMethod.GET) // 장소정보 가져오기
	public List<PlaceVO> placeList(HttpServletRequest request) throws Exception {
		
		String local = request.getParameter("localData");
		local = "%" + local + "%";
		List<PlaceVO> list = service.readLocal(local);
		
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i).getPlace_code() + " ");
			System.out.println(list.get(i).getPlace_address());
		}
		
		return list;
	}
	
}
