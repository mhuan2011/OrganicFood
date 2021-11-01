package organicfood.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="LOAIBV")
public class LoaiBV {
	@Id
	@Column(name="MALOAI")
	private String maLoai;
	
	@Column(name="TENLOAI")
	private String tenLoai;
	
	@Column(name="TAG")
	private String tag;
	
	@OneToMany(mappedBy="loaiBV", fetch = FetchType.EAGER)
	Collection<BaiViet> baiViets;

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Collection<BaiViet> getBaiViets() {
		return baiViets;
	}

	public void setBaiViets(Collection<BaiViet> baiViets) {
		this.baiViets = baiViets;
	}
	
	
}
