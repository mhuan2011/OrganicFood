package organicfood.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class FrontendController {
	@RequestMapping("index")
	public String showIndex() {
		return "frontend/index";
	}
}
