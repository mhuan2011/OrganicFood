package organicfood.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.BlogDao;
import dao.CategoryBlogDao;
import organicfood.entity.BaiViet;
import organicfood.entity.LoaiBV;

@Validated
@Transactional
@Controller
@RequestMapping("/blog/")
public class BlogController {
	
	@Autowired
	SessionFactory factory;
	
	public void initBlogView(ModelMap model) {
		
		model.addAttribute("categoryBlogs", CategoryBlogDao.getCategoryBlogs(factory));
		model.addAttribute("recentBlogs", BlogDao.getRecentBlogs(factory, 3));
	}
	
	@RequestMapping("blogView")
	public String viewBlog(ModelMap model, HttpServletRequest request) {
		/*
		 * initBlogView(model);
		 */
		
		 PagedListHolder pagedListHolder = new PagedListHolder(BlogDao.getBlogs(factory)); 
		 int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		 pagedListHolder.setPage(page); 
		 pagedListHolder.setMaxLinkedPages(5);
		 pagedListHolder.setPageSize(2);
		 model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("blogs", BlogDao.getBlogs(factory));
		return "frontend/blog/blog";
	}
	
	
	
	  @ModelAttribute("categoryBlogs") public List<LoaiBV> getCategoryBlogs(){
	  return CategoryBlogDao.getCategoryBlogs(factory); }
	  
	  @ModelAttribute("recentBlogs") public List<BaiViet> getRecentBlogs(){ return
	  BlogDao.getRecentBlogs(factory, 3); }
	 
	
	@RequestMapping("searchByCategory/{id}")
	public String searchByCategory(ModelMap model, @PathVariable("id") String maLoai, HttpServletRequest request) {
		/* initBlogView(model); */
		 PagedListHolder pagedListHolder = new PagedListHolder(BlogDao.getBlogByCategory(factory, maLoai)); 
		 int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		 pagedListHolder.setPage(page); 
		 pagedListHolder.setMaxLinkedPages(5);
		 pagedListHolder.setPageSize(2);
		 model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("blogs", BlogDao.getBlogByCategory(factory, maLoai));
		return "frontend/blog/blog";
	}
	
	@RequestMapping("searchByWord")
	public String searchByWord(ModelMap model, HttpServletRequest request) {
		/* initBlogView(model); */
		String word = request.getParameter("search");
		PagedListHolder pagedListHolder = new PagedListHolder(BlogDao.getBlogBySearch(factory, word)); 
		 int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		 pagedListHolder.setPage(page); 
		 pagedListHolder.setMaxLinkedPages(5);
		 pagedListHolder.setPageSize(2);
		 model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("blogs", BlogDao.getBlogBySearch(factory, word));
		return "frontend/blog/blog";
	}
	
	@RequestMapping("blogDetails/{id}.html")
	public String blogDetails(ModelMap model, @PathVariable("id") int id) {
		BaiViet bv = BlogDao.getBlog(factory, id);
		String loaiBV = bv.getLoaiBV().getMaLoai();
		model.addAttribute("relatedBlogs", BlogDao.getBlogByCategory(factory, loaiBV,3));
		model.addAttribute("blog", bv);
		return "frontend/blog/blogDetails";
	}
}
