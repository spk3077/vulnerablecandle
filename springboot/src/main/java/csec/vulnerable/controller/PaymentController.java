package csec.vulnerable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import csec.vulnerable.http.Response;
import csec.vulnerable.service.PaymentService;

@RestController()
@RequestMapping("/payments")
public class PaymentController {
	@Autowired
	PaymentService paymentService;
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER','ROLE_SELLER')")
	@GetMapping("/{id}")
	public Payment getProduct(@PathVariable int id) {
		return paymentService.getPayment(id);
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER','ROLE_SELLER')")
	@GetMapping
	public List<Payment> getPayments() {
		return paymentService.getPayments();
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER','ROLE_SELLER')")
	@PostMapping
	public Response addPayment(@RequestBody Payment payment,Authentication authentication) {
		return paymentService.addPayment(payment,authentication);
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER','ROLE_SELLER')")
	@PutMapping
	public Response changePayment(@RequestBody Payment payment,Authentication authentication) {
		return paymentService.changePayment(payment, authentication);
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER','ROLE_SELLER')")
	@DeleteMapping("/{id}")
	public Response deletePayment(@PathVariable int id,Authentication authentication) {
		return paymentService.deletePayment(id, authentication);
	}
}