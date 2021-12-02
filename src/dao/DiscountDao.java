package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import organicfood.entity.KhuyenMai;

public class DiscountDao {
	
	public static List<KhuyenMai> getDiscountsShow(SessionFactory factory) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhuyenMai where show = true";
		Query query = session.createQuery(hql);
		List<KhuyenMai> list = query.list();
		
		return list;
	}
	public static KhuyenMai getDiscount(SessionFactory factory, String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KhuyenMai where id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		KhuyenMai d = (KhuyenMai)query.list().get(0);
		return d;
	}
}
