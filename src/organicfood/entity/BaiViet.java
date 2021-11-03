package organicfood.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import organicfood.bean.StringUtil;


@Entity
@Table(name="BAIVIET")
public class BaiViet {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MABV")
	private int maBV;
	
	@Column(name="TIEUDE")
	private String tieuDe;
	
	@Column(name="TRICHDAN")
	private String trichDan;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name="NGAY")
	private Date ngay;
	
	@Column(name="NOIDUNG")
	private String noiDung;
	
	@Column(name="HINHANH")
	private String hinhAnh;
	
	@ManyToOne
	@JoinColumn(name="MANV")
	private NhanVien NV;
	
	@ManyToOne
	@JoinColumn(name="MALOAI")
	private LoaiBV loaiBV;

	
	 public String getTieuDeXuongDong() {
		 if(tieuDe.length()<=20) return tieuDe;
		 return StringUtil.newLine(tieuDe, 20);
	 }
	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date date) {
		ngay = date;
	}

	public String getTrichDan2() {
		return StringUtil.baCham(trichDan, 20, "...");
	}

	public void setTrichDan(String moTa) {
		this.trichDan = moTa;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public int getMaBV() {
		return maBV;
	}

	public void setMaBV(int maBV) {
		this.maBV = maBV;
	}

	public String getTieuDe() {
		return tieuDe;
	}

	public String getTrichDan() {
		return trichDan;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public String getTieuDe2() {
		return StringUtil.baCham(tieuDe, 20, "...");
	}

	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}

	public String getNoiDung2() {
		
		return StringUtil.baCham(noiDung, 20, "...");
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public NhanVien getNV() {
		return NV;
	}

	public void setNV(NhanVien nV) {
		NV = nV;
	}

	public LoaiBV getLoaiBV() {
		return loaiBV;
	}

	public void setLoaiBV(LoaiBV loaiBV) {
		this.loaiBV = loaiBV;
	}
	
	
}
