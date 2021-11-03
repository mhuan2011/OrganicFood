package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import organicfood.entity.BaiViet;
import organicfood.entity.LoaiBV;
import organicfood.entity.LoaiNongSan;
import organicfood.entity.NongSan;

public class NongSanDao {
	public static List<NongSan> getProducts(SessionFactory factory){
		List<NongSan> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		String hql = "FROM NongSan";
		Query query = session.createQuery(hql);
		list = query.list();
		return list;
	}
	
public static NongSan getProduct(SessionFactory factory, String id) {
		
		Session session = factory.getCurrentSession();
		String hql = "FROM NongSan where id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		NongSan u = (NongSan) query.list().get(0);
		return u;
	}


public static List<NongSan> getProductByCategory(SessionFactory factory, String loai){
	Session session = factory.getCurrentSession();
	String hql = "FROM NongSan where category.id = :loai";
	Query query = session.createQuery(hql);
	query.setParameter("loai", loai);
	List<NongSan> list = query.list();
	return list;
}

public static List<NongSan> getProductBySearch(SessionFactory factory, String s){
	Session session = factory.getCurrentSession();
		s = "%" + s + "%"; 
        Query query = session.createQuery("SELECT a FROM NongSan a "
                + "WHERE a.name LIKE :s" );
        query.setParameter("s", s);
        List <NongSan> list = query.list();
	return list;
}

public static List<LoaiNongSan> getCategoryProducts(SessionFactory factory){
	List<LoaiNongSan> list = new ArrayList<>();
	Session session = factory.getCurrentSession();
	String hql = "FROM LoaiNongSan";
	Query query = session.createQuery(hql);
	list = query.list();
	return list;
}

public static LoaiNongSan getCategoryProduct(SessionFactory factory, String id) {
	
	Session session = factory.getCurrentSession();
	String hql = "FROM LoaiNongSan where id = :id";
	Query query = session.createQuery(hql);
	query.setParameter("id", id);
	LoaiNongSan u = (LoaiNongSan) query.list().get(0);
	return u;
}

public static List<LoaiNongSan> getFeaturedCategoryProduct(SessionFactory factory, int max) {
	
	Session session = factory.getCurrentSession();
	String hql = "FROM LoaiNongSan order by featured ASC";
	Query query = session.createQuery(hql);
	List<LoaiNongSan> u = query.list();
	List<LoaiNongSan> list = new ArrayList<>();
	for(int i=0; i< max; i++) {
		list.add(u.get(i));
	}
	return list;
}
}
