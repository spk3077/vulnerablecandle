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
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

import com.fasterxml.jackson.annotation.JsonIgnore;

import csec.vulnerable.dto.PaymentDTO;

@Entity
@Table(name = "ecom_payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "PAYMENT_SEQ")
    @SequenceGenerator(name = "PAYMENT_SEQ", sequenceName = "ECOM_PAYMENT_SEQ", allocationSize = 1, initialValue = 8)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    @Column
    @NotNull
    @CreditCardNumber
    private String cardNumber;

    @Column
    @NotNull
    @Size(min = 2, max = 50)
    private String ownerName;

    @Column(name = "expiry_month", nullable = false)
    @NotNull
    @Min(1)
    @Max(12)
    private int expiryMonth;

    @Column(name = "expiry_year", nullable = false)
    @NotNull
    @Min(23)
    @Max(33)
    private int expiryYear;

    @Column
    @NotNull
    @Digits(integer = 3, fraction = 0)
    private int secCode;

    public Payment() {
    }

    
    public Payment(@NotNull @CreditCardNumber String cardNumber, @NotNull @Size(min = 2, max = 50) String ownerName,
            @NotNull @Min(1) @Max(12) int expiryMonth, @NotNull @Min(23) @Max(33) int expiryYear,
            @NotNull @Digits(integer = 3, fraction = 0) int secCode) {
        this.cardNumber = cardNumber;
        this.ownerName = ownerName;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.secCode = secCode;
    }

    public Payment(User user, @NotNull @CreditCardNumber String cardNumber,
            @NotNull @Size(min = 2, max = 50) String ownerName, @NotNull @Min(1) @Max(12) int expiryMonth,
            @NotNull @Min(23) @Max(33) int expiryYear, @NotNull @Digits(integer = 3, fraction = 0) int secCode) {
        this.user = user;
        this.cardNumber = cardNumber;
        this.ownerName = ownerName;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.secCode = secCode;
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


    public String getCardNumber() {
        return cardNumber;
    }


    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public String getOwnerName() {
        return ownerName;
    }


    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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


    public int getSecCode() {
        return secCode;
    }


    public void setSecCode(int secCode) {
        this.secCode = secCode;
    }


    public Payment getAnonymousPayment() {
        Payment result = new Payment();
        result.setOwnerName(ownerName);
        result.setCardNumber("**** **** **** " + cardNumber.substring(cardNumber.length() - 4));
        return result;
    }

    @Override
    public String toString() {
        return "Payment [cardNumber=" + cardNumber + ", ownerName=" + ownerName + ", expiryMonth=" + expiryMonth
                + ", expiryYear=" + expiryYear + ", secCode=" + secCode + "]";
    }

    public PaymentDTO toPaymentDTO() {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(id);
        dto.setCardNumber(cardNumber);
        dto.setOwnerName(ownerName);
        dto.setExpiryMonth(expiryMonth);
        dto.setExpiryYear(expiryYear);
        dto.setSecCode(secCode);
        return dto;
    }

}
