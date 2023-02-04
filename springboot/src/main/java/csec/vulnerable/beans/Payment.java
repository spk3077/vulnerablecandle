package csec.vulnerable.beans;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Column
	@JsonFormat(pattern = "MM-yyyy")
	private Date expiration_date;
    @Column
    private int sec_code;
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
    public Date getExpiration_date() {
        return expiration_date;
    }
    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }
    public int getSec_code() {
        return sec_code;
    }
    public void setSec_code(int sec_code) {
        this.sec_code = sec_code;
    }

    
}
