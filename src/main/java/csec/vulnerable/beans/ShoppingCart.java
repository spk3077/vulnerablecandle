package csec.vulnerable.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "ecom_shoppingcart")
public class ShoppingCart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SHOPPINGCART_SEQ")
	@SequenceGenerator(name = "SHOPPINGCART_SEQ",sequenceName = "ECOM_SHOPPINGCART_SEQ",allocationSize = 1)
	private int id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	@JsonIgnoreProperties("purchases")
	Order order;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	Product product;
	@Column
	private int quantity;
	
	public ShoppingCart() {
		super();
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public ShoppingCart(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", productName=" + product.getName() + 
				", productPrice=" + product.getPrice() +", quantity=" + quantity +"]";
	}
	
}

