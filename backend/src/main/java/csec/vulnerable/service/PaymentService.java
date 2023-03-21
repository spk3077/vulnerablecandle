package csec.vulnerable.service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import csec.vulnerable.beans.Payment;
import csec.vulnerable.beans.User;
import csec.vulnerable.dao.PaymentDao;
import csec.vulnerable.http.Response;

@Service
@Transactional
public class PaymentService {

    @Autowired
    PaymentDao paymentDao;
    
    @Autowired
    UserDao userDao;

    public Payment getPayment(int id,Authentication authentication) {
        Optional<Payment> payment = paymentDao.findById(id);
        return payment.orElse(null);
    }

    public List<Payment> getPayments(User user) {
        return paymentDao.findByUser(user);
    }

    public List<Payment> getPayments() {
        return paymentDao.findAll();
    }

    public Response addPayment(Payment payment, Authentication authentication) {
        if (isValidCardNumber(payment.getCardNumber())
                && isValidExpiryDate(payment.getExpiryMonth(), payment.getExpiryYear())) {
            paymentDao.save(payment);
            return new Response(true, "Payment added successfully");
        } else {
            return new Response(false, "Invalid payment details");
        }
    }

    public Response changePayment(Payment payment, Authentication authentication) {
        if (isValidCardNumber(payment.getCardNumber())
                && isValidExpiryDate(payment.getExpiryMonth(), payment.getExpiryYear())) {
            paymentDao.save(payment);
            return new Response(true, "Payment updated successfully");
        } else {
            return new Response(false, "Invalid payment details");
        }
    }

    public Response deletePayment(int id, Authentication authentication) {
        Optional<Payment> payment = paymentDao.findById(id);
        if (payment.isPresent()) {
            if (payment.get().getUser().getUsername().equals(userDao.getUsername())) {
                paymentDao.deleteById(id);
                return new Response(true, "Payment deleted successfully");
            } else {
                return new Response(false, "You are not authorized to delete this payment");
            }
        } else {
            return new Response(false, "Payment not found");
        }
    }

    private boolean isValidCardNumber(String cardNumber) {
        int[] digits = new int[cardNumber.length()];
        for (int i = 0; i < cardNumber.length(); i++) {
            digits[i] = Integer.parseInt(cardNumber.substring(i, i + 1));
        }
        for (int i = digits.length - 2; i >= 0; i = i - 2) {
            int j = digits[i];
            j = j * 2;
            if (j > 9) {
                j = j % 10 + 1;
            }
            digits[i] = j;
        }
        int sum = 0;
        for (int i = 0; i < digits.length; i++) {
            sum += digits[i];
        }
        return sum % 10 == 0;
    }

    private boolean isValidExpiryDate(int expiryMonth, int expiryYear) {
        YearMonth expiry = YearMonth.parse(String.format("%02d/%02d", expiryMonth, expiryYear), DateTimeFormatter.ofPattern("MM/yy"));
        YearMonth now = YearMonth.now();
        return !expiry.isBefore(now);
    }

}    
