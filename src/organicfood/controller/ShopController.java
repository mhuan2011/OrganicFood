package organicfood.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import organicfood.bean.Account;
import organicfood.bean.Mailer;
import organicfood.bean.Uploadfile;
import organicfood.entity.BaiViet;
import organicfood.entity.ChiTietDDH;
import organicfood.entity.DatHang;
import organicfood.entity.KhachHang;
import organicfood.entity.KhuyenMai;
import organicfood.entity.LoaiNongSan;
import organicfood.entity.NongSan;

@Controller
@Transactional
@RequestMapping("/shop/")
public class ShopController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	Uploadfile baseuploadfile;
	
	public ArrayList<NongSan> listNS= new ArrayList<NongSan>();
	public ArrayList<Integer> listSLNS = new ArrayList<>();
	@RequestMapping("shop")
	public String showShow(ModelMap model) {
		List<NongSan> list = getProductList();
		List<LoaiNongSan> list1=getCategoryList();
		model.addAttribute("product", list);
		model.addAttribute("category",list1);
		return "frontend/shop/shop";
	}
	public List<NongSan> getProductList(){
		Session session = factory.getCurrentSession();
		String hql = "FROM NongSan";
		Query query = session.createQuery(hql);
		List<NongSan> list = query.list();
		return list;
	}
	public List<LoaiNongSan> getCategoryList(){
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiNongSan";
		Query query = session.createQuery(hql);
		List<LoaiNongSan> list = query.list();
		return list;
	}
	
	@RequestMapping(value="shop-detail/{id}.html")
	public String showDetail( ModelMap model, @PathVariable("id") String id, @ModelAttribute("product") NongSan ns) {
		
		ns = this.getProduct(id);
		String url = "UploadFiles/"+ns.getImage();                                                                                                                                                                                                                      
		model.addAttribute("product", ns);
		model.addAttribute("imageLink", url);
		return "frontend/shop/shop-detail";
	}
	public NongSan getProduct(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NongSan where id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		NongSan u = (NongSan) query.list().get(0);
		return u;
	}
	@RequestMapping(value="shop-category/{id}.html")
	public String showProductFollowCate( ModelMap model, @PathVariable("id") String id, @ModelAttribute("product") NongSan ns) {
		List<NongSan> list = getProductListFollowCategory(id);
		List<LoaiNongSan> list1=getCategoryList();
		model.addAttribute("product", list);
		model.addAttribute("category",list1);
		return "frontend/shop/shop";
	}
	public List<NongSan> getProductListFollowCategory(String id){
		Session session = factory.getCurrentSession();
		String hql = "FROM NongSan NS where NS.category.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<NongSan> list = query.list();
		return list;
	}
	//------------------------------searchProduct
	@RequestMapping(value = "search", params = "btnsearch")
	public String seachPro(HttpServletRequest request, ModelMap model,
			@RequestParam("search") String product_name) {
		List<NongSan> products = this.searchProducts(product_name); 
		model.addAttribute("product", products);
		model.addAttribute("searchText", product_name);
		return "frontend/shop/shop";
	}
	
	public List<NongSan> searchProducts(String product_name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NongSan where name LIKE :product_name";
		Query query = session.createQuery(hql);
		query.setParameter("product_name", "%" + product_name + "%");
		List<NongSan> list = query.list();
		return list;
	}
	//------------------------------searchPrice
		@RequestMapping(value = "searchPrice", params = "btnFilterPrice")
		public String seachPrice(HttpServletRequest request, ModelMap model,
				@RequestParam("price1") String price1,@RequestParam("price2") String price2) {
			System.out.print("gia 1:"+price1+","+price2);
			model.addAttribute("filterPrice1", price1);
			model.addAttribute("filterPrice2", price2);
			price1 = price1.substring(1);
			price2 = price2.substring(1);
			float fprice1=Float.parseFloat(price1);
			float fprice2=Float.parseFloat(price2);
			List<NongSan> products = this.searchPrice(fprice1,fprice2); 
			model.addAttribute("product", products);
			float left1=fprice1*100/fprice2;
			String sleft1=left1+"";
			model.addAttribute("sleft1",sleft1);
			return "frontend/shop/shop";
		}
		
		public List<NongSan> searchPrice(float price1,float price2) {
			Session session = factory.getCurrentSession();
			String hql = "FROM NongSan where price >= :price1 and price <= :price2";
			Query query = session.createQuery(hql);
			query.setParameter("price1", price1);
			query.setParameter("price2", price2);
			List<NongSan> list = query.list();
			return list;
		}
		//them vao gio hang
		@RequestMapping(value="shoppingCart/{id}.html")
		public String addToCart( ModelMap model, @PathVariable("id") String id, @ModelAttribute("product") NongSan ns, HttpSession session) {
			
			ns = this.getProduct(id);
			String url = "UploadFiles/"+ns.getImage();                                                                                                                                                                                                                      
			model.addAttribute("product", ns);
			model.addAttribute("imageLink", url);
			listNS.add(ns);
			listSLNS.add(1);
			model.addAttribute("listNS",listNS);
			model.addAttribute("listSLNS",listSLNS);
			setQuantityCart(session);
			return "frontend/shoppingCart";
		}
		//xoa khoi gio hang
		@RequestMapping(value="deleteShoppingCart/{id}.html")
		public String deleteFromCart( ModelMap model, @PathVariable("id") int id, @ModelAttribute("product") NongSan ns) {
			
			//ns = this.getProduct(id);
			//String url = "UploadFiles/"+ns.getImage();
			//model.addAttribute("imageLink", url);
			listNS.remove(id-2);
			listSLNS.remove(id-2);
			model.addAttribute("listNS",listNS);
			model.addAttribute("listSLNS",listSLNS);
			return "frontend/shoppingCart";
		}
		//tinh lai gia tien
		@RequestMapping(value="updateCart.html")
		public String updateCart(ModelMap model, HttpServletRequest request) {
			listSLNS.removeAll(listSLNS);
			float tongtien=0;
			for(int i=0;i<listNS.size();i++) {
				String soluong1 =request.getParameter("ip"+listNS.get(i).getId());
				System.out.print("ip"+listNS.get(i).getId());
				System.out.print("so luong:"+soluong1);
				int soluong=Integer.parseInt(soluong1);
				tongtien+=listNS.get(i).getPrice()*soluong;
				listSLNS.add(soluong);
			}
			
			
			model.addAttribute("listNS",listNS);
			model.addAttribute("listSLNS",listSLNS);
			System.out.print("tong tien:"+tongtien);
			model.addAttribute("tongtien",tongtien);
			String magg=request.getParameter("discount");
			maKhuyenMai=magg;
			float hesogiam=getHesogiam(magg);
			if(hesogiam==0) {
				model.addAttribute("messDiscount","Khuyến mãi quá hạn hoặc không tồn tại");
			}else {
				model.addAttribute("messDiscount","Bạn được giảm "+hesogiam*100+"%");
			}
			float tiendagiam=tongtien-hesogiam*tongtien;
			model.addAttribute("tiendagiam",tiendagiam);
			return "frontend/shoppingCart";
		}
		public String maKhuyenMai;
		
		public float getHesogiam(String id) {
			Session session = factory.getCurrentSession();
			Date now=new Date();
			Date begin=null,end=null;
			
			String hql = "SELECT discount FROM KhuyenMai WHERE id = :id AND quantity>0 ";
			//String hql = "SELECT discount FROM KhuyenMai AS K WHERE K.id = :id AND :now BETWEEN K.begin AND K.end";
			
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			//query.setDate("now",now);
			
			//query.setParameter("now", now);
			
//			String hql1 = "SELECT begin FROM KhuyenMai WHERE id = :id";
//			Query query1 = session.createQuery(hql1);
//			query1.setParameter("id", id);
//			
//			String hql2 = "SELECT end FROM KhuyenMai WHERE id = :id";
//			Query query2 = session.createQuery(hql2);
//			query2.setParameter("id", id);
//			
//			try {
//				begin = new SimpleDateFormat("dd-MM-yyyy").parse(query1.list().get(0).toString());
//				end = new SimpleDateFormat("dd-MM-yyyy").parse(query2.list().get(0).toString());
//			} catch (ParseException e1) {
//				e1.printStackTrace();
//			}
			
			
			//System.out.print("string list:"+query.list().get(0));
			String tam="0";
			try {
				tam=query.list().get(0).toString();
			} catch (Exception e) {
				// TODO: handle exception
			}
//			if(query.list().get(0).toString()!=null) {
//				tam=query.list().get(0).toString();
//			}
//			String tam="0";tam=query.list().get(0).toString();
			//System.out.print("string tam:"+tam);
			float kq =0f;
//			if(now.after(begin)&&now.before(end)) {
//				return kq;
//			}
			try {
				kq=Float.parseFloat(tam);
			}catch(Exception e) {
			}
			
			
			
			System.out.print("he so giam:"+kq);
			return kq;
		}
		//check out
		
		public KhuyenMai getKhuyenMai(String id) {
			Session session = factory.getCurrentSession();
			String hql = "FROM KhuyenMai where id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			KhuyenMai u =null;
			try {
				u = (KhuyenMai) query.list().get(0);
			}catch(Exception e) {
				
			}
			 
			return u;
		}
		
		@RequestMapping("checkout")
		public String showCheckout(ModelMap model,HttpSession httpsession) {
			Account a=(Account)httpsession.getAttribute("user");
			KhachHang client = getUser(a.getUsername());
			
			
			DatHang dh=new DatHang();
			int maso=getMaSo()+1;
			dh.setMasoddh(maso+"");
			dh.setKhachhang(client);
			dh.setMakm(getKhuyenMai(maKhuyenMai));
			Date date = new Date();
	        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	        String strDate = formatter.format(date);
	        Date date1;
			try {
				date1 = new SimpleDateFormat("dd-MM-yyyy").parse(strDate);
				dh.setNgay(date1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			float tongtien=0;
			for(int i=0;i<listNS.size();i++) {
				tongtien+=listNS.get(i).getPrice()*listSLNS.get(i);
			}
			model.addAttribute("DatHang", dh);
			model.addAttribute("listNS",listNS);
			model.addAttribute("listSLNS",listSLNS);
			model.addAttribute("tongtien",tongtien);
			float hesogiam=getHesogiam(maKhuyenMai);
			float tiendagiam=tongtien-hesogiam*tongtien;
			model.addAttribute("tiendagiam",tiendagiam);
			return "frontend/checkout";
		}
		public int getMaSo() {
			Session session = factory.getCurrentSession();
			String hql = "SELECT masoddh FROM DatHang";
			Query query = session.createQuery(hql);
			List<String> list = query.list();
			int a=0,max=0;
			for(int i=0;i<list.size();i++) {
				a=Integer.parseInt(list.get(i).trim());
				if(a>max) {
					max=a;
				}
			}
			return a;
		}
		@RequestMapping(value="insertOrder", method=RequestMethod.POST)
		public String insert(ModelMap model, @ModelAttribute("DatHang") DatHang dh,HttpSession httpsession) {
			Account a=(Account)httpsession.getAttribute("user");
			KhachHang client = getUser(a.getUsername());
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				dh.setTrangthai("Chưa duyệt");
				session.save(dh);
				t.commit();
				model.addAttribute("message", "Thêm mới thành công!");
				System.out.print("Thêm mới thành công!");
			} catch (Exception e) {
				// TODO: handle exception
				t.rollback();
				model.addAttribute("message", "Thêm mới thất bại!");
				System.out.print("Thêm mới thất bại!"+e.getMessage()+"\n"+e.getStackTrace()+"\n"+e.toString());
			} finally {
				session.close();
			}
			for(int i=0;i<listNS.size();i++) {
				ChiTietDDH ct=new ChiTietDDH();
				ct.setMasoddh(dh);;
				ct.setNongsan(listNS.get(i));
				ct.setSoluong(listSLNS.get(i));
				ct.setDongia(listNS.get(i).getPrice());
				insertCTDDH(ct);
				
			}
			/////////////////////////////
//			listNS.removeAll(listNS);
//			listSLNS.removeAll(listSLNS);
//			return "frontend/shoppingCart";
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	        String strDate = formatter.format(dh.getNgay());
			String nd="Thông tin đơn hàng:"+
					"<br>"+"Mã số đơn đặt hàng: "+dh.getMasoddh().toString()+
					"<br>"+"Tên khách hàng: "+dh.getKhachhang().getPhone()+
					"<br>"+"Ngày đặt hàng: "+strDate+
					"<br>"+"Tên nhân viên: "+dh.getNhanvien().getId()+
					"<br>"+"Tên đơn vị vận chuyển: "+dh.getDvvc().getMadv()+
					"<br>"+"Trạng thái đơn hàng: "+dh.getTrangthai()+
					"<br>"+"Mã khuyến mãi được áp dụng: "+dh.getMakm()+
					"<br>";
			nd+="Chi tiết đơn hàng:"+"<br>";
			nd+="Tên sản phẩm		Số lượng		Đơn giá			Thành tiền";
			
			for(int i=0;i<listNS.size();i++) {
				nd+="<br>";
				nd+="Tên sản phẩm" +listNS.get(i).getName()+"		";
				
				nd+="Số lượng"+listSLNS.get(i)+"		";
				//format lại số tiền
				int tien=Math.round(listNS.get(i).getPrice()*100)/100;
				nd+="Đơn giá"+tien+"		";
				int tongtien=Math.round(listNS.get(i).getPrice()*listSLNS.get(i));
				nd+="Thành tiền"+tongtien+"		";
				
			}
			
			String from = "phongthietbiptit@gmail.com";
		    String email = client.getEmail();
			String subject = "Gửi thông tin chi tiết đơn hàng của bạn đã đặt";
			String body = nd;
			
			boolean result = send(from, email, subject, body);
			
			return "frontend/orderSuccess";
		}
		//send email
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
		private KhachHang getUser(String phoneNumber) {
			KhachHang kh = null;
			Session session = factory.getCurrentSession();
			String hql = "FROM KhachHang WHERE phone = '"+phoneNumber+"'";
			Query query = session.createQuery(hql);
			List<Object[]> list = query.list();
			if(list.size() != 0){
				kh = (KhachHang) query.list().get(0);
			}
			
			return kh;
		}
		public void insertCTDDH(ChiTietDDH ct) {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.save(ct);
				t.commit();
			} catch (Exception e) {
				// TODO: handle exception
				t.rollback();
			} finally {
				session.close();
			}
			
		}
		
		
		@ModelAttribute("dsDonVi")
		public Map<String,String> getTenDV(){
			Session session = factory.getCurrentSession();
			String hql0 = "SELECT madv FROM DVVC";
			Query query0 = session.createQuery(hql0);
			List<String> list0 = query0.list();
			
			String hql = "SELECT tendv FROM DVVC";
			Query query = session.createQuery(hql);
			List<String> list = query.list();
			
			String hql1 = "SELECT giavc FROM DVVC";
			Query query1 = session.createQuery(hql1);
			List<Float> list1 = query1.list();
			
			String hql2 = "SELECT thoigianvc FROM DVVC";
			Query query2 = session.createQuery(hql2);
			List<String> list2 = query2.list();
			
			
			
		    Map<String, String> tt = new HashMap<>();
		    for(int i=0;i<list.size();i++) {
		    	tt.put(list0.get(i).toString(),list.get(i).toString()+" Giá:"+list1.get(i).toString()+" Thời gian:"+list2.get(i).toString());
			}
			return tt;
		}
		
		public void setQuantityCart(HttpSession session) {
			int number = 0;
			for(int i=0; i<listSLNS.size(); i++) {
				number += listSLNS.get(i);
			}
			
			session.setAttribute("cartNumber", number);
		}
}
