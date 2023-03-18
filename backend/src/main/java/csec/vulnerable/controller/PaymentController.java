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
import csec.vulnerable.http.Response;
import csec.vulnerable.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable int id, Authentication authentication) {
        Payment payment = paymentService.getPayment(id);
        if (payment != null) {
            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
                    || payment.getUser().getId() == ((User)authentication.getPrincipal()).getId()) {
                return payment;
            }
        }
        return null;
    }

    @GetMapping
    public List<Payment> getPayments(Authentication authentication) {
        return paymentService.getPayments(authentication);
    }

    @PostMapping
    public Response addPayment(@RequestBody Payment payment, Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
                || payment.getUser().getId() == ((User)authentication.getPrincipal()).getId()) {
            return paymentService.addPayment(payment, authentication);
        } else {
            return new Response(false, "You are not authorized to add this payment");
        }
    }

    @PutMapping
    public Response changePayment(@RequestBody Payment payment, Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
                || payment.getUser().getId() == ((User)authentication.getPrincipal()).getId()) {
            return paymentService.changePayment(payment, authentication);
        } else {
            return new Response(false, "You are not authorized to change this payment");
        }
    }

    @DeleteMapping("/{id}")
    public Response deletePayment(@PathVariable int id, Authentication authentication) {
        Payment payment = paymentService.getPayment(id);
        if (payment != null) {
            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))
                    || payment.getUser().getId() == ((User)authentication.getPrincipal()).getId()) {
                return paymentService.deletePayment(id, authentication);
            } else {
                return new Response(false, "You are not authorized to delete this payment");
            }
        } else {
            return new Response(false, "Payment not found");
        }
    }
}
