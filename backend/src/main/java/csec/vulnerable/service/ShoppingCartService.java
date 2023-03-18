package csec.vulnerable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csec.vulnerable.beans.CartItem;
import csec.vulnerable.beans.Product;
import csec.vulnerable.beans.ShoppingCart;
import csec.vulnerable.dao.CartItemDao;
import csec.vulnerable.dao.ProductDao;
import csec.vulnerable.dao.ShoppingCartDao;
import csec.vulnerable.dao.UserDao;
import csec.vulnerable.http.Response;

@Service
@Transactional
public class ShoppingCartService {

    @Autowired
    ShoppingCartDao shoppingCartDao;

    @Autowired
    CartItemDao cartItemDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    UserDao userDao;

    public ShoppingCart getShoppingCart(Authentication authentication) {
        return shoppingCartDao.findByUser(userDao.findByUsername(authentication.getName()));
    }

    public Response addCartItem(int productId, int quantity, Authentication authentication) {
        try {
            Product product = productDao.findById(productId).get();
            ShoppingCart shoppingCart = shoppingCartDao.findByUser(userDao.findByUsername(authentication.getName()));
            CartItem cartItem = new CartItem(product, quantity);
            cartItem.setShoppingCart(shoppingCart);
            cartItemDao.save(cartItem);
            return new Response(true);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    public Response updateCartItem(int cartItemId, int quantity, Authentication authentication) {
        try {
            CartItem cartItem = cartItemDao.findById(cartItemId).get();
            ShoppingCart shoppingCart = shoppingCartDao.findByUser(userDao.findByUsername(authentication.getName()));
            if (cartItem.getShoppingCart().getId() != shoppingCart.getId()) {
                return new Response(false, "Unauthorized");
            }
            cartItem.setQuantity(quantity);
            cartItemDao.save(cartItem);
            return new Response(true);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    public Response removeCartItem(int cartItemId, Authentication authentication) {
        try {
            CartItem cartItem = cartItemDao.findById(cartItemId).get();
            ShoppingCart shoppingCart = shoppingCartDao.findByUser(userDao.findByUsername(authentication.getName()));
            if (cartItem.getShoppingCart().getId() != shoppingCart.getId()) {
                return new Response(false, "Unauthorized");
            }
            cartItemDao.deleteById(cartItemId);
            return new Response(true);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

	public Response clearShoppingCart(Authentication authentication) {
		try {
			ShoppingCart shoppingCart = shoppingCartDao.findByUser(userDao.findByUsername(authentication.getName()));
			List<CartItem> cartItems = shoppingCart.getCartItems();
			cartItemDao.deleteAll(cartItems);
			return new Response(true);
		} catch (Exception e) {
			return new Response(false, e.getMessage());
		}
	}
}
