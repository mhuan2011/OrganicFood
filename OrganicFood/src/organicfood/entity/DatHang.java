package organicfood.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date ngay;
	
	@ManyToOne
	@JoinColumn(name="MANV")
	NhanVien nhanvien;
	
	@ManyToOne
	@JoinColumn(name="MAKH")
	KhachHang khachhang;
	
	@Column(name = "TRANGTHAI")
	String trangthai;
	
	@ManyToOne
	@JoinColumn(name="MADV")
	DVVC dvvc;
	
	@Column(name = "MAKM")
	String makm;

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

	public String getMakm() {
		return makm;
	}

	public void setMakm(String makm) {
		this.makm = makm;
	}
	
	
	
}