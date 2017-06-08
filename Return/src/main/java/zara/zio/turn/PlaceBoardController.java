package zara.zio.turn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PlaceBoardController {
	
	@RequestMapping(value="/placeInfo", method = RequestMethod.GET)
	public String placeInfo() {
		
		return "placeBorad/placeInfo";
	}
	
}
