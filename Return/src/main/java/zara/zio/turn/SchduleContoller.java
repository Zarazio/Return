 package zara.zio.turn;

import java.sql.Date;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	public String schduleSet(GroupVO group, String scheduleDate, String local, RedirectAttributes rttr) throws Exception {

		String[] date = scheduleDate.split(" - ");
		   
	    String date01 = date[0];
	    String date02 = date[1];
	      
	    Date start_Date = Date.valueOf(date01) ;
	    Date end_Date = Date.valueOf(date02) ;
	   
	    group.setStart_Date(start_Date);
	    group.setEnd_Date(end_Date);
	       
	    service1.create(group);
	      
	    // groupCode 뽑아내는 문
	    int groupCode = service1.selectGroupCode(group);
		
		rttr.addAttribute("scheduleDate", scheduleDate);
		rttr.addAttribute("local", local);
		rttr.addAttribute("groupCode", groupCode);
		
		return "redirect:scheduleSet";
	}
	
	
	
	@RequestMapping (value="scheduleSet", method=RequestMethod.GET) // 스케쥴 페이지 이동 
	public String schduleSetG(String scheduleDate, String local, int groupCode, Model model) {

		
		model.addAttribute("scheduleDate", scheduleDate);
		model.addAttribute("local", local);
		model.addAttribute("groupCode", groupCode);
		
		return "schedulePage/schedulePageA";
		
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
	public int planPlaceCodCheck(TravelListVO travel, String plan, String group) throws Exception{
		
	
	    int group_Code = Integer.parseInt(group);
	    Date travel_Date = Date.valueOf(plan);
	      
	 
	    travel.setGroup_Code(group_Code);
	    travel.setTravel_Date(travel_Date);
		
		int count = service1.travel_place(travel) ;
		//System.out.println("placecheck : " + place);
		System.out.println("countDB  : " + count);
		return count ;
	}
	
	
	@ResponseBody
	@RequestMapping (value="planList", method=RequestMethod.POST)
	public void planList(String place, String plan, String group, TravelListVO travel, int priority) throws Exception{
		int place_code = Integer.parseInt(place);
	    int group_Code = Integer.parseInt(group);
	    Date travel_Date = Date.valueOf(plan);
	    
		System.out.println("priority : " + travel.getTravel_Priority());
		
		travel.setTravel_Priority(priority);
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
	//public String planPlacePriority(@RequestBody List<TravelListVO> array) throws Exception {
	public String planPlacePriority(HttpServletRequest request, String plan, String group, TravelListVO travel, int count) throws Exception {

		int count_check = 0 ;
		String[] array = (String[])request.getParameterValues("array") ;
		String[] array01 = (String[])request.getParameterValues("array01") ;
	
		
		int group_Code = Integer.parseInt(group);
		Date travel_Date = Date.valueOf(plan);
       
		travel.setTravel_Priority(count);
		travel.setGroup_Code(group_Code);
		travel.setTravel_Date(travel_Date);
		
		
		for(int i=0 ; i<array.length ; i++){
			System.out.println();
			System.out.print("array : " + array[i]);
			System.out.println();
			System.out.print("array01 : " + array01[i]);
			System.out.println();
			count_check++ ;
			if(count >= count_check){
				//0000000000System.out.println(Integer.parseInt(array[i]));
				
				int place_code = Integer.parseInt(array[i]) ;
				int travel_Priority = Integer.parseInt(array01[i]) ;
				
				travel.setCount(count_check);
				travel.setPlace_code(place_code);
				travel.setTravel_Priority(travel_Priority);
				
				
				
				System.out.println("travel.setTravel_Priority : " + travel.getTravel_Priority());
				System.out.println("travel.getCount : " + travel.getCount());
				service1.planPriority(travel);
				
				
			}
			
			if(count == count_check)
				count_check = 1 ;
			
			
			
		}

		
		
		//travel.setPlace_code(place_code);
		
//       
//		service1.planPriority(travel);
		return "success";
    }
	
	@ResponseBody
	@RequestMapping (value="planPlaceDelete", method=RequestMethod.POST)
    public void planPlaceDelete(String place, String plan, String group, TravelListVO travel) throws Exception{
	    
		int place_code = Integer.parseInt(place);
	    int group_Code = Integer.parseInt(group);
	    Date travel_Date = Date.valueOf(plan);
	       
	    travel.setPlace_code(place_code);
	    travel.setGroup_Code(group_Code);
	    travel.setTravel_Date(travel_Date);
	      
	    service1.planDelete(travel);
	}

	@ResponseBody
	@RequestMapping (value="planRealTimePriority", method=RequestMethod.POST)
    public List<TravelListVO> planRealTimePriority(String plan, String group, TravelListVO travel) throws Exception{
	    
		
	    int group_Code = Integer.parseInt(group);
	    Date travel_Date = Date.valueOf(plan);
	       
	    
	    travel.setGroup_Code(group_Code);
	    travel.setTravel_Date(travel_Date);
	      
	    List<TravelListVO> list = service1.planRealTimePriority(travel);
	    
		return list;
	}
	

	
}
