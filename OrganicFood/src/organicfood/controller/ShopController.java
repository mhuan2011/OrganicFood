package organicfood.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import organicfood.bean.Uploadfile;
import organicfood.entity.LoaiNongSan;
import organicfood.entity.NongSan;

@Controller
@Transactional
@RequestMapping("/shop/")
public class ShopController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	Uploadfile baseuploadfile;
	
	public ArrayList<NongSan> listNS= new ArrayList<NongSan>();
	
	@RequestMapping("shop")
	public String showShow(ModelMap model) {
		List<NongSan> list = getProductList();
		List<LoaiNongSan> list1=getCategoryList();
		model.addAttribute("product", list);
		model.addAttribute("category",list1);
		return "frontend/shop/shop";
	}
	public List<NongSan> getProductList(){
		Session session = factory.getCurrentSession();
		String hql = "FROM NongSan";
		Query query = session.createQuery(hql);
		List<NongSan> list = query.list();
		return list;
	}
	public List<LoaiNongSan> getCategoryList(){
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiNongSan";
		Query query = session.createQuery(hql);
		List<LoaiNongSan> list = query.list();
		return list;
	}
	
	@RequestMapping(value="shop-detail/{id}.html")
	public String showDetail( ModelMap model, @PathVariable("id") String id, @ModelAttribute("product") NongSan ns) {
		
		ns = this.getProduct(id);
		String url = "UploadFiles/"+ns.getImage();                                                                                                                                                                                                                      
		model.addAttribute("product", ns);
		model.addAttribute("imageLink", url);
		return "frontend/shop/shop-detail";
	}
	public NongSan getProduct(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NongSan where id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		NongSan u = (NongSan) query.list().get(0);
		return u;
	}
	@RequestMapping(value="shop-category/{id}.html")
	public String showProductFollowCate( ModelMap model, @PathVariable("id") String id, @ModelAttribute("product") NongSan ns) {
		List<NongSan> list = getProductListFollowCategory(id);
		List<LoaiNongSan> list1=getCategoryList();
		model.addAttribute("product", list);
		model.addAttribute("category",list1);
		return "frontend/shop/shop";
	}
	public List<NongSan> getProductListFollowCategory(String id){
		Session session = factory.getCurrentSession();
		String hql = "FROM NongSan NS where NS.category.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<NongSan> list = query.list();
		return list;
	}
	//------------------------------searchProduct
	@RequestMapping(value = "search", params = "btnsearch")
	public String seachPro(HttpServletRequest request, ModelMap model,
			@RequestParam("search") String product_name) {
		List<NongSan> products = this.searchProducts(product_name); 
		model.addAttribute("product", products);
		model.addAttribute("searchText", product_name);
		return "frontend/shop/shop";
	}
	
	public List<NongSan> searchProducts(String product_name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NongSan where name LIKE :product_name";
		Query query = session.createQuery(hql);
		query.setParameter("product_name", "%" + product_name + "%");
		List<NongSan> list = query.list();
		return list;
	}
	//------------------------------searchPrice
		@RequestMapping(value = "searchPrice", params = "btnFilterPrice")
		public String seachPrice(HttpServletRequest request, ModelMap model,
				@RequestParam("price1") String price1,@RequestParam("price2") String price2) {
			System.out.print("gia 1:"+price1+","+price2);
			model.addAttribute("filterPrice1", price1);
			model.addAttribute("filterPrice2", price2);
			price1 = price1.substring(1);
			price2 = price2.substring(1);
			float fprice1=Float.parseFloat(price1);
			float fprice2=Float.parseFloat(price2);
			List<NongSan> products = this.searchPrice(fprice1,fprice2); 
			model.addAttribute("product", products);
			float left1=fprice1*100/fprice2;
			String sleft1=left1+"";
			model.addAttribute("sleft1",sleft1);
			return "frontend/shop/shop";
		}
		
		public List<NongSan> searchPrice(float price1,float price2) {
			Session session = factory.getCurrentSession();
			String hql = "FROM NongSan where price >= :price1 and price <= :price2";
			Query query = session.createQuery(hql);
			query.setParameter("price1", price1);
			query.setParameter("price2", price2);
			List<NongSan> list = query.list();
			return list;
		}
		//them vao gio hang
		@RequestMapping(value="shoppingCart/{id}.html")
		public String addToCart( ModelMap model, @PathVariable("id") String id, @ModelAttribute("product") NongSan ns) {
			
			ns = this.getProduct(id);
			String url = "UploadFiles/"+ns.getImage();                                                                                                                                                                                                                      
			model.addAttribute("product", ns);
			model.addAttribute("imageLink", url);
			listNS.add(ns);
			model.addAttribute("listNS",listNS);
			return "frontend/shoppingCart";
		}
		//cap nhat gio hang
		
}
