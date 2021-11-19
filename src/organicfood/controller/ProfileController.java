package organicfood.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import organicfood.bean.Account;
import organicfood.entity.DatHang;
import organicfood.entity.KhachHang;

@Controller
@Transactional
public class ProfileController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("/profile")
	public String showProfile(ModelMap model,HttpSession httpsession) {
		Account a=(Account)httpsession.getAttribute("user");
		KhachHang kh = getKhachHang(a.getUsername());
		model.addAttribute("Profile", kh);
		return "frontend/profile/profile";
	}

	public KhachHang getKhachHang(String phone) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhachHang WHERE phone =:phone";
		Query query = session.createQuery(hql);
		query.setParameter("phone", phone);
		KhachHang u = (KhachHang) query.list().get(0);
		return u;
	}
	@RequestMapping(value="profile/edit-profile", method=RequestMethod.POST)
	public String updateDatHang(ModelMap model, @ModelAttribute("Profile") KhachHang kh,HttpSession httpsession) {
		Integer temp = this.updateKhachHang(kh);
		
		if(temp==1) {
			model.addAttribute("message", "Cập nhật thành công");
			System.out.print("Cập nhật thành công");
		}
		else {
			model.addAttribute("message", "Cập nhật thất bại");
			System.out.print("Cập nhật thất bại");
		}
		Account a=(Account)httpsession.getAttribute("user");
		KhachHang khachhang = getKhachHang(a.getUsername());
		model.addAttribute("Profile", khachhang);

		return "redirect:/profile.html";
	}
	public Integer updateKhachHang(KhachHang khachhang) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(khachhang);
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
}
