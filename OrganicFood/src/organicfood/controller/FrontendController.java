package organicfood.controller;


import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class FrontendController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String showIndex() {
		return "frontend/index";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginAccess(HttpServletRequest request, ModelMap model) {
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		//validation 
		
		model.addAttribute("phone", phone);
		model.addAttribute("password", phone);
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
			
			if(result != null) {
				return "redirect:/home.html"; 
			}
			
			
			
			return "login";
		}
		else{
			return "login";

		}
		
	}
}
