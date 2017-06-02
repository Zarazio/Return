package zara.zio.turn;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zara.zio.turn.domain.MemberVO;
import zara.zio.turn.persistence.MemberService;

@Controller
public class LoginController {
	
	@Inject
	private MemberService service;
	
	// �α��� ���� ���̵� & �н����� üũ (����ó��)
	@RequestMapping(value="user_check")
	@ResponseBody // ȸ�� �α�������Ȯ�� (����ó��)
	public String userConfirm(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String value = "";
		
		try {
			if(service.userin(id).equals(pass))
				value = "1";
			else 
				value = "0";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	
	// �α��� ���̵� 
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String loginForm(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		String info = (String)session.getAttribute("info");
		// �α����� �ٽ� �α���â���� �̵��ҽ�
		if(info == "admin") { // ������  
			return "mHome";
		}
		else if (info == "user"){ // �Ϲ�ȸ��
			return "uHome";
		}
		
		return "loginForm/login";
	}
	
	// ���������� �̵� ��Ʈ�ѷ�
	@RequestMapping(value="main", method = RequestMethod.GET)
	public String layoutForm() {
		return "layout";
	}
	
	//�α��� �������� ����Ȱ��ȭ 
	@RequestMapping (value="main", method=RequestMethod.POST) // �α����̵�
	public String Login(HttpServletRequest request, HttpServletResponse response, MemberVO mem) throws Exception {
		
		HttpSession session = request.getSession();
		
		String id = mem.getUser_id();
		session.setAttribute("mem", mem);
		
		if(id.equals("manager")) { // �Ŵ��� �α��� 
			session.setAttribute("info", "admin"); // ������ jstl����
			return "mHome";
		}
		
		// �Ϲ�ȸ�� �α���
		session.setAttribute("info", "user"); // ���� jstl����
		return "uHome";
	}
	
	@RequestMapping (value="logout")
	public String Logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		session.invalidate(); // ���� �α׾ƿ�
		
		return "redirect:login"; // �α׾ƿ������� �α׾ƿ� �������� �ٽ� �α��� ������ �����̷�Ʈ 
	}
}
