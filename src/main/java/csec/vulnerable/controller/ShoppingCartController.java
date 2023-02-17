package csec.vulnerable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import csec.vulnerable.beans.ShoppingCart;
import csec.vulnerable.http.Response;
import csec.vulnerable.service.ShoppingCartService;

@RestController()
@RequestMapping("/shoppingcarts")
public class ShoppingCartController {
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/{id}")
	public ShoppingCart getProduct(@PathVariable int id) {
		return shoppingCartService.getShoppingCart(id);
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	@GetMapping
	public List<ShoppingCart> getShoppingCarts() {
		return shoppingCartService.getShoppingCarts();
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	@PostMapping
	public Response addShoppingCart(@RequestBody ShoppingCart ShoppingCart) {
		return shoppingCartService.addShoppingCart(ShoppingCart);
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	@PutMapping
	public Response changeShoppingCart(@RequestBody ShoppingCart ShoppingCart) {
		return shoppingCartService.changeShoppingCart(ShoppingCart);
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	@DeleteMapping("/{id}")
	public Response deleteShoppingCart(@PathVariable int id) {
		return shoppingCartService.deleteShoppingCart(id);
	}
}

