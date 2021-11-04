package organicfood.controller;


import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.SysexMessage;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import organicfood.bean.Uploadfile;
import organicfood.entity.KhuyenMai;
import organicfood.entity.NongSan;



@Controller
@Transactional
@RequestMapping("/admin/discount")
public class DiscountController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	Uploadfile baseuploadfile;
	
	 @InitBinder
	 public final void initBinderUsuariosFormValidator(final WebDataBinder binder, final Locale locale) {
	     final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
	 }
	
	
	@RequestMapping("discount-list")
	public String showDiscountList(ModelMap model) {
		List<KhuyenMai> discounts = getDiscounts();
		model.addAttribute("discounts", discounts);
		return "admin/discount/index";
	}
	
	public List<KhuyenMai> getDiscounts() {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhuyenMai";
		Query query = session.createQuery(hql);
		List<KhuyenMai> list = query.list();
		
		return list;
	}
	//insert
	@RequestMapping("add-discount")
	public String addDiscount(ModelMap model) {
		model.addAttribute("discount", new KhuyenMai());
		model.addAttribute("btnStatus", "btnAdd");
		return "admin/discount/addDiscount";
	}
	
	@RequestMapping(value="/add-discount", method=RequestMethod.POST, params = "btnAdd" )
	public String insertDiscount(ModelMap model, @ModelAttribute("discount") KhuyenMai discount, BindingResult errors, @RequestParam("hinhanh") MultipartFile image) {
		
		//validate
		
		boolean check = true;
		if(discount.getId().equals("")) {
			errors.rejectValue("id", "discount", "*Vui lòng nhập mã khuyến mãi !");
			check=false;
		} else {
			boolean flag = checkIdDiscount(discount.getId());
			if(flag) {
				errors.rejectValue("id", "discount", "*Mã khuyến mãi bị trùng!");
			}
		}
		if(discount.getName().equals("")) {
			errors.rejectValue("name", "discount", "*Vui lòng nhập tên !");
			check=false;
		}
		if(discount.getBegin() == null) {
			errors.rejectValue("begin", "discount", "*Vui lòng chọn ngày bắt đầu !");
			check=false;
		}
		if(discount.getEnd()== null) {
			errors.rejectValue("end", "discount", "*Vui lòng chọn ngày kết thúc !");
			check=false;
		}
		if(discount.getDiscount() <= 0) {
			errors.rejectValue("discount", "discount", "*Vui lòng nhập số lượng giảm!");
			check=false;
		}
		if(discount.getQuantity() <= 0) {
			errors.rejectValue("quantity", "discount", "*Vui lòng nhập số lượng mã !");
			check=false;
		}
		
		if(!check) {
			model.addAttribute("btnStatus", "btnAdd");
			return "admin/discount/addDiscount";
		}
		
		
		if(!image.isEmpty()) {
			String fileName = createFileName(image);
			boolean result = upLoadImage(image,fileName );
			if(!result)   model.addAttribute("messageImg","Upload hình ảnh không thành công");
			else discount.setImage(fileName);
		}
		boolean re = this.InsertDiscount(discount);
		if(re) {
			model.addAttribute("message", "Thêm khuyến mãi thành công"); 
		}else model.addAttribute("message", "Thêm khuyến mãi thất bại");
		List<KhuyenMai> discounts = getDiscounts();
		model.addAttribute("discounts", discounts);
		return "redirect:discount-list.html";
	}
	
	public boolean InsertDiscount(KhuyenMai discount) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(discount);
			t.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
	public boolean upLoadImage(MultipartFile image, String imgName) {
		try {
			String photoPath =
			baseuploadfile.getBasePath()+File.separator+ "Discount" +File.separator +imgName; 
			image.transferTo(new File(photoPath));
			Thread.sleep(2000);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String createFileName(MultipartFile image) {
		String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
		String imgName = date +image.getOriginalFilename(); 
		return imgName;
	}
	
	//update
	@RequestMapping(value="update/{id}.html", method = RequestMethod.GET)
	public String updateDiscount( ModelMap model, @PathVariable("id") String id, @ModelAttribute("discount") KhuyenMai discount) {
		
		discount = this.getDiscount(id);
		String url = null;
		if(discount.getImage() != null) {
			url = "UploadFiles/Discount/"+discount.getImage();
		}
		 
        
		model.addAttribute("discount", discount);
		model.addAttribute("btnStatus", "btnUpdate");
		
		model.addAttribute("imageLink", url);
		
		return "admin/discount/addDiscount";
		
	}
	
	public KhuyenMai getDiscount(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhuyenMai where id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		KhuyenMai d = (KhuyenMai)query.list().get(0);
		return d;
	}
	
	@RequestMapping(value="/add-discount", method=RequestMethod.POST, params = "btnUpdate")
	public String saveEditDiscount( ModelMap model, @ModelAttribute("discount") KhuyenMai discount,
			@RequestParam("hinhanh") MultipartFile image) {
		
		System.out.print("check");
		if(!image.isEmpty()) {
			String fileName = createFileName(image);
			boolean result = upLoadImage(image,fileName );
			if(!result)   model.addAttribute("messageImg","Upload hình ảnh không thành công");
			else discount.setImage(fileName);
		}
		int re = updateDiscount(discount);
		if(re==1) {
			model.addAttribute("message","Cập nhật thành công");
			
			
		}else {
			model.addAttribute("message","Cập nhật thất bại");
		}
		List<KhuyenMai> discounts = getDiscounts();
		model.addAttribute("discounts", discounts);
		return "redirect:discount-list.html";
	}
	
	public Integer updateDiscount(KhuyenMai discount) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(discount);
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
	
	//delete
	
	@RequestMapping(value="delete/{id}.html", method = RequestMethod.GET)
	public String deleteDiscount( ModelMap model, @PathVariable("id") String id, @ModelAttribute("discount") KhuyenMai discount) {
		
		discount = this.getDiscount(id);

		int result = deleteDiscount(discount);
		if(result == 1) {
			model.addAttribute("message", "Xóa khuyến mãi thành công");
		} else model.addAttribute("message", "Xóa khuyến mãi thất bại");
		List<KhuyenMai> discounts = getDiscounts();
		model.addAttribute("discounts", discounts);
		return "redirect:discount-list.html";
		
	}
	
	public Integer deleteDiscount(KhuyenMai discount) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(discount);
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
	//search
	@RequestMapping(value = "discount-list", params = "btnsearch")
	public String seachPro(HttpServletRequest request, ModelMap model,
			@RequestParam("search") String discoutid
			
			) {
		

		List<KhuyenMai> discounts = this.serchDiscount(discoutid);
		
		model.addAttribute("discounts", discounts);
		model.addAttribute("searchText", discoutid);
		
		return "admin/discount/index";
	}
	
	
	public List<KhuyenMai> serchDiscount(String disscount_id) {

		Session session = factory.getCurrentSession();
		String hql = "FROM KhuyenMai where id LIKE :disscount_id";
		Query query = session.createQuery(hql);
		query.setParameter("disscount_id", "%" + disscount_id + "%");
		List<KhuyenMai> list = query.list();

		return list;
	}
	//show - hidden
	@RequestMapping(value="show/{id}.html")
	public String showHidden(ModelMap model, @PathVariable("id") String id,@ModelAttribute("discount") KhuyenMai discount ){
		discount = this.getDiscount(id);
		
		boolean s = discount.getShow();
		discount.setShow(!s);
	
		updateDiscount(discount);
		List<KhuyenMai> discounts = getDiscounts();
		model.addAttribute("discounts", discounts);
		return "admin/discount/index";
	}
	
	//checkId
	public boolean checkIdDiscount(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhuyenMai where id = :disscount_id";
		Query query = session.createQuery(hql);
		query.setParameter("disscount_id", id);
		List<KhuyenMai> list = query.list();
		if(list.size()> 0) return true;
		return false;
	}
}
