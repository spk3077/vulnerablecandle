package csec.vulnerable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csec.vulnerable.beans.CartItem;
import csec.vulnerable.beans.Product;
import csec.vulnerable.beans.ShoppingCart;
import csec.vulnerable.beans.User;
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
        User user = userDao.findByUsername(authentication.getName());
        ShoppingCart shoppingCart = shoppingCartDao.findByUser(user);
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart(user);
            shoppingCartDao.save(shoppingCart);
        }
        return shoppingCart;
    }

    public Response addCartItem(int productId, int quantity, double price, Authentication authentication) {
        try {
            Product product = productDao.findById(productId).orElse(null);
            if (product == null) {
                return new Response(false, "Product not found");
            }
            ShoppingCart shoppingCart = getShoppingCart(authentication);
            List<CartItem> cartItems = shoppingCart.getCartItems();
            CartItem existingCartItem = null;
            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct().getId() == productId) {
                    cartItem.setItemPrice(price);
                    existingCartItem = cartItem;
                    break;
                }
            }
            if (existingCartItem == null) {
                CartItem cartItem = new CartItem(product, quantity);
                cartItem.setItemPrice(price);
                cartItem.setShoppingCart(shoppingCart);
                cartItemDao.save(cartItem);
                shoppingCart.addCartItem(cartItem);
            } else {
                existingCartItem.setItemPrice(price);
                int newQuantity = existingCartItem.getQuantity() + quantity;
                existingCartItem.setQuantity(newQuantity);
                cartItemDao.save(existingCartItem);
                double newTotalPrice = shoppingCart.getTotalPrice() + (existingCartItem.getItemPrice() * quantity);
                shoppingCart.setTotalPrice(newTotalPrice);
            }
            shoppingCartDao.save(shoppingCart);
            return new Response(true);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    public Response updateCartItem(int cartItemId, int quantity, Authentication authentication) {
        try {
            CartItem cartItem = cartItemDao.findById(cartItemId).orElse(null);
            if (cartItem == null) {
                return new Response(false, "Cart item not found");
            }
            ShoppingCart shoppingCart = getShoppingCart(authentication);
            if (cartItem.getShoppingCart().getId() != shoppingCart.getId()) {
                return new Response(false, "Unauthorized");
            }
            double oldItemPrice = cartItem.getProduct().getPrice() * cartItem.getQuantity();
            double newItemPrice = cartItem.getProduct().getPrice() * quantity;
            cartItem.setQuantity(quantity);
            cartItemDao.save(cartItem);
            shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() - oldItemPrice + newItemPrice);
            shoppingCartDao.save(shoppingCart);
            return new Response(true);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }

    public Response removeCartItem(int cartItemId, Authentication authentication) {
        try {
            CartItem cartItem = cartItemDao.findById(cartItemId).orElse(null);
            if (cartItem == null) {
                return new Response(false, "Cart item not found");
            }
            ShoppingCart shoppingCart = getShoppingCart(authentication);
            if (cartItem.getShoppingCart().getId() != shoppingCart.getId()) {
                return new Response(false, "Unauthorized");
            }
            double itemPrice = cartItem.getItemPrice() * cartItem.getQuantity();
            shoppingCart.setTotalPrice(shoppingCart.getTotalPrice() - itemPrice);
            shoppingCart.getCartItems().remove(cartItem);
            cartItemDao.deleteById(cartItemId);
            shoppingCartDao.save(shoppingCart);
            return new Response(true);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
    }
    public Response clearShoppingCart(Authentication authentication) {
        try {
            ShoppingCart shoppingCart = getShoppingCart(authentication);
            List<CartItem> cartItems = shoppingCart.getCartItems();
            int totalPrice = 0;
            shoppingCart.setTotalPrice(totalPrice);
            shoppingCart.getCartItems().clear();
            cartItemDao.deleteAll(cartItems);
            shoppingCartDao.save(shoppingCart);
            return new Response(true);
        } catch (Exception e) {
            return new Response(false, e.getMessage());
        }
   }
}