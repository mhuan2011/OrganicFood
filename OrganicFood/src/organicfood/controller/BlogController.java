package organicfood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog/")
public class BlogController {
	@RequestMapping("index")
	public String index() {
		return "frontend/blog/blog";
	}
}
