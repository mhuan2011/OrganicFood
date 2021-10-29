package organicfood.controller;


import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import organicfood.bean.Account;

@Controller
@Transactional
public class FrontendController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("")
	public String index() {
		return "frontend/index";
	}
	
	@RequestMapping("index")
	public String showIndex() {
		return "frontend/index";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String showlogin(ModelMap model, HttpSession session, HttpServletRequest request) {
		Account account = checkCookie(request);
		if (account == null) {
			model.addAttribute("user", new Account());
			
			
		} else {
			model.addAttribute("user", account);
			model.addAttribute("phone", account.getUsername());
			model.addAttribute("password", account.getPassword());
			System.out.print(account.getUsername());
			
		}
		
		return "frontend/loginSignup";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginAccess(@ModelAttribute("user") Account a, HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession s) {
		String phone = a.getUsername();
		String password = a.getPassword();
		String b = request.getParameter("rememberMe");
		//validation 
		
		
		
		boolean check = true;
		if(phone.trim().equals("")) {
			model.addAttribute("phoneError", "Hãy nhập số điện thoại!");
			check = false;
		}

		if(password.equals("")) {
			model.addAttribute("passwordError", "Hãy nhập mật khẩu!");
			check = false;
		}

		if(check){
			//check exist account
			Session session = factory.getCurrentSession();
			String hql = "SELECT name FROM KhachHang WHERE phone = '"+phone+"' AND password = '"+password+"'";
			Query query = session.createQuery(hql);
			Integer result = query.getFirstResult();
			List<Object[]> list = query.list();
			
			if(list.size() != 0) {
				if(b=="on") {
					Cookie ckUsername = new Cookie("username", a.getUsername());
					ckUsername.setMaxAge(3600);
					response.addCookie(ckUsername);
					Cookie ckPassword = new Cookie("password", a.getPassword());
					ckPassword.setMaxAge(3600);
					response.addCookie(ckPassword);
				}else {
					
				}
				
				
				
				s.setAttribute("user", a);
				return "redirect:/index.html";  
			}
//			if(result != null) {
//				return "redirect:/index.html"; 
//			}
			
			
		}
		model.addAttribute("message", "Sai mã nhân viên hoặc mật khẩu !");
		return "frontend/loginSignup";
		
	}
//	@RequestMapping(value = "login", method = RequestMethod.POST)
//	public String login(@ModelAttribute(value = "account") Account account, ModelMap modelMap, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
//		AccountModel accountModel = new AccountModel();
//		if (accountModel.login(account.getUsername(), account.getPassword())) {
//			session.setAttribute("username", account.getUsername());
//			if (request.getParameter("remember") != null) {
//				Cookie ckUsername = new Cookie("username", account.getUsername());
//				ckUsername.setMaxAge(3600);
//				response.addCookie(ckUsername);
//				Cookie ckPassword = new Cookie("password", account.getPassword());
//				ckPassword.setMaxAge(3600);
//				response.addCookie(ckPassword);
//			}
//			return "account/welcome";
//		} else {
//			modelMap.put("error", "Account's Invalid");
//			return "account/index";
//		}
//	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		// Remove session
		session.removeAttribute("username");
		// Remove cookie
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equalsIgnoreCase("username")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			if (cookie.getName().equalsIgnoreCase("password")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		return "redirect:/account/login";
	}
	
	public Account checkCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		
		Account account = null;
		String username = "", password = "";
		if(cookies==null) return account;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equalsIgnoreCase("username")) {
				username = cookie.getValue();
			}
			if (cookie.getName().equalsIgnoreCase("password")) {
				password = cookie.getValue();
			}
		}
		if (!username.isEmpty() && !password.isEmpty()) {
			account = new Account(username, password);
		}
		return account;
	}
}
