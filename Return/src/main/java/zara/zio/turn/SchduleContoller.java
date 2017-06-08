package zara.zio.turn;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import zara.zio.turn.domain.GroupVO;
import zara.zio.turn.domain.PlaceVO;
import zara.zio.turn.domain.TravelListVO;
import zara.zio.turn.persistence.GroupTravelService;
import zara.zio.turn.persistence.PlaceService;

@Controller
public class SchduleContoller {
	
	@Inject
	private PlaceService service;
	
	@Inject
	private GroupTravelService service1;
	
	
	@RequestMapping (value="scheduleSet", method=RequestMethod.POST) // 스케쥴 페이지 이동 
	public String schduleSet(GroupVO group,RedirectAttributes redirectAttributes ,String scheduleDate, String local, Model model) throws Exception {
		
		
		String[] date = scheduleDate.split(" - ");
		   
	    String date01 = date[0] ;
	    String date02 = date[1] ;
	      
	    Date start_Date = Date.valueOf(date01) ;
	    Date end_Date = Date.valueOf(date02) ;
	   
	    group.setStart_Date(start_Date);
	    group.setEnd_Date(end_Date);
	       
	    service1.create(group);
	      
	    // groupCode 뽑아내는 문
	    int groupCode = service1.selectGroupCode(group) ;
	    
		System.out.println(scheduleDate);
		
		if(scheduleDate.isEmpty())
			scheduleDate = "nullTest";
		
		if(local.isEmpty())
			local = "알수없는 지역";
		
		
		redirectAttributes.addAttribute("groupCode", groupCode);
		redirectAttributes.addAttribute("scheduleDate", scheduleDate);
		redirectAttributes.addAttribute("local", local);
		
		return "redirect:scheduleSet";
	}
	
	
	
	@RequestMapping (value="scheduleSet", method=RequestMethod.GET) // 스케쥴 페이지 이동 
	public String schduleSetG(String scheduleDate, String local, Model model, @ModelAttribute("groupCode") int groupCode) {
		
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
	
	@ResponseBody
	@RequestMapping (value="planPlaceCodCheck", method=RequestMethod.POST)
	public int planPlaceCodCheck(TravelListVO travel,String place, String plan, String group) throws Exception{
		
		String place_code = place;
	    int group_Code = Integer.parseInt(group);
	    Date travel_Date = Date.valueOf(plan);
	      
	    travel.setPlace_code("%" + place_code);
	    travel.setGroup_Code(group_Code);
	    travel.setTravel_Date(travel_Date);
		
		int count = service1.travel_place(travel) ;
		System.out.println("placecheck : " + place);
		//System.out.println("countDB  : " + count);
		return count ;
	}
	
	
	@ResponseBody
	@RequestMapping (value="planList", method=RequestMethod.POST)
	public void planList(String place, String plan, String group, TravelListVO travel) throws Exception{
	    String place_code = place;
	    int group_Code = Integer.parseInt(group);
	    Date travel_Date = Date.valueOf(plan);
	      
	    travel.setPlace_code(place_code);
	    travel.setGroup_Code(group_Code);
	    travel.setTravel_Date(travel_Date);
	    
	    
	
	      
	    service1.create(travel) ;
	      
	}
	   
	@ResponseBody
	@RequestMapping (value="planDayList" , method=RequestMethod.POST)
	public List<TravelListVO> planList01(String plan, String group , TravelListVO travel) throws Exception{
      
		int group_Code = Integer.parseInt(group) ;
		Date travel_Date = Date.valueOf(plan) ;
      
		travel.setGroup_Code(group_Code);
		travel.setTravel_Date(travel_Date);
		
		List<TravelListVO> place = service1.planDayList(travel);
	
		
		return place;
      
		
   }
	
	@ResponseBody
	@RequestMapping (value="planPlacePriority", method=RequestMethod.POST)
	public void planPlacePriority(TravelListVO travel , String[] place_code ) {
		
		for(int i=0 ; i<place_code.length ;i++){
			System.out.println("place_code : " +place_code[i]);
			travel.setPlace_code(place_code[i]);
			service1.planPriority(travel) ;
		}
		
	}

	
}
