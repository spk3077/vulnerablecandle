package csec.vulnerable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import csec.vulnerable.beans.Payment;
import csec.vulnerable.beans.User;
import csec.vulnerable.dao.UserDao;
import csec.vulnerable.http.Response;
import csec.vulnerable.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    
    @Autowired
    UserDao userDao;

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable int id, Authentication authentication) {
        Payment payment = paymentService.getPayment(id);
        if (payment != null) {
            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
                    || payment.getUser().equals(userDao.findByUsername(authentication.getName()))) {
                return payment;
            }
        }
        return null;
    }

    @GetMapping
    public List<Payment> getPayments(Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            return paymentService.getPayments();
        } else {
            return paymentService.getPayments(user);
        }
    }

    @PostMapping
    public Response addPayment(@RequestBody Payment payment, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        payment.setUser(user);

        if (paymentService.isValidCardNumber(payment.getCardNumber())
                && paymentService.isValidExpiryDate(payment.getExpiryMonth(), payment.getExpiryYear())) {
            return paymentService.addPayment(payment);
        } else {
            return new Response(false, "Invalid payment details");
        }
    }

    @PutMapping
    public Response changePayment(@RequestBody Payment payment, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        payment.setUser(user);
        if (paymentService.isValidCardNumber(payment.getCardNumber())
                && paymentService.isValidExpiryDate(payment.getExpiryMonth(), payment.getExpiryYear())) {
            return paymentService.changePayment(payment);
        } else {
            return new Response(false, "Invalid payment details");
        }
    }

    @DeleteMapping("/{id}")
    public Response deletePayment(@PathVariable int id, Authentication authentication) {
        Payment payment = paymentService.getPayment(id);
        if (payment != null) {
            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
                    || payment.getUser().getUsername().equals(userDao.findByUsername(authentication.getName()))) {
                return paymentService.deletePayment(id,authentication);
            } else {
                return new Response(false, "You are not authorized to delete this payment");
            }
        } else {
            return new Response(false, "Payment not found");
        }
    }
}
