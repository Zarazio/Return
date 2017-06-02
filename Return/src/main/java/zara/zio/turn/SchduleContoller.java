package zara.zio.turn;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SchduleContoller {
	
	@RequestMapping (value="scheduleSet", method=RequestMethod.POST) // 스케쥴 페이지 이동 
	public String schduleSet(String scheduleDate, Model model) {
		
		System.out.println(scheduleDate);
		
		if(scheduleDate.isEmpty())
			scheduleDate = "nullTest";
		
		model.addAttribute("scheduleDate", scheduleDate);
		
		return "schedulePage/schdulePageA";
	}
	
	@RequestMapping (value="scheduleSet", method=RequestMethod.GET) // 스케쥴 페이지 이동 
	public String schduleSetA(String scheduleDate, Model model) {
		
		System.out.println(scheduleDate);
		
		if(scheduleDate == null)
			scheduleDate = "nullTest";
		
		model.addAttribute("scheduleDate", scheduleDate);
		
		return "schedulePage/schdulePageA";
	}
	
}
