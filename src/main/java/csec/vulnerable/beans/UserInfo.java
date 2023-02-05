package csec.vulnerable.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ecom_user_info")
public class UserInfo {
	@OneToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	User user;
	@Id
	int id;
	public int getId() {
		return id;
	}
	public void setId(User user) {
		this.id = user.getId();
	}
	@Column
	String name;
	@Column
	String phone;
	@Column
	String email;
	@Column
	String address;
	@Column
	String city;
	@Column
	String state;
	@Column
	String zip;
	public UserInfo() {
		super();
	}
	public UserInfo(User user, String name, String phone, String email, String address, String city, String state,
			String zip) {
		super();
		this.user = user;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public UserInfo(int id, String name, String phone, String email, String address, String city, String state,
			String zip) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	public UserInfo(String name, String phone, String email, String address, String city, String state, String zip) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	@Override
	public String toString() {
		return "UserInfo [user=" + user + ", name=" + name + ", phone=" + phone + ", email=" + email + ", address="
				+ address + ", city=" + city + ", state=" + state + ", zip=" + zip + "]";
	}
	
	
	
	
}