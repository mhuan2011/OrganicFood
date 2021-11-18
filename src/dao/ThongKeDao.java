package dao;

import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transaction;

import organicfood.entity.ChiTietDDH;
import organicfood.entity.DatHang;
import organicfood.entity.NongSan;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ThongKeDao {

	public static long sumListOrder(SessionFactory factory, List<DatHang> list) {
		long sum = 0;
		Session session = factory.getCurrentSession();
		String hql = "";
		Query query = null;
		List<ChiTietDDH> listOrder= null;
		for(DatHang i : list) {
			hql="FROM ChiTietDDH where masoddh =:masoddh";
			query = session.createQuery(hql);
			query.setString("masoddh", i.getMasoddh());
			listOrder = query.list();
			for(ChiTietDDH s :listOrder) {
				sum += s.getDongia()*s.getSoluong();
			}
		}
		
		return sum;
	}
	
	public static long thongKeDoanhSo(SessionFactory factory, Date fromDate, Date toDate) {
		Session session = factory.getCurrentSession();
		List<DatHang> list= new ArrayList<>();
		Query query = null;
		String hql = "FROM DatHang where ngay BETWEEN :fromDate AND :toDate AND trangthai=:trangThai";
		query = session.createQuery(hql);
		 query.setDate("fromDate", fromDate); 
		 query.setDate("toDate", toDate); 
		 query.setString("trangThai", "Đã giao");
		list = query.list();
		
		return sumListOrder(factory, list);
	}
	
	public static long thongKeDonDatHang(SessionFactory factory,   Date fromDate, Date toDate) {
		String hql="";
		Session session = factory.getCurrentSession();
		Query query = null;
			hql = "select count(*) FROM DatHang where ngay BETWEEN :fromDate AND :toDate AND trangthai=:trangThai";
		
		query = session.createQuery(hql);
		query.setDate("fromDate", fromDate); 
		 query.setDate("toDate", toDate); 
		 query.setString("trangThai", "Đã giao");
		Long count = (Long)query.uniqueResult();
		return count;
	}
	
	public static long thongKeKhachHang(SessionFactory factory, Date fromDate, Date toDate) {
		String hql="";
		Session session = factory.getCurrentSession();
		Query query = null;
		hql = "select count(distinct khachhang.phone) FROM DatHang where ngay BETWEEN :fromDate AND :toDate AND trangthai=:trangThai";
		query = session.createQuery(hql);
		query.setDate("fromDate", fromDate); 
		 query.setDate("toDate", toDate); 
		 query.setString("trangThai", "Đã giao");
		Long count = (Long)query.uniqueResult();
		return count;
	}
	
	
	public static HashMap<String, Integer> thongKeTrangThaiDonHang2(SessionFactory factory, Date fromDate, Date toDate){
		String hql = "FROM DatHang where ngay BETWEEN :fromDate AND :toDate AND trangthai='Ä�Ă£ giao'";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setDate("fromDate", fromDate); 
		 query.setDate("toDate", toDate); 
		List<DatHang> list = query.list();
		HashMap<String, Integer> map = new HashMap<>();
		for(DatHang s: list) {
			if(map.get(s.getTrangthai())==null) {// chua co trang thai trong danh sach thi them vao ds
				map.put(s.getTrangthai(), 1);
			}else {
				map.put(s.getTrangthai(), map.get(s.getTrangthai())+1);
			}
		}
		return map;
	}
	
	public static List<Object[]> thongKeTrangThaiDonHang(SessionFactory factory, Date fromDate, Date toDate){
		String hql = "select trangthai, count(trangthai) FROM DatHang where ngay BETWEEN :fromDate AND :toDate group by trangthai";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setDate("fromDate", fromDate); 
		 query.setDate("toDate", toDate); 
		return query.list();
	}
	
	public static  HashMap<NongSan, Long> thongKeTungSanPham(SessionFactory factory, Date fromDate, Date toDate){
		String hql = "FROM DatHang where ngay BETWEEN :fromDate AND :toDate AND trangthai=:trangthai";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setDate("fromDate", fromDate); 
		 query.setDate("toDate", toDate); 
		 query.setString("trangthai", "Đã giao");
		 List<DatHang> datHang = query.list();
		 System.out.println("List dathang:" + datHang.size());
		 HashMap<String, Long> map = new HashMap<>();
		 for(DatHang i : datHang) {
			 
			 String hql2 = "FROM ChiTietDDH where masoddh.id=:masoddh";
			 query = session.createQuery(hql2);
			 query.setParameter("masoddh", i.getMasoddh());
			 List<ChiTietDDH> CTDDH = query.list();// tra ve ds ctddh
			 System.out.println("Chi tiet ddh size:" + CTDDH.size());
			 for(ChiTietDDH ctDDH: CTDDH) {
				 long doanhThu = (long) (ctDDH.getSoluong()*ctDDH.getDongia());
				 String idNongSan = ctDDH.getNongsan().getId();
				 if(map.get(idNongSan)==null) {// chua co trang thai trong danh sach thi them vao ds
						map.put(idNongSan, doanhThu);
					}else {
						
						map.put(idNongSan, map.get(idNongSan)+doanhThu);
					}
			 }
		 }
		 HashMap<NongSan, Long> mapNS = new HashMap<>();
		 List<Object[]> list = new ArrayList<>();
		 int max=10;
		 int i=0;
		 for(String id: map.keySet()) {
			 String hql3 ="from NongSan where id=:id";
			 query = session.createQuery(hql3);
			 query.setString("id", id);
			 NongSan ns = (NongSan) query.list().get(0);
			 mapNS.put((NongSan) query.list().get(0), map.get(id));
			 i++;
			 if(i==max) break;
		 }
		return mapNS;
	}
	
	
	
	public static List<Object[]> thongKeTungSanPham2(SessionFactory factory, Date fromDate, Date toDate){
		/*String hql = "select ns.id, ns.name, ns.image, sum(ctddh.doanhthu) "
				+"from (select masoddh from DatHang where ngay between :fromDate and :toDate and trangthai=:trangThai) as dh,"
				+"(select masoddh, nongsan, sum(dongia*soluong) as doanhthu from ChiTietDDH) as ctddh, "
				+"NongSan as ns"
				+"where dh.masoddh = ctddh.masoddh "
				+"and ctddh.nongsan.id = ns.id"
				+"group by ns.id, ns.name, ns.image";*/
		
		Session session = factory.openSession();
		Query query = session.createSQLQuery("exec test");
		return query.list();
	}
	

}
