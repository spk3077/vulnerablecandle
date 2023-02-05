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

import csec.vulnerable.beans.Order;
import csec.vulnerable.http.Response;
import csec.vulnerable.service.ShoppingCartService;
import csec.vulnerable.service.OrderService;

@RestController()
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	ShoppingCartService ShoppingCartService;
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER','ROLE_SELLER')")
	@GetMapping
	public List<Order> getOrders(Authentication authentication){
		return orderService.getOrders(authentication);
	}
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER','ROLE_SELLER')")
	@GetMapping("/{id}")
	public Order getOrder(@PathVariable int id) {
		return orderService.getOrder(id);
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER','ROLE_SELLER')")
	@PostMapping
	public Response addOrder(@RequestBody Order order, Authentication authentication) {
		
		return orderService.addOrder(order, authentication);
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER','ROLE_SELLER')")
	@PutMapping
	public Response changeOrder(@RequestBody Order order) {
		return orderService.changeOrder(order);
	}
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER','ROLE_SELLER')")
	@DeleteMapping("/{id}")
	public Response deleteOrder(@PathVariable int id) {
		return orderService.deleteOrder(id);
	}

			
}

