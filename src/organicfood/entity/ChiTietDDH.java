package organicfood.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Entity
@Table(name="CTDDH") @IdClass(MyKey.class)
public class ChiTietDDH {
	
	
	@Id
	@ManyToOne
	@JoinColumn(name="MasoDDH")
	DatHang masoddh;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "MANS")
	NongSan nongsan;
	
	@Column(name = "SOLUONG")
	int soluong;
	
	@Column(name = "DONGIA")
	float dongia;


	

	public DatHang getMasoddh() {
		return masoddh;
	}

	public void setMasoddh(DatHang masoddh) {
		this.masoddh = masoddh;
	}

	public NongSan getNongsan() {
		return nongsan;
	}

	public void setNongsan(NongSan nongsan) {
		this.nongsan = nongsan;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public float getDongia() {
		return dongia;
	}

	public void setDongia(float dongia) {
		this.dongia = dongia;
	}

}
