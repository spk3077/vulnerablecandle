package csec.vulnerable.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "ecom_user")
public class User implements UserDetails{
private static final long serialVersionUID = 1L;
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQ")
	@SequenceGenerator(name = "USER_SEQ",sequenceName = "ECOM_USER_SEQ",allocationSize = 1, initialValue = 8)
	private int id;
	
	@Column(name = "username", unique = true, nullable = false)
	@Size(min = 4, max = 16, message = "Username must be between 4 and 16 characters long.")
	private String username;
	
	@Column(name = "password", nullable = false)
	@Size(min = 8, max = 35, message = "Password must be between 8 and 35 characters long.")
	@Pattern(regexp = ".*[0-9].*", message = "Password must contain at least one digit.")
	@Pattern(regexp = ".*[!@#\\$%\\^&\\*].*", message = "Password must contain at least one symbol.")
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "c_user_c_user_profile",joinColumns = {
			@JoinColumn(name = "user_id",referencedColumnName = "id")},inverseJoinColumns = {
				@JoinColumn(name = "user_profile_id",referencedColumnName = "id")})
	@JsonIgnore
	private List<UserProfile> profiles = new ArrayList<UserProfile>();
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private UserInfo userInfo;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProductReview> myreviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
    private List<Payment> payments;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
    private ShoppingCart shoppingCart;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Order> orders;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return profiles;
	}
	@Override
	public String getPassword() {

		return password;
	}
	@Override
	public String getUsername() {
		
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	@Override
	public boolean isEnabled() {
		
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", profiles=" + profiles
				+ ", userInfo=" + userInfo + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<UserProfile> getProfiles() {
		return profiles;
	}
	public void setProfiles(List<UserProfile> profiles) {
		this.profiles = profiles;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(int id, String username, String password, List<UserProfile> profiles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.profiles = profiles;
	}
	public User() {
		super();
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public List<ProductReview> getMyreviews() {
		return myreviews;
	}
	public void setMyreviews(List<ProductReview> myreviews) {
		this.myreviews = myreviews;
	}
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	public List<Payment> getPayments() {
		return payments;
	}
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	
	
}
