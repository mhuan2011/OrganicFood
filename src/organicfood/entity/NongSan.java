package organicfood.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
@Entity
@Table(name="NONGSAN")
public class NongSan {
	
	
	@Id
	@Column(name = "MANS")
	@NotNull(message="Mã nông sản không được để trống")
	private String id;
	
	//@NotBlank(message = "Tên nông sản không được để trống")
	@Column(name = "TENNS")
	private String name;
	
	@Column(name = "DVT")
	private String Unit;
	
	@Column(name = "SOLUONGTON")
	private int number;
	
	@Column(name = "GIA")
	private int price;
	
	@Column(name = "HINHANH")
	private String image;
	
	@Column(name = "MOTA")
	private String description;
	

	@ManyToOne
	@JoinColumn(name="MALOAI")
	private LoaiNongSan category;
	
	@Column(name = "KHUYENMAI")
	private float discount;

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

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LoaiNongSan getCategory() {
		return category;
	}

	public void setCategory(LoaiNongSan category) {
		this.category = category;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}
	
	
}
