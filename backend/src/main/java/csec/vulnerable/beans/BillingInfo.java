package csec.vulnerable.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

@Entity
@Table(name = "ecom_billing_info")
public class BillingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    @Size(min = 3, max = 50)
    private String name;

    @Column(name = "email")
    @Size(min = 5, max = 50)
    private String email;

    @Column(name = "address")
    @Size(min = 3, max = 100)
    private String address;

    @Column(name = "city")
    @Size(min = 2, max = 20)
    private String city;

    @Column(name = "state")
    @Size(min = 2, max = 20)
    private String state;

    @Column(name = "zip")
    @Size(min = 2, max = 20)
    private String zip;

    @Column(name = "card_number")
    @CreditCardNumber
    private String cardNumber;

    @Column(name = "payment_owner_name")
    @Size(min = 2, max = 50)
    private String paymentOwnerName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public BillingInfo() {}

    public BillingInfo(String name, String email, String address, String city, String state, String zip,
                       String cardNumber, String paymentOwnerName) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.cardNumber = cardNumber;
        this.paymentOwnerName = paymentOwnerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "BillingInfo [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", city="
                + city + ", state=" + state + ", zip=" + zip + ", cardNumber=" + cardNumber + ", paymentOwnerName="
                + paymentOwnerName + ", order=" + order + "]";
    }
}

    
