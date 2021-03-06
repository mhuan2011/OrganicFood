package organicfood.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.Query;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

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
import org.springframework.web.multipart.MultipartFile;

import organicfood.bean.Uploadfile;
import organicfood.entity.LoaiNongSan;
import organicfood.entity.NongSan;






@Controller
@Validated
@Transactional
@RequestMapping("/admin/product/")
public class ProductController {
	private String imageFlag;
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	Uploadfile baseuploadfile;
	
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, ModelMap model) {
		
		List<NongSan> list = getProductList();
		
		 PagedListHolder pagedListHolder = new PagedListHolder(list); 
		 int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		 pagedListHolder.setPage(page); 
		 pagedListHolder.setMaxLinkedPages(5);
		 pagedListHolder.setPageSize(2);
		 model.addAttribute("pagedListHolder", pagedListHolder);
		
		//model.addAttribute("product", list);
		return "admin/product/index";
	}
	public List<NongSan> getProductList(){
		Session session = factory.getCurrentSession();
		String hql = "FROM NongSan";
	
		Query query = session.createQuery(hql);
		List<NongSan> list = query.list();
		
	
		
		return list;
	}
	
	@RequestMapping(value="/add-product", method=RequestMethod.GET)
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
	@RequestMapping(value="/add-product", method=RequestMethod.POST, params = "btnAdd" )
	public String addProduct(@Validated @ModelAttribute("product") NongSan product,HttpServletRequest request, ModelMap model,
			@RequestParam("hinhanh") MultipartFile image, BindingResult errors
			) {

		if(errors.hasErrors()){
			model.addAttribute("message", "Vui l??ng s???a c??c l???i sau ????y !");
			return "admin/product/addProduct";
		}
		
		boolean check = true;
		if(product.getId().equals("")) {
			errors.rejectValue("id", "product", "Vui l??ng nh???p id !");
			check=false;
		}
		if(product.getName().equals("")) {
			errors.rejectValue("name", "product", "Vui l??ng nh???p t??n !");
			check=false;
		}
		if(product.getPrice()==0) {
			errors.rejectValue("price", "product", "Vui l??ng nh???p gi?? !");
			check=false;
		}
		if(product.getUnit().equals("")) {
			errors.rejectValue("Unit", "product", "Vui l??ng nh???p ????n v??? t??nh !");
			check=false;
		}

		if(!check) {
			model.addAttribute("btnStatus", "btnAdd");
			return "admin/product/addProduct";
		}
		try { 
			
			String date =
		 LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
		 String imgName = date +image.getOriginalFilename(); 
		 String photoPath =
		 baseuploadfile.getBasePath()+File.separator +imgName; image.transferTo(new
		 File(photoPath));
		 Thread.sleep(2000);
		 
		 product.setImage(imgName);
		//start - save db
		 	Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.save(product);
				t.commit();
				model.addAttribute("message", "Th??m m???i th??nh c??ng!");
			} catch (Exception e) {
				// TODO: handle exception
				t.rollback();
				model.addAttribute("message", "Th??m m???i th???t b???i!");
			} finally {
				session.close();
			}
			
		//end- save db
		 
		 System.out.print(product.getDescription());
		 //product.setImage(imgName);
		 List<NongSan> list = getProductList();
//		 model.addAttribute("product", list);
		 
		 PagedListHolder pagedListHolder = new PagedListHolder(list); 
		 int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		 pagedListHolder.setPage(page); 
		 pagedListHolder.setMaxLinkedPages(5);
		 pagedListHolder.setPageSize(2);
		 model.addAttribute("pagedListHolder", pagedListHolder);
		 
		 return "admin/product/index"; } 
		
		catch (Exception e) {
		 model.addAttribute("message", "L???i l??u file!"); 
		 
		 }
	
		 
		
		List<NongSan> list = getProductList();
		model.addAttribute("product", list);
		//return "admin/product/add-product";
		return "redirect:/admin/product/index.html";
	}
	//update
	@RequestMapping(value="update/{id}.html", method = RequestMethod.GET)
	public String updateCategory( ModelMap model, @PathVariable("id") String id, @ModelAttribute("product") NongSan ns) {
		
		ns = this.getProduct(id);
		String url = "UploadFiles/"+ns.getImage();
		                                                                                                                                                                                                                         
		model.addAttribute("product", ns);
		model.addAttribute("btnStatus", "btnUpdate");
		
		model.addAttribute("imageLink", url);
		return "admin/product/addProduct";
	}
	public NongSan getProduct(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NongSan where id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		NongSan u = (NongSan) query.list().get(0);
		return u;
	}
	
	@RequestMapping(value="/add-product", method=RequestMethod.POST, params = "btnUpdate")
	public String updateCategory(HttpServletRequest request, ModelMap model,@ModelAttribute("CategoryProduct") NongSan ns, @RequestParam("hinhanh") MultipartFile image) {
		
		if(!image.isEmpty()) {
			try { 
				String date =
			 LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
			 String imgName = date +image.getOriginalFilename(); 
			 String photoPath =
			 baseuploadfile.getBasePath()+File.separator +imgName; 
			 image.transferTo(new File(photoPath));
			 
			 ns.setImage(imgName);
			 Thread.sleep(2000);
			}
			catch (Exception e) {
			 model.addAttribute("message", "L???i l??u file!"); 
			 
			 }
			
		}
		
		
		
		 Integer temp = this.updateProduct(ns);
			
			if(temp==1) {
				model.addAttribute("message", "C???p nh???t th??nh c??ng");
			}
			else {
				model.addAttribute("message", "C???p nh???t th???t b???i");
			}
			
			List<NongSan> list = getProductList();
			//model.addAttribute("product", list);
			PagedListHolder pagedListHolder = new PagedListHolder(list); 
			 int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			 pagedListHolder.setPage(page); 
			 pagedListHolder.setMaxLinkedPages(5);
			 pagedListHolder.setPageSize(2);
			 model.addAttribute("pagedListHolder", pagedListHolder);
			
			return "redirect:/admin/product/index.html";
			

	}
	public Integer updateProduct(NongSan product) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(product);
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
	//delete product
	
	
	//search
	@RequestMapping(value = "index", params = "btnsearch")
	public String seachPro(HttpServletRequest request, ModelMap model,
			@RequestParam("search") String product_name
			
			) {
		

		List<NongSan> products = this.searchProducts(product_name);
		PagedListHolder pagedListHolder = new PagedListHolder(products); 
		 int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		 pagedListHolder.setPage(page); 
		 pagedListHolder.setMaxLinkedPages(5);
		 pagedListHolder.setPageSize(2);
		 model.addAttribute("pagedListHolder", pagedListHolder);
		//model.addAttribute("product", products);
		model.addAttribute("searchText", product_name);
		
		return "admin/product/index";
	}
	
	public List<NongSan> searchProducts(String product_name) {

		Session session = factory.getCurrentSession();
		String hql = "FROM NongSan where name LIKE :product_name";
		Query query = session.createQuery(hql);
		query.setParameter("product_name", "%" + product_name + "%");
		List<NongSan> list = query.list();

		return list;
	}
	
}
