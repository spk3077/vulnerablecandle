package csec.vulnerable.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csec.vulnerable.beans.CartItem;
import csec.vulnerable.beans.Order;
import csec.vulnerable.beans.OrderItem;
import csec.vulnerable.beans.ShoppingCart;
import csec.vulnerable.beans.User;
import csec.vulnerable.dao.OrderDao;
import csec.vulnerable.dao.ShoppingCartDao;
import csec.vulnerable.dao.UserDao;

@Service
@Transactional
public class OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ShoppingCartDao shoppingCartDao;

    public List<Order> getOrdersByUser(User user) {
        return orderDao.findAllByUser(user);
    }

    public Order getOrderById(int id) {
        return orderDao.findById(id).orElse(null);
    }

    public Order createOrder(Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(authentication);
        Order order = new Order();
        order.setUser(user);
        for (CartItem cartItem : shoppingCart.getCartItems()) {
            OrderItem orderItem = new OrderItem(cartItem.getProduct(), cartItem.getQuantity());
            order.addOrderItem(orderItem);
        }
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Date currentDate = new java.sql.Date(now.getTime());
        order.setPurchase_date(currentDate);
        order = orderDao.save(order);
        shoppingCartService.clearShoppingCart(authentication);
        return order;
    }
}
