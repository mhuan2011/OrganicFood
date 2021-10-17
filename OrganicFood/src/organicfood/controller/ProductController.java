package organicfood.controller;

import java.util.List;

import org.hibernate.Query;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import organicfood.entity.LoaiNongSan;
import organicfood.entity.NongSan;






@Controller
@Transactional
@RequestMapping("/admin/product/")
public class ProductController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String index(ModelMap model) {
		
		List<NongSan> list = getProductList();
		model.addAttribute("product", list);
		return "admin/product/index";
	}
	public List<NongSan> getProductList(){
		Session session = factory.getCurrentSession();
		String hql = "FROM NongSan";
	
		Query query = session.createQuery(hql);
		List<NongSan> list = query.list();
		
	
		
		return list;
	}
	
	@RequestMapping("/add-product")
	public String addCategory(ModelMap model) {
		model.addAttribute("product", new NongSan());
		model.addAttribute("btnStatus", "btnAdd");
		return "admin/product/addProduct";
	}
	
	@ModelAttribute("category")
	public List<LoaiNongSan> getCategoryList(){
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiNongSan";
		Query query = session.createQuery(hql);
		List<LoaiNongSan> list = query.list();
		return list;
	}
	//add product
	@RequestMapping(value="/add-product", method=RequestMethod.POST, params = "btnAdd")
	public String addProduct(ModelMap model, @ModelAttribute("product") NongSan product) {
		
		
		return "redirect:/admin/product.html";
	}
}
