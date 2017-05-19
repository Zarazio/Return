package zara.zio.turn;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zara.zio.turn.domain.MemberVO;
import zara.zio.turn.persistence.MemberService;

/**
 * Handles requests for the application home page.
 */

@Controller
public class RegController {
	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
//	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
//		
//		return "home";
//	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@Inject
	private MemberService service;
	
	// ȸ������ ���̵� 
	@RequestMapping(value="register", method = RequestMethod.GET)
	public String registerForm() {
		return "registerForm/register";
	}
	
	// ȸ������ �����Է� ����
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String create(MemberVO mem) throws Exception {
		String yyyy = mem.getYyyy();
		String mm = mem.getMm();
		String dd = mem.getDd();
		String birth = yyyy + "-" + mm + "-" + dd;
		mem.setUser_birth(birth);
		service.regist(mem);
		return "redirect:login";
		
	}
	
	// ����� �ϰ� �ٽ� ���ΰ�ħ�Ͽ����� �����ִ� �ּ������� �ʱ�ȭ 
    //	@RequestMapping(value="success")
	//	public String regResult() {
	//		return "registerForm/success";
	//	}
	
	// ���̵� �ߺ�üũ (����ó��)
	@RequestMapping(value="confirm")
	@ResponseBody // ��Ͻ� ���̵��ߺ�Ȯ�� (����ó��)
	public String idConfirm(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		System.out.println(id);
		String text = "";
		try {
			text = service.confirm(id) + "";
			System.out.println(text);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}
	
}
