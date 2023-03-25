package csec.vulnerable.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ecom_shoppingcart")
public class ShoppingCart {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SHOPPING_CART_SEQ")
    @SequenceGenerator(name = "SHOPPING_CART_SEQ", sequenceName = "SHOPPING_CART_SEQ", allocationSize = 1)
    @JsonIgnore
    private int id;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private double totalPrice = 0;
    
    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
        cartItem.setShoppingCart(this);
        this.addTotalPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
    }

    public void removeCartItem(CartItem cartItem) {
        this.cartItems.remove(cartItem);
        cartItem.setShoppingCart(null);
        this.addTotalPrice(-cartItem.getProduct().getPrice() * cartItem.getQuantity());
    }

    public void clearCart() {
        this.cartItems.clear();
        this.totalPrice = 0;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addTotalPrice(double price){
        this.totalPrice += price;
    }

    
}