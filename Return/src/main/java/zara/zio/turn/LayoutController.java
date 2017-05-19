package zara.zio.turn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="*")
public class LayoutController {
	// 메인페이지 인덱스 
	@RequestMapping(value="main", method = RequestMethod.GET)
	public String layoutForm() {
		return "layout";
	}
}
