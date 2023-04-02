package csec.vulnerable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import csec.vulnerable.beans.BillingInfo;
import csec.vulnerable.beans.Order;
import csec.vulnerable.beans.User;
import csec.vulnerable.dao.UserDao;
import csec.vulnerable.service.OrderService;
import csec.vulnerable.service.ShoppingCartService;

@RestController()
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    UserDao userDao;

    @GetMapping
    public List<Order> getOrders(Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        return orderService.getOrdersByUser(user);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order createOrder(Authentication authentication, @RequestBody(required = false) BillingInfo billingInfo) {
        return orderService.createOrder(authentication, billingInfo);
    }
}
