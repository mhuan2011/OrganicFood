package organicfood.entity;


import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="KHACHHANG")
public class KhachHang {
	
	@Id
	@Column(name = "SDT")
	private String phone;
	
	@Column(name = "HOTEN")
	private String name;
	
	@Column(name = "DIACHI")
	private String address;
	
	@Column(name = "MATKHAU")
	private String password;

	@Column(name = "EMAIL")
	private String email;
		
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
