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
	
	
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private Collection<NongSan> product;
	
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