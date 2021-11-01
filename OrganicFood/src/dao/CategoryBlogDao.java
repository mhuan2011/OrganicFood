package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import organicfood.entity.LoaiBV;

public class CategoryBlogDao {

	public static List<LoaiBV> getCategoryBlogs(SessionFactory factory){
		List<LoaiBV> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiBV";
		Query query = session.createQuery(hql);
		list = query.list();
		return list;
	}
	
	public static LoaiBV getCategoryBlog(SessionFactory factory, String id) {
		
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiBV where maLoai = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		LoaiBV u = (LoaiBV) query.list().get(0);
		return u;
	}
	
}
