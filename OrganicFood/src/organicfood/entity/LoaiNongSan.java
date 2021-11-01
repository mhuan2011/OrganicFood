package organicfood.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="LOAINS")
public class LoaiNongSan {
	@Id
	@Column(name = "MALOAI")
	private String id;
	
	@Column(name = "TENLOAI")
	private String name;
	

	@Column(name="HINHANH")
	private String image;
	
	@Column(name="YEUTHICH")
	private int featured;
	

	
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private Collection<NongSan> product;
	
	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getFeatured() {
		return featured;
	}

	public void setFeatured(int featured) {
		this.featured = featured;
	}

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

	


	public Collection<NongSan> getProduct() {
		return product;
	}

	public void setProduct(Collection<NongSan> product) {
		this.product = product;
	}

	
	
	
	
}
