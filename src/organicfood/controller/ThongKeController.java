package organicfood.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.ThongKeDao;


@Transactional
@Controller
@RequestMapping("/admin/")
public class ThongKeController {
	
	@Autowired
	SessionFactory factory;
	
	 @InitBinder
	 public final void initBinderUsuariosFormValidator(final WebDataBinder binder, final Locale locale) {
	     final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
	 }
	
	private void initLoaiThongKe() {
		
	}
	
	@ModelAttribute("loaiThongKe")
	public List<String> loaiThongKe(){
		List<String> list = new ArrayList<>();
		list.add("Thống kê theo ngày");
		list.add("Thống kê theo tháng");
		list.add("Thống kê theo năm");
		return list;
	}
	
	@RequestMapping("home")
	public String thongKe(ModelMap model, HttpServletRequest request) {
		
		String thongKe = "Thống kê theo ngày";
		Date date = new Date();
		
		  model.addAttribute("fromDate", new SimpleDateFormat("yyyy-MM-dd").format(date));
		  model.addAttribute("toDate", new SimpleDateFormat("yyyy-MM-dd").format(date));
		 
		model.addAttribute("doanhSo", ThongKeDao.thongKeDoanhSo(factory,  date, date));
		model.addAttribute("khachHang", ThongKeDao.thongKeKhachHang(factory,  date, date));
		model.addAttribute("donDatHang", ThongKeDao.thongKeDonDatHang(factory, date, date));
		model.addAttribute("trangThaiDonHang", ThongKeDao.thongKeTrangThaiDonHang(factory, date, date));
		model.addAttribute("sanPham", ThongKeDao.thongKeTungSanPham(factory, date, date));
		return "admin/home";
	}
	@RequestMapping("home/thongKe")
	public String btnThongKe(ModelMap model, HttpServletRequest request) {
		Date fromDate=null;
		Date toDate=null;
		try {
			fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fromDate"));
			toDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("toDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("fromDate", new SimpleDateFormat("yyyy-MM-dd").format(fromDate));
		model.addAttribute("toDate", new SimpleDateFormat("yyyy-MM-dd").format(toDate));
		
		
		model.addAttribute("doanhSo", ThongKeDao.thongKeDoanhSo(factory, fromDate, toDate));
		model.addAttribute("khachHang", ThongKeDao.thongKeKhachHang(factory, fromDate, toDate));
		model.addAttribute("donDatHang", ThongKeDao.thongKeDonDatHang(factory, fromDate, toDate));
		model.addAttribute("trangThaiDonHang", ThongKeDao.thongKeTrangThaiDonHang(factory, fromDate, toDate));
		model.addAttribute("sanPham", ThongKeDao.thongKeTungSanPham(factory, fromDate, toDate));
		return "admin/home";
	
	
	}
}
