package organicfood.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.multipart.MultipartFile;

import dao.BlogDao;
import dao.CategoryBlogDao;
import organicfood.bean.Account;
import organicfood.bean.Mailer;
import organicfood.bean.Uploadfile;
import organicfood.entity.BaiViet;
import organicfood.entity.LoaiBV;
import organicfood.entity.LoaiNongSan;
import organicfood.entity.NhanVien;
import organicfood.entity.NongSan;

@Transactional
@Controller
@RequestMapping("/admin/blog/")
public class ManagerBlogController {
	
	private int idBlog=0;
	private NhanVien nv=null;
	
	private String imgFlag;
	@Autowired
	ServletContext context;
	
	@Autowired
	Uploadfile baseuploadfile;
	
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String managerBlog(ModelMap model, HttpServletRequest request) {
		List<BaiViet> list = this.getBlogs();
		
		String search=request.getParameter("search");
		if(search!=null) {
			list= BlogDao.search(search, factory);
		}else {
			model.addAttribute("searchText", search);
		}
		
		 PagedListHolder pagedListHolder = new PagedListHolder(list); 
		 int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		 pagedListHolder.setPage(page); 
		 pagedListHolder.setMaxLinkedPages(5);
		 pagedListHolder.setPageSize(2);
		 model.addAttribute("pagedListHolder", pagedListHolder);
		model.addAttribute("blogs", list);
		return "admin/blog/index";
	}
	
	private List<BaiViet> getBlogs(){
		List<BaiViet> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		String hql = "FROM BaiViet";
		Query query = session.createQuery(hql);
		list = query.list();
		return list;
	}
	
private BaiViet getBlog(int id) {
		
		Session session = factory.getCurrentSession();
		String hql = "FROM BaiViet where MABV = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		BaiViet u = (BaiViet) query.list().get(0);
		return u;
	}

	
	 
	  public List<LoaiBV> getCategoryBlogs2(){
	  List<LoaiBV> list = new ArrayList<>(); 
	  Session s = factory.getCurrentSession(); 
	  String hql = "FROM LoaiBV"; 
	  Query query = s.createQuery(hql); 
	  list = query.list(); 
	  return list; }
	 

	@RequestMapping("addBlog")
	public String addBlog(ModelMap model) {
		model.addAttribute("blog", new BaiViet());
		model.addAttribute("loaiBV", this.getCategoryBlogs2());
		model.addAttribute("btnStatus", "btnAdd");
		return "admin/blog/addBlog";
	}
	
	public NhanVien getNVFromSession(HttpSession s) {
		Account a = (Account) s.getAttribute("account");
		String maNV = a.getUsername();
		Session session = factory.getCurrentSession();
		String hql="FROM NhanVien where id=:maNV";
		Query query = session.createQuery(hql);
		query.setParameter("maNV", maNV);
		NhanVien nv = (NhanVien) query.list().get(0);
		return nv;
	}
	
	@Autowired
	Mailer mailer;
	
