package organicfood.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.CategoryBlogDao;
import organicfood.entity.LoaiBV;

@Transactional
@Controller
@RequestMapping("/admin/categoryBlog/")
public class CategoryBlogController {
	@Autowired
	ServletContext context;
	
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String catalogory(ModelMap model, HttpServletRequest request) {
		
		List<LoaiBV> list = this.getCategoryBlogs();
		String search=request.getParameter("search");
		if(search!=null) {
			list= CategoryBlogDao.search(search, factory);
		}else {
			model.addAttribute("searchText", search);
		}
		
		 PagedListHolder pagedListHolder = new PagedListHolder(list); 
		 int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		 pagedListHolder.setPage(page); 
		 pagedListHolder.setMaxLinkedPages(5);
		 pagedListHolder.setPageSize(2);
		 model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("categoryBlogs", list);
		
		return "admin/categoryBlog/index";
	}
	

	private List<LoaiBV> getCategoryBlogs(){
		List<LoaiBV> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiBV";
		Query query = session.createQuery(hql);
		list = query.list();
		return list;
	}
	
	private LoaiBV getCategoryBlog(String id) {
		
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiBV where maLoai = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		LoaiBV u = (LoaiBV) query.list().get(0);
		return u;
	}
	
	@RequestMapping("add")
	public String addCategory(ModelMap model, @ModelAttribute("LoaiBV") LoaiBV loaiBV) {
		model.addAttribute("LoaiBV", new LoaiBV());
		model.addAttribute("btnStatus", "btnAdd");
		return "admin/categoryBlog/addCategory";
	}
	@RequestMapping(value="insert-category", method=RequestMethod.POST, params = "btnAdd")
	public String insert(ModelMap model,@Validated @ModelAttribute("LoaiBV") LoaiBV loaiBV, BindingResult errors) {
		if(errors.hasErrors()) {
			model.addAttribute("btnStatus", "btnAdd");
			return "admin/categoryBlog/addCategory";
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			loaiBV.setMaLoai(loaiBV.getMaLoai().trim());
			session.save(loaiBV);
			t.commit();
			model.addAttribute("message", "Thêm thành công!");
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			model.addAttribute("message", "Thêm thất bại!");
		} finally {
			session.close();
		}
		List<LoaiBV> list = this.getCategoryBlogs();
		model.addAttribute("categoryBlogs", list);
		return "redirect:/admin/categoryBlog/index.html";
	}
	@RequestMapping("update/{id}")
	public String updateCategory(ModelMap model, @ModelAttribute("LoaiBV") LoaiBV loaiBV,  @PathVariable("id") String id) {
		loaiBV = this.getCategoryBlog(id);
		model.addAttribute("LoaiBV", loaiBV);
		System.out.print(loaiBV.getMaLoai()+loaiBV.getTenLoai()+loaiBV.getTag());
		model.addAttribute("btnStatus", "btnUpdate");
		return "admin/categoryBlog/addCategory";
	}
	
	@RequestMapping(value="insert-category", method=RequestMethod.POST, params = "btnUpdate")
	public String updateCategory(ModelMap model, @ModelAttribute("LoaiBV") LoaiBV lns) {
		Integer temp = this.updateCategory(lns);
		System.out.print(lns.getMaLoai()+lns.getTenLoai()+lns.getTag());
		if(temp==1) {
			model.addAttribute("message", "CĂ¡ÂºÂ­p nhĂ¡ÂºÂ­t thÄ‚Â nh cÄ‚Â´ng");
		}
		else {
			model.addAttribute("message", "CĂ¡ÂºÂ­p nhĂ¡ÂºÂ­t thĂ¡ÂºÂ¥t bĂ¡ÂºÂ¡i");
		}
		List<LoaiBV> list = this.getCategoryBlogs();
		model.addAttribute("categoryBlogs", list);
		return "redirect:/admin/categoryBlog/index.html";
	}
	
	public Integer updateCategory(LoaiBV category) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(category);
			t.commit();
			return 1;
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();	
		} finally {
			session.close();
		}
		return 0;
	}
	
	@RequestMapping(value="delete/{id}.html")
	public String deleteCategory(ModelMap model, @PathVariable("id") String id) {
		if(CategoryBlogDao.delete(factory, id)) {
			model.addAttribute("message","Xóa thành công!");
		}else model.addAttribute("message", "Xóa thất bại!");
		
		List<LoaiBV> list = this.getCategoryBlogs();
		model.addAttribute("categoryBlogs", list);
		return "redirect:/admin/categoryBlog/index.html";
	}
	
	
}
