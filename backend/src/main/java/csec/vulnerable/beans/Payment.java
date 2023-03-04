package csec.vulnerable.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "ecom_payment")
public class Payment {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PAYMENT_SEQ")
	@SequenceGenerator(name = "PAYMENT_SEQ",sequenceName = "ECOM_PAYMENT_SEQ",allocationSize = 1)
	private int id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties("mypayments")
	User user;
    @Column
    private long card_number;
    @Column
    private String owner_name;
    @Column(name="expiry_month", nullable=false)
    private int expiryMonth;
    @Column(name="expiry_year", nullable=false)
    private int expiryYear;
    @Column
    private int sec_code;

    
    public Payment() {
    }

    public Payment(long card_number, String owner_name, String expiration_date, int sec_code) {
        this.card_number = card_number;
        this.owner_name = owner_name;
        this.sec_code = sec_code;
    }
    
    public Payment(User user, long card_number, String owner_name, int expiryMonth, int expiryYear, int sec_code) {
        this.user = user;
        this.card_number = card_number;
        this.owner_name = owner_name;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.sec_code = sec_code;
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(int expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(int expiryYear) {
        this.expiryYear = expiryYear;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public long getCard_number() {
        return card_number;
    }
    public void setCard_number(long card_number) {
        this.card_number = card_number;
    }
    public String getOwner_name() {
        return owner_name;
    }
    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }
    public int getSec_code() {
        return sec_code;
    }
    public void setSec_code(int sec_code) {
        this.sec_code = sec_code;
    }

    
}
