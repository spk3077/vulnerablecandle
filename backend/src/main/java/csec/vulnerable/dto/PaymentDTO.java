package csec.vulnerable.dto;

public class PaymentDTO {

    private int id;
    private String cardNumber;
    private String ownerName;
    private int expiryMonth;
    private int expiryYear;
    private int secCode;
    
    public PaymentDTO() {
    }

    public PaymentDTO(int id, String cardNumber, String ownerName, int expiryMonth, int expiryYear, int secCode) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.ownerName = ownerName;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.secCode = secCode;
    }

    public PaymentDTO(int id, String cardNumber, String ownerName, int expiryMonth, int expiryYear) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.ownerName = ownerName;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public PaymentDTO getAnonymousPayment() {
        PaymentDTO result = new PaymentDTO();
        result.setOwnerName(ownerName);
        result.setCardNumber("**** **** **** " + cardNumber.substring(cardNumber.length() - 4));
        result.setExpiryMonth(expiryMonth);
        result.setExpiryYear(expiryYear);
        return result;
    }
    

    @Override
    public String toString() {
        return "PaymentDTO [id=" + id + ", cardNumber=" + cardNumber + ", ownerName=" + ownerName + ", expiryMonth="
                + expiryMonth + ", expiryYear=" + expiryYear + ", secCode=" + secCode + "]";
    }

}
