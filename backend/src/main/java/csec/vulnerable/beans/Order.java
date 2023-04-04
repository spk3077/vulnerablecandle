package csec.vulnerable.beans;

import java.sql.Date;
import java.util.ArrayList;
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

import csec.vulnerable.dto.OrderUserIdDTO;

@Entity
@Table(name = "ecom_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ORDER_SEQ")
    @SequenceGenerator(name = "ORDER_SEQ", sequenceName = "ECOM_ORDER_SEQ", allocationSize = 1,initialValue = 10)
    private int id;

    @Column
	@JsonFormat(pattern = "yyyy-MM-dd")
    private Date purchase_date;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	@JsonIgnore
    private User user;

	@Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "payment_owner_name")
    private String paymentOwnerName;

	
    @Column(name = "total_price")
    private double totalPrice;

    public Order() {
    }
	


	public Order(int id, Date purchase_date, List<OrderItem> orderItems, User user, String name, String email,
			String address, String city, String state, String zip, String cardNumber, String paymentOwnerName,
			double totalPrice) {
		this.id = id;
		this.purchase_date = purchase_date;
		this.orderItems = orderItems;
		this.user = user;
		this.name = name;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.cardNumber = cardNumber;
		this.paymentOwnerName = paymentOwnerName;
		this.totalPrice = totalPrice;
	}



	public Order(int id, Date purchase_date, List<OrderItem> orderItems, User user, String name, String email,
			String address, String city, String state, String zip, String cardNumber, String paymentOwnerName) {
		this.id = id;
		this.purchase_date = purchase_date;
		this.orderItems = orderItems;
		this.user = user;
		this.name = name;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.cardNumber = cardNumber;
		this.paymentOwnerName = paymentOwnerName;
	}


	public Order(int id, Date purchase_date, List<OrderItem> orderItems, User user) {
		this.id = id;
		this.purchase_date = purchase_date;
		this.orderItems = orderItems;
		this.user = user;
	}

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

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void addOrderItem(OrderItem orderItem) {
		this.orderItems.add(orderItem);
		orderItem.setOrder(this);
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


	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getPaymentOwnerName() {
		return paymentOwnerName;
	}


	public void setPaymentOwnerName(String paymentOwnerName) {
		this.paymentOwnerName = paymentOwnerName;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", purchase_date=" + purchase_date + ", orderItems=" + orderItems + ", user=" + user
				+ ", name=" + name + ", email=" + email + ", address=" + address + ", city=" + city + ", state=" + state
				+ ", zip=" + zip + ", cardNumber=" + cardNumber + ", paymentOwnerName=" + paymentOwnerName
				+ ", totalPrice=" + totalPrice + "]";
	}


	public OrderUserIdDTO getUserId(){
		OrderUserIdDTO user_id = new OrderUserIdDTO();
		user_id.setUser_id(this.getUser().getId());
		return user_id;
	}
	
}
