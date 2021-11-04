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
@Table(name="KHUYENMAI")
public class KhuyenMai {
	@Id
	@Column(name = "MAKM")
	private String id;
	
	@Column(name = "TENKM")
	private String name; 
	
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")	
	@Column(name = "NGAYBATDAU")
	private Date begin;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")	
	@Column(name = "NGAYKETTHUC")
	private Date end; 
	
	
	@Column(name = "HESOGIAM")
	private float discount;
	
	@Column(name = "SOLUONG")
	private int quantity; 
	
	@Column(name = "MOTAKM")
	private String description;

	@Column(name = "HINHANH")
	private String image;
	
	@Column(name = "HIENTHI")
	private boolean show;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean getShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	} 
	
	
	
}
