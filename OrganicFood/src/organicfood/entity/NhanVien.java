package organicfood.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="NHANVIEN")
public class NhanVien {
	@Id
	@Column(name = "MANV")
	private String id;
	
	@Column(name = "HO")
	private String firstName;
	
	@Column(name = "TEN")
	private String lastName;
	
	@Column(name = "DIACHI")
	private String address;
	
	@Column(name = "NGAYSINH")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date date;
	
	/*
	 * @OneToMany(mappedBy="NV", fetch=FetchType.EAGER) Collection<BaiViet>
	 * baiViets;
	 */
	@Column(name = "LUONG")
	private float salary;
	
	@Column(name = "MATKHAU")
	private String password;

	public String getId() {
		return id;
	}

	public String getFullName() {
		return firstName + lastName;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
