package organicfood.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import organicfood.bean.Account;

@Controller
@Transactional
@RequestMapping("/admin/")
public class AdminController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String showLogin(ModelMap model) {
		model.addAttribute("account", new Account());
		return "admin/login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginAccess(@ModelAttribute("account") Account a, ModelMap model, BindingResult errors) {
		String username = a.getUsername();
		String password = a.getPassword();
		//validation 
		
		if(username.trim().length() == 0){
			errors.rejectValue("username", "account", "Hãy nhập mã nhân viên!");
		}
		if(password.trim().length() == 0){
			errors.rejectValue("password", "account", "Hãy nhập mật khẩu!");
		}
		
		if(errors.hasErrors()){
			return "admin/login";
		}
		else{		
			//check exist account
			
			Session session = factory.getCurrentSession();
			String hql = "SELECT firstName FROM NhanVien WHERE id ='"+username+"' AND password='"+password+"'";
			Query query = session.createQuery(hql);
			List<Object[]> list = query.list();
			Integer result = query.getFirstResult();
			
			if(list.size() != 0) {
				return "redirect:/admin/home.html"; 
			}
			model.addAttribute("message", "Sai mã nhân viên hoặc mật khẩu !");

			
			
			return "admin/login";

		}
		
	}
	@RequestMapping("home")
	public String showHomePages() {
		return "admin/home";
	}
}
