package csec.vulnerable.beans;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity 
@Table(name = "ecom_order")
public class Order {
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ORDER_SEQ")
	@SequenceGenerator(name = "ORDER_SEQ",sequenceName = "ECOM_ORDER_SEQ",allocationSize = 1)
	private int id;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date purchase_date;
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<ShoppingCart> purchases;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	User user;
	
	/**
	 * Constructor
	 */
	public Order() {
		super();
	}


	public Order(Date purchase_date, List<ShoppingCart> purchases) {
		super();
		this.purchase_date = purchase_date;
		this.purchases = purchases;
	}
	
	/**
	 * Get and Set
	 */
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getPurchase_date() {
		return purchase_date;
	}


	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}


	public List<ShoppingCart> getPurchases() {
		return purchases;
	}


	public void setPurchases(List<ShoppingCart> purchases) {
		this.purchases = purchases;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "Order [id=" + id + ", purchase_date=" + purchase_date + ", purchases="
				+ purchases + ", user=" + user + "]";
	}
	
}

