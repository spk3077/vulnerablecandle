package csec.vulnerable.beans;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

