package organicfood.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="DATHANG")
public class DatHang {
	@Id
	@Column(name = "MasoDDH")
	String masoddh;
	
	@Column(name = "NGAY")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ngay;
	
	@ManyToOne
	@JoinColumn(name="MANV")
	NhanVien nhanvien;
	
	@ManyToOne
	@JoinColumn(name="MAKH")
	KhachHang khachhang;
	
	@Column(name = "TRANGTHAI")
	String trangthai;
	
	@OneToMany(mappedBy="masoddh", fetch=FetchType.EAGER)
	private Collection<ChiTietDDH> listOrder; 
	
	@ManyToOne
	@JoinColumn(name="MADV")
	DVVC dvvc;
	
	@ManyToOne
	@JoinColumn(name="MAKM")
	KhuyenMai makm;

	public String getMasoddh() {
		return masoddh;
	}

	public void setMasoddh(String masoddh) {
		this.masoddh = masoddh;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	

	public NhanVien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(NhanVien nhanvien) {
		this.nhanvien = nhanvien;
	}

	

	public KhachHang getKhachhang() {
		return khachhang;
	}

	public void setKhachhang(KhachHang khachhang) {
		this.khachhang = khachhang;
	}

	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}


	public DVVC getDvvc() {
		return dvvc;
	}

	public void setDvvc(DVVC dvvc) {
		this.dvvc = dvvc;
	}

	public KhuyenMai getMakm() {
		return makm;
	}

	public void setMakm(KhuyenMai makm) {
		this.makm = makm;
	}
	

	public void showListOrder() {
		System.out.println("List order: " + masoddh);
		for(ChiTietDDH i : listOrder) {
			System.out.println("Chi tiết đơn đặt hàng:" + i.getMasoddh().getMasoddh());
			System.out.println("Nông sản:" + i.getNongsan().getName());
			System.out.println("Đơn giá:" + i.getDongia());
			System.out.println("Số lượng:" + i.getSoluong());
		}
	}
	
	public Collection<ChiTietDDH> getListOrder() {
		return listOrder;
	}

	public void setListOrder(Collection<ChiTietDDH> listOrder) {
		this.listOrder = listOrder;
	}
	
	public long sumListOrder() {
		showListOrder();
		long tong = 0;
		for(ChiTietDDH s :listOrder) {
			tong += s.getDongia()*s.getSoluong();
		}
		return tong;
	}

	
}