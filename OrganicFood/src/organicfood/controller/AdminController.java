package organicfood.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import organicfood.bean.Account;
import organicfood.entity.LoaiNongSan;





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
	public String loginAccess(@ModelAttribute("account") Account a, ModelMap model, BindingResult errors, HttpSession s) {
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
			//String hql = "FROM NhanVien WHERE id ='"+username+"' AND password='"+password+"'";
			Query query = session.createQuery(hql);
			List<Object[]> list = query.list();
			//Integer result = query.getFirstResult();


			if(list.size() != 0) {
				
				s.setAttribute("account", a);
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
//	logout
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("account");
		return "redirect:/admin/login.html";
	}
//	product category
	@RequestMapping("/product/category")
	public String showCategory(ModelMap model) {
		List<LoaiNongSan> list = getCategoryList();
		model.addAttribute("LoaiNongSan", list);
		return "admin/product/category";
	}
	//add category 
	
	@RequestMapping("/product/add-category")
	public String addCategory(ModelMap model) {
		model.addAttribute("CategoryProduct", new LoaiNongSan());
		model.addAttribute("btnStatus", "btnAdd");
		return "admin/product/addCategory";
	}
	@RequestMapping(value="/product/insert-category", method=RequestMethod.POST, params = "btnAdd")
	public String insert(ModelMap model, @ModelAttribute("CategoryProduct") LoaiNongSan lns) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(lns);
			t.commit();
			model.addAttribute("message", "Thêm mới thành công!");
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			model.addAttribute("message", "Thêm mới thất bại!");
		} finally {
			session.close();
		}
		List<LoaiNongSan> list = getCategoryList();
		model.addAttribute("LoaiNongSan", list);
		return "redirect:/admin/product/category.html";
	}
	public List<LoaiNongSan> getCategoryList(){
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiNongSan";
		Query query = session.createQuery(hql);
		List<LoaiNongSan> list = query.list();
		return list;
	}
	//update category
	@RequestMapping(value="/product/category/update/{id}.html")
	public String updateCategory( ModelMap model, @PathVariable("id") String id, @ModelAttribute("LoaiNongSan") LoaiNongSan lns) {
		
		lns = this.getCategoryProduct(id);
		model.addAttribute("CategoryProduct", lns);
		model.addAttribute("btnStatus", "btnUpdate");
		return "admin/product/addCategory";
	}
	public LoaiNongSan getCategoryProduct(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiNongSan where id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		LoaiNongSan u = (LoaiNongSan) query.list().get(0);
		return u;
	}
	@RequestMapping(value="/product/insert-category", method=RequestMethod.POST, params = "btnUpdate")
	public String updateCategory(ModelMap model, @ModelAttribute("CategoryProduct") LoaiNongSan lns) {
		Integer temp = this.updateCategory(lns);
		
		if(temp==1) {
			model.addAttribute("message", "Cập nhật thành công");
		}
		else {
			model.addAttribute("message", "Cập nhật thất bại");
		}
		List<LoaiNongSan> list = getCategoryList();
		model.addAttribute("LoaiNongSan", list);
		return "redirect:/admin/product/category.html";
//		return "admin/product/category";
	}
	
	public Integer updateCategory(LoaiNongSan category) {
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
	//delete category
	@RequestMapping(value="/product/category/delete/{id}.html")
	public String deleteCategory( ModelMap model, @PathVariable("id") String id, @ModelAttribute("LoaiNongSan") LoaiNongSan lns) {
		
		lns = this.getCategoryProduct(id);
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(lns);
			t.commit();
			model.addAttribute("message", "Xóa thành công");
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			model.addAttribute("message", "Xóa thất bại");
		} finally {
			session.close();
		}
		List<LoaiNongSan> list = getCategoryList();
		model.addAttribute("LoaiNongSan", list);
		return "redirect:/admin/product/category.html";
	}
}

