package organicfood.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import organicfood.bean.Account;
import organicfood.entity.ChiTietDDH;
import organicfood.entity.DatHang;

@Controller
@Transactional
public class OrderedController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("/ordered")
	public String showOrdered(ModelMap model,HttpSession httpsession) {
		Account a=(Account)httpsession.getAttribute("user");
		List<DatHang> list = getDatHang(a.getUsername());
		model.addAttribute("DatHang", list);
		return "frontend/ordered/ordered";
	}
	public List<DatHang> getDatHang(String id){
		Session session = factory.getCurrentSession();
		String hql = "FROM DatHang where khachhang.phone =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<DatHang> list = query.list();
		return list;
	}
	@RequestMapping(value="/order/details/{masoddh}.html")
	public String updateDatHang( ModelMap model, @PathVariable("masoddh") String masoddh, @ModelAttribute("DatHang") DatHang dathang) {
		
		dathang = this.get1Dathang(masoddh.trim());
		model.addAttribute("DatHang", dathang);
		
		List<ChiTietDDH> ctddh=getCTDDH(masoddh);
		model.addAttribute("CTDDH",ctddh);
		return "frontend/ordered/ChiTietOrdered";
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
}
