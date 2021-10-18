package organicfood.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	
	@Column(name = "MANV")
	String manv;
	
	@Column(name = "MAKH")
	String makh;
	
	@Column(name = "TRANGTHAI")
	String trangthai;
	
	@Column(name = "MADV")
	String madv;
	
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

	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public String getMakh() {
		return makh;
	}

	public void setMakh(String makh) {
		this.makh = makh;
	}

	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	public String getMadv() {
		return madv;
	}

	public void setMadv(String madv) {
		this.madv = madv;
	}

	public String getMakm() {
		return makm;
	}

	public void setMakm(String makm) {
		this.makm = makm;
	}
	
	
	
}