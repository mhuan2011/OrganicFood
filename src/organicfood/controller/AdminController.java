package organicfood.controller;


import java.util.ArrayList;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


import organicfood.bean.Account;
import organicfood.bean.Uploadfile;
import organicfood.entity.ChiTietDDH;
import organicfood.entity.DVVC;
import organicfood.entity.DatHang;
import organicfood.entity.KhachHang;
import organicfood.entity.LoaiNongSan;
import organicfood.entity.NongSan;





@Controller
@Transactional
@RequestMapping("/admin/")
public class AdminController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	Uploadfile baseuploadfile;
	
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
	public String insert(ModelMap model, @ModelAttribute("CategoryProduct") LoaiNongSan lns, @RequestParam("hinhanh") MultipartFile image, BindingResult errors) {
		
		boolean check = true;
		
		if(lns.getId().equals("")) {
			errors.rejectValue("id", "CategoryProduct", "Vui lòng nhập id !");
			check=false;
		}
		if(lns.getName().equals("")) {
			errors.rejectValue("name", "CategoryProduct", "Vui lòng nhập tên !");
			check=false;
		}
		if(lns.getImage().equals("")) {
			errors.rejectValue("image", "CategoryProduct", "Vui lòng chọn hình ảnh !");
			check=false;
		}
		
		if(!check) {
			model.addAttribute("btnStatus", "btnAdd");
			return "admin/product/addCategory";
		}
		
		if(check ) {
			try {
				String date =
						 LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
						 String imgName = date +image.getOriginalFilename(); 
						 String photoPath =
						 baseuploadfile.getBasePath()+File.separator +imgName; image.transferTo(new
						 File(photoPath));
						 Thread.sleep(3000);
				lns.setImage(imgName);
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
				
			} catch (Exception e) {
				e.printStackTrace();
			}
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
	public String updateCategory(ModelMap model, @ModelAttribute("CategoryProduct") LoaiNongSan lns, @RequestParam("hinhanh") MultipartFile image) {
		
		if(!image.isEmpty()) {
			try { 
				String date =
			 LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
			 String imgName = date +image.getOriginalFilename(); 
			 String photoPath =
			 baseuploadfile.getBasePath()+File.separator +imgName; 
			 image.transferTo(new File(photoPath));
			 
			 lns.setImage(imgName);
			 Thread.sleep(3000);
			}
			catch (Exception e) {
			 model.addAttribute("message", "Lỗi lưu file!"); 
			 
			 }
			
		}
		
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
			
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();	
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	//delete category
	@RequestMapping(value="/product/category/delete/{id}.html")
	public String deleteCategory( ModelMap model, @PathVariable("id") String id) {
		
		LoaiNongSan lns = this.getCategoryProduct(id);
		
		int result = deleteCategories2(lns);
		if(result == 1) {
			model.addAttribute("message", "Xóa thành công");
		}
		else {
			model.addAttribute("message", "Xóa thất bại");
		}
		List<LoaiNongSan> list = getCategoryList();
		model.addAttribute("LoaiNongSan", list);
		return "redirect:/admin/product/category.html";
	}
	
	public Integer deleteCategories(LoaiNongSan lns) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(lns);
			t.commit();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	public Integer deleteCategories2(LoaiNongSan lns) {
		Session session = factory.getCurrentSession();
		String hql = "delete LoaiNongSan where id = :id";
		
		Query query = session.createQuery(hql);
		query.setParameter("id", lns.getId());
		int result = query.executeUpdate();
		if(result > 0) return 1;
		return 0;
		
	}
	//Nhan da o day ===============================================================
//	-------------------------------------------------------------------client-----------------------------------------------------------------------
	@RequestMapping("/client")
	public String showClient(ModelMap model) {
		List<KhachHang> list = getClient();
		model.addAttribute("KhachHang", list);
		return "admin/client/client";
	}
	public List<KhachHang> getClient(){
		Session session = factory.getCurrentSession();
		String hql = "FROM KhachHang";
		Query query = session.createQuery(hql);
		List<KhachHang> list = query.list();
		return list;
	}
	//search
		@RequestMapping(value = "/search-client", params = "btnsearch")
		public String seachPro(HttpServletRequest request, ModelMap model,
				@RequestParam("search") String client_name
				
				) {
			

			List<KhachHang> clients = this.searchClients(client_name);
			PagedListHolder pagedListHolder = new PagedListHolder(clients); 
			 int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			 pagedListHolder.setPage(page); 
			 pagedListHolder.setMaxLinkedPages(5);
			 pagedListHolder.setPageSize(2);
			 model.addAttribute("pagedListHolder", pagedListHolder);
			model.addAttribute("searchText", client_name);
			model.addAttribute("KhachHang", clients);
			return "admin/client/client";
		}
		
		public List<KhachHang> searchClients(String client_name) {

			Session session = factory.getCurrentSession();
			String hql = "FROM KhachHang where name LIKE :client_name";
			Query query = session.createQuery(hql);
			query.setParameter("client_name", "%" + client_name + "%");
			List<KhachHang> list = query.list();

			return list;
		}
//	--------------------------------------------------------------don vi van chuyen------------------------------------------------------------------
	@RequestMapping("/dvvc")
	public String showDVVC(ModelMap model) {
		List<DVVC> list = getDVVC();
		model.addAttribute("DonViVanChuyen", list);
		return "admin/dvvc/dvvc";
	}
	public List<DVVC> getDVVC(){
		Session session = factory.getCurrentSession();
		String hql = "FROM DVVC";
		Query query = session.createQuery(hql);
		List<DVVC> list = query.list();
		return list;
	}
	//add don vi van chuyen
	
	@RequestMapping("/dvvc/add-dvvc")
	public String addDvvc(ModelMap model) {
		model.addAttribute("DVVC", new DVVC());
		model.addAttribute("btnStatus", "btnAdd");
		return "admin/dvvc/addDVVC";
	}
	@RequestMapping(value="/dvvc/insert-dvvc", method=RequestMethod.POST, params = "btnAdd")
	public String insertDvvc(ModelMap model,@Validated @ModelAttribute("DVVC") DVVC dvvc, BindingResult errors) {
		
		boolean check = true;
		if(dvvc.getMadv().equals("")) {
			errors.rejectValue("madv", "DVVC", "Vui lòng nhập mã đơn vị !");
			check=false;
		}
		if(dvvc.getTendv().equals("")) {
			errors.rejectValue("tendv", "DVVC", "Vui lòng nhập tên đơn vị !");
			check=false;
		}
		if(dvvc.getGiavc()==null) {
			errors.rejectValue("giavc", "DVVC", "Vui lòng nhập giá vận chuyển !");
			check=false;
		}
		if(dvvc.getThoigianvc().equals("")) {
			errors.rejectValue("thoigianvc", "DVVC", "Vui lòng nhập thời gian vận chuyển !");
			check=false;
		}
		if(!check) {
			model.addAttribute("btnStatus", "btnAdd");
			return "admin/dvvc/addDVVC";
		}{
			//start - save db
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.save(dvvc);
				t.commit();
				model.addAttribute("message", "Thêm mới thành công!");
			} catch (Exception e) {
				// TODO: handle exception
				t.rollback();
				model.addAttribute("message", "Thêm mới thất bại!");
			} finally {
				session.close();
			}
			List<DVVC> list = getDVVC();
			model.addAttribute("DVVC", list);
			return "redirect:/admin/dvvc.html";
			//return "admin/dvvc/dvvc";
		}
		
	}
	//update đơn vị vận chuyển
		@RequestMapping(value="/dvvc/update/{madv}.html")
		public String updateDvvc( ModelMap model, @PathVariable("madv") String madv, @ModelAttribute("DVVC") DVVC dvvc) {
			
			dvvc = this.get1DVVC(madv);
			model.addAttribute("DVVC", dvvc);
			model.addAttribute("btnStatus", "btnUpdate");
			return "admin/dvvc/addDVVC";
		}
		public DVVC get1DVVC(String madv) {
			Session session = factory.getCurrentSession();
			String hql = "FROM DVVC where madv = :madv";
			Query query = session.createQuery(hql);
			query.setParameter("madv", madv);
			DVVC u = (DVVC) query.list().get(0);
			return u;
		}
		@RequestMapping(value="/dvvc/insert-dvvc", method=RequestMethod.POST, params = "btnUpdate")
		public String updateDvvc(ModelMap model, @ModelAttribute("DVVC") DVVC dvvc) {
			Integer temp = this.updateDvvc(dvvc);
			
			if(temp==1) {
				model.addAttribute("message", "Cập nhật thành công");
			}
			else {
				model.addAttribute("message", "Cập nhật thất bại");
			}
			List<DVVC> list = getDVVC();
			model.addAttribute("DVVC", list);
			return "redirect:/admin/dvvc.html";
			//return "admin/dvvc/dvvc";
		}
		
		public Integer updateDvvc(DVVC dvvc) {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.update(dvvc);
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
//----------------------------------------------dat hang--------------------------------
		@RequestMapping("/dathang")
		public String showDatHang(ModelMap model) {
			List<DatHang> list = getDatHang();
			model.addAttribute("DatHang", list);
			return "admin/dathang/dathang";
		}
		public List<DatHang> getDatHang(){
			Session session = factory.getCurrentSession();
			String hql = "FROM DatHang";
			Query query = session.createQuery(hql);
			List<DatHang> list = query.list();
			return list;
		}
		//update đơn vị vận chuyển
		@RequestMapping(value="/dathang/edit/{masoddh}.html")
		public String updateDatHang( ModelMap model, @PathVariable("masoddh") String masoddh, @ModelAttribute("DatHang") DatHang dathang) {
			
			dathang = this.get1Dathang(masoddh);
			model.addAttribute("DatHang", dathang);
			
			List<ChiTietDDH> ctddh=getCTDDH(masoddh);
			model.addAttribute("CTDDH",ctddh);
			return "admin/dathang/editDathang";
		}
		public List<ChiTietDDH> getCTDDH(String masoddh) {
			Session session = factory.getCurrentSession();
			String hql = "FROM ChiTietDDH WHERE masoddh.masoddh = :masoddh";
			Query query = session.createQuery(hql);
			query.setParameter("masoddh", masoddh);
			List<ChiTietDDH> list = query.list();
			return list;
		}
		public DatHang get1Dathang(String masoddh) {
			Session session = factory.getCurrentSession();
			String hql = "FROM DatHang where masoddh = :masoddh";
			Query query = session.createQuery(hql);
			query.setParameter("masoddh", masoddh);
			DatHang u = (DatHang) query.list().get(0);
			return u;
		}
		@RequestMapping(value="dathang/edit-dathang", method=RequestMethod.POST)
		public String updateDatHang(ModelMap model, @ModelAttribute("DatHang") DatHang dathang) {
			Integer temp = this.updateDathang(dathang);
			
			if(temp==1) {
				model.addAttribute("message", "Cập nhật thành công");
			}
			else {
				model.addAttribute("message", "Cập nhật thất bại");
			}
			List<DatHang> list = getDatHang();
			model.addAttribute("DatHang", list);
			return "redirect:/admin/dathang.html";
		}
		
		public Integer updateDathang(DatHang dathang) {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.update(dathang);
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
		@ModelAttribute("dstrangthai")
		public List<String> getTT(){
			List<String> tt = new ArrayList<String>();
			tt.add("Chưa duyệt");
			tt.add("Đã duyệt");
			tt.add("Đang giao");
			tt.add("Đã giao");
			return tt;
		}
}

