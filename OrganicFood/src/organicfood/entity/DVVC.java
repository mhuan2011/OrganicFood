package organicfood.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DVVC")
public class DVVC {
	@Id
	@Column(name = "MADV")
	String madv;
	
	@Column(name = "TENDV")
	String tendv;
	
	@Column(name = "GIAVC")
	Float giavc;
	
	@Column(name = "THOIGIANVC")
	String thoigianvc;

	public String getMadv() {
		return madv;
	}

	public void setMadv(String madv) {
		this.madv = madv;
	}

	public String getTendv() {
		return tendv;
	}

	public void setTendv(String tendv) {
		this.tendv = tendv;
	}

	public Float getGiavc() {
		return giavc;
	}

	public void setGiavc(Float giavc) {
		this.giavc = giavc;
	}

	public String getThoigianvc() {
		return thoigianvc;
	}

	public void setThoigianvc(String thoigianvc) {
		this.thoigianvc = thoigianvc;
	}
	
	
	
}