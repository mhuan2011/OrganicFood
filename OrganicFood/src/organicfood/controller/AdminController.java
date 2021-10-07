package organicfood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	@RequestMapping("login")
	public String showLogin() {
		return "admin/login";
	}
	@RequestMapping("home")
	public String showHomePages() {
		return "admin/home";
	}
}