	public boolean send( String from, String to,String subject, String body ) {
		try {
			mailer.send(from, to, subject, body);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
	@RequestMapping(value="insertBlog", method=RequestMethod.POST, params = "btnAdd")
public String addBlog(ModelMap model, @Validated @ModelAttribute("blog") BaiViet blog,
		HttpServletRequest request, HttpSession s,
		@RequestParam("hinhanh") MultipartFile image, BindingResult errors) {
		String message="";
	/*
	 * System.out.print("Vào lỗi!"); if(errors.hasErrors()){
	 * System.out.print("Vào lỗi!"); model.addAttribute("message",
	 * "Vui lòng sửa các lỗi sau đây !"); model.addAttribute("blog", new BaiViet());
	 * model.addAttribute("loaiBV", this.getCategoryBlogs2());
	 * model.addAttribute("btnStatus", "btnAdd"); return "admin/blog/addBlog"; }
	 */
		
		boolean check = true;
		if(blog.getTieuDe().equals("")) {
			errors.rejectValue("tieuDe", "blog", "Vui lòng nhập tiêu đề!");
			check=false;
		}
		if(blog.getTrichDan().equals("")) {
			errors.rejectValue("trichDan", "blog", "Vui lòng nhập trích dẫn!");
			check=false;
		}
		if(blog.getNoiDung().equals("")) {
			errors.rejectValue("noiDung", "blog", "Vui lòng nhập nội dung!");
			check=false;
		}
		if(blog.getHinhAnh().equals("")) {
			errors.rejectValue("hinhAnh", "blog", "Vui lòng chọn hình ảnh!");
			check=false;
		}

		if(!check) {
			model.addAttribute("blog", blog);
			model.addAttribute("btnStatus", "btnAdd");
			model.addAttribute("loaiBV", this.getCategoryBlogs2());
			return "admin/blog/addBlog";
		}
		
		try { 
			String date =
		 LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
			
		 String imgName = date +image.getOriginalFilename(); 
		 String photoPath = baseuploadfile.getBasePath()+File.separator +imgName; 
		 image.transferTo(new File(photoPath));
		 Thread.sleep(5000);
		 
		 blog.setHinhAnh(imgName);
		 System.out.print(blog.getHinhAnh()+blog.getNgay()+blog.getMaBV());
		//start - save db
		 	Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				NhanVien nv = this.getNVFromSession(s);
				blog.setNV(this.getNVFromSession(s));
				blog.setNgay(new Date());
				session.save(blog);
				t.commit();
				// gửi mail thông báo về bài viết mới
				
				  String from = "phongthietbiptit@gmail.com";
				  String email="trunghienaz09@gmail.com";
				  String subject = nv.getFullName() + " vừa đăng một bài viết mới"; 
				  String body = "http://localhost:9090/OrganicFood/blog/blogDetails/"+ blog.getMaBV() +".html";
				 
				
				
				  boolean result = send(from, email, subject, body);
				  if(!result) {
				  System.out.print("Gửi mail thât bại"); }
				 
				
				
				model.addAttribute("message", "Thêm mới thành công!");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				t.rollback();
				model.addAttribute("message", "Thêm mới thất bại!!");
			} finally {
				session.close();
			}
			
		//end- save db
		 
		
		 //product.setImage(imgName);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		 model.addAttribute("message", "Lá»—i lÆ°u file!"); 
		 
		 }
		 List<BaiViet> list = getBlogs();
		 PagedListHolder pagedListHolder = new PagedListHolder(list); 
		 int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		 pagedListHolder.setPage(page); 
		 pagedListHolder.setMaxLinkedPages(5);
		 pagedListHolder.setPageSize(2);
		 model.addAttribute("pagedListHolder", pagedListHolder);
		
		 model.addAttribute("blogs", list);
		 return "admin/blog/index"; 
		
	}
	//update
	
	@RequestMapping("update/{id}")
	public String updateBlog(ModelMap model,  @PathVariable("id") int id) {
		idBlog = id;
		BaiViet blog = this.getBlog(id);
		System.out.print(blog.getNoiDung());
		nv = blog.getNV();
		model.addAttribute("blog", blog);
		model.addAttribute("loaiBV", this.getCategoryBlogs2());
		model.addAttribute("btnStatus", "btnUpdate");
		return "admin/blog/addBlog";
	}
	

	@RequestMapping(value="insertBlog", method=RequestMethod.POST, params = "btnUpdate")
	public String updateBlog(ModelMap model, @ModelAttribute("blog") BaiViet lns) {
		Integer temp = this.updateBlog(lns);
		if(temp==1) {
			model.addAttribute("message", "Cập nhật thành công");
		}
		else {
			model.addAttribute("message", "Cập nhật thất bại");
		}
		List<BaiViet> list = this.getBlogs();
		model.addAttribute("blogs", list);
		return "redirect:/admin/blog/index.html";
	}
	
	public Integer updateBlog(BaiViet blog) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			blog.setMaBV(idBlog);
			blog.setNgay(new Date());
			blog.setNV(nv);
			session.update(blog);
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
	public String deleteBlog( ModelMap model,@ModelAttribute("blog") BaiViet blog, @PathVariable("id") int id) {
		blog = this.getBlog(id);
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(blog);
			t.commit();
			model.addAttribute("message", "XÄ‚Â³a thÄ‚Â nh cÄ‚Â´ng");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			t.rollback();
			model.addAttribute("message", "XÄ‚Â³a thĂ¡ÂºÂ¥t bĂ¡ÂºÂ¡i");
		} finally {
			session.close();
		}
		List<BaiViet> list = this.getBlogs();
		model.addAttribute("blogs", list);
		return "redirect:/admin/blog/index.html";
	}
	
	//====================================================================

	
}
