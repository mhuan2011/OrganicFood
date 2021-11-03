package organicfood.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.BlogDao;
import dao.CategoryBlogDao;
import dao.NongSanDao;
import organicfood.bean.Account;
import organicfood.entity.BaiViet;
import organicfood.entity.LoaiBV;
import organicfood.entity.NongSan;

@Transactional
@Controller
public class HomeController {

	@Autowired
	SessionFactory factory;

	public void initHomeView(ModelMap model) {

		model.addAttribute("categoryProducts", NongSanDao.getCategoryProducts(factory));
		model.addAttribute("featuredCategory", NongSanDao.getFeaturedCategoryProduct(factory, 4));
		model.addAttribute("recentBlogs", BlogDao.getRecentBlogs(factory, 3));
	}


	
	  
	  @RequestMapping("index") 
	  public String viewBlog(HttpServletRequest request, HttpSession session, ModelMap model) {
		  Account user = (Account) session.getAttribute("user");
			model.addAttribute("phoneNumber", user.getUsername());
	  initHomeView(model); 
	  return "frontend/index"; }
	  
	 

	@ModelAttribute("categoryBlogs")
	public List<LoaiBV> getCategoryBlogs() {
		return CategoryBlogDao.getCategoryBlogs(factory);
	}

	@ModelAttribute("recentBlogs")
	public List<BaiViet> getRecentBlogs() {
		return BlogDao.getRecentBlogs(factory, 3);
	}

	@RequestMapping("searchByCategory/{id}")
	public String searchByCategory(ModelMap model, @PathVariable("id") String maLoai) {
		/* initBlogView(model); */
		model.addAttribute("blogs", BlogDao.getBlogByCategory(factory, maLoai));
		return "frontend/blog/blog";
	}

	@RequestMapping("searchByWord")
	public String searchByWord(ModelMap model, HttpServletRequest request) {
		/* initBlogView(model); */
		String word = request.getParameter("search");
		model.addAttribute("blogs", BlogDao.getBlogBySearch(factory, word));
		return "frontend/blog/blog";
	}
}
