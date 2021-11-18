package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import organicfood.entity.BaiViet;
import organicfood.entity.LoaiBV;

public class BlogDao {

	
	public static List<BaiViet> getBlogs(SessionFactory factory){
		List<BaiViet> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		String hql = "FROM BaiViet";
		Query query = session.createQuery(hql);
		list = query.list();
		return list;
	}
	
public static BaiViet getBlog(SessionFactory factory, int id) {
		
		Session session = factory.getCurrentSession();
		String hql = "FROM BaiViet where MABV = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		BaiViet u = (BaiViet) query.list().get(0);
		return u;
	}
public static List<BaiViet> getRecentBlogs(SessionFactory factory, int quantity){
	Session session = factory.getCurrentSession();
	String hql = "FROM BaiViet order by ngay desc";
	Query query = session.createQuery(hql);
	query.setMaxResults(quantity);
	List<BaiViet> temp = query.list();
	System.out.println("length of recent blog" + temp.size());
	return temp;
}

public static List<BaiViet> getBlogByCategory(SessionFactory factory, String loai){
	Session session = factory.getCurrentSession();
	String hql = "FROM BaiViet where loaiBV.maLoai = :loai";
	Query query = session.createQuery(hql);
	query.setParameter("loai", loai);
	List<BaiViet> list = query.list();
	return list;
}

public static List<BaiViet> getBlogByCategory(SessionFactory factory, String loai, int maxBlog){
	Session session = factory.getCurrentSession();
	String hql = "FROM BaiViet where loaiBV.maLoai = :loai";
	Query query = session.createQuery(hql);
	query.setParameter("loai", loai);
	query.setMaxResults(maxBlog);
	List<BaiViet> list = query.list();
	return list;
}

public static List<BaiViet> getBlogBySearch(SessionFactory factory, String s){
	Session session = factory.getCurrentSession();
		s = "%" + s + "%"; 
        Query query = session.createQuery("SELECT a FROM BaiViet a "
                + "WHERE a.tieuDe LIKE :s or a.noiDung LIKE :s" );
        query.setParameter("s", s);
        List <BaiViet> list = query.list();
	return list;
}

public static List<BaiViet> search(String search, SessionFactory factory) {

	Session session = factory.getCurrentSession();
	String hql = "FROM BaiViet where tieuDe LIKE :search or noiDung LIKE :search ";
	Query query = session.createQuery(hql);
	query.setParameter("search", "%" + search + "%");
	List<BaiViet> list = query.list();
	return list;
}

}
