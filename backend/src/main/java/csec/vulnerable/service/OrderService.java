package csec.vulnerable.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csec.vulnerable.beans.CartItem;
import csec.vulnerable.beans.Order;
import csec.vulnerable.beans.OrderItem;
import csec.vulnerable.beans.ShoppingCart;
import csec.vulnerable.beans.User;
import csec.vulnerable.dao.OrderDao;
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

    public List<Order> getOrdersByUser(User user) {
        return orderDao.findAllByUser(user);
    }

    public Order getOrderById(int id) {
        return orderDao.findById(id).orElse(null);
    }

    public Order createOrder(ShoppingCart shoppingCart, User user) {
        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(new ArrayList<>());
        
        for (CartItem cartItem : shoppingCart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
        
        return orderDao.save(order);
    }
}
