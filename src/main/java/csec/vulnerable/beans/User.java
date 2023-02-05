package csec.vulnerable.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "ecom_user")
public class User implements UserDetails{
private static final long serialVersionUID = 1L;
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQ")
	@SequenceGenerator(name = "USER_SEQ",sequenceName = "ECOM_USER_SEQ",allocationSize = 1)
	private int id;
	@Column(name = "username",unique = true,nullable = false)
	private String username;
	
	@Column(name = "password",nullable = false)
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "c_user_c_user_profile",joinColumns = {
			@JoinColumn(name = "user_id",referencedColumnName = "id")},inverseJoinColumns = {
				@JoinColumn(name = "user_profile_id",referencedColumnName = "id")})
	private List<UserProfile> profiles = new ArrayList<UserProfile>();
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private UserInfo userInfo;
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<ProductReview> myreviews;
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Payment> mypayments;
			
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
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", profiles=" + profiles
				+ ", userInfo=" + userInfo + ", myreviews=" + myreviews + "]";
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
	public List<Payment> getMypayments() {
		return mypayments;
	}
	public void setMypayments(List<Payment> mypayments) {
		this.mypayments = mypayments;
	}
}
