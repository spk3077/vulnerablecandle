package csec.vulnerable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @GetMapping("/")
    public ShoppingCart getShoppingCart(Authentication authentication) {
        return shoppingCartService.getShoppingCart(authentication);
    }
    
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/add/{productId}/{quantity}")
    public Response addCartItem(@PathVariable int productId, @PathVariable int quantity, Authentication authentication) {
        return shoppingCartService.addCartItem(productId, quantity, authentication);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("/update/{cartItemId}/{quantity}")
    public Response updateCartItem(@PathVariable int cartItemId, @PathVariable int quantity, Authentication authentication) {
        return shoppingCartService.updateCartItem(cartItemId, quantity, authentication);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping("/remove/{cartItemId}")
    public Response removeCartItem(@PathVariable int cartItemId, Authentication authentication) {
        return shoppingCartService.removeCartItem(cartItemId, authentication);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping("/clear")
    public Response clearShoppingCart(Authentication authentication) {
        return shoppingCartService.clearShoppingCart(authentication);
    }
}

